package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReleaseActivityServlet")
public class ReleaseActivityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        String cid = request.getParameter("cid");
        String activityName = request.getParameter("activityName");
        String activityContent = request.getParameter("activityContent");
        boolean groupable = Boolean.parseBoolean(request.getParameter("groupable"));
        String activityStartTime = request.getParameter("activityStartTime");
        String activityDeadline = request.getParameter("activityDeadline");
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (activityName.isEmpty() || activityContent.isEmpty()) {
            // 活动名称和活动内容不能为空
            response.getWriter().println("活动名称和活动内容不能为空");
            return;
        }


        if (activityStartTime.isEmpty() || activityDeadline.isEmpty()) {
            // 活动名称和活动内容不能为空
            response.getWriter().println("活动开始时间和活动结束时间不能为空");
            return;
        }

        Date startDateTime = null;
        Date endDateTime = null;
        Date now = new Date();
        try {
            startDateTime = sdf.parse(activityStartTime);
            endDateTime = sdf.parse(activityDeadline);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if (now.compareTo(startDateTime) > 0 || now.compareTo(endDateTime) > 0) {
            response.getWriter().println("活动开始时间和截止时间不能早于当前时间");
            return;
        }

        if (activityStartTime.compareTo(activityDeadline) > 0) {
            // 截止时间不能早于开始时间
            response.getWriter().println("截止时间不能早于开始时间");

        }
        else {

            String aid = generateNewAID(cid);
            if (insertActivity(aid, activityName, activityContent, groupable, cid, activityStartTime, activityDeadline)) {
                response.getWriter().println("活动发布成功");
            }
        }
    }
    private String generateNewAID(String cid) throws ServletException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DBUtil.getConnection();
            String existingAIDQuery = "SELECT AID FROM Activity WHERE CID = ?";
            pstmt = conn.prepareStatement(existingAIDQuery);
            pstmt.setString(1, cid );
            resultSet = pstmt.executeQuery();

            int maxActivityIdSuffix = 0;
            if(resultSet.next()){
                String existingAID = resultSet.getString("AID");
                String[] parts = existingAID.split("_");
                if (parts.length > 1) {
                    String suffix = parts[1];
                    int activityIdSuffix = Integer.parseInt(suffix);
                    if (activityIdSuffix > maxActivityIdSuffix) {
                        maxActivityIdSuffix = activityIdSuffix;
                    }
                }
            while (resultSet.next()) {
                 existingAID = resultSet.getString("AID");
                 parts = existingAID.split("_");
                if (parts.length > 1) {
                    String suffix = parts[1];
                    int activityIdSuffix = Integer.parseInt(suffix);
                    if (activityIdSuffix > maxActivityIdSuffix) {
                        maxActivityIdSuffix = activityIdSuffix;
                    }
                }
            }
            int newActivityIdSuffix = maxActivityIdSuffix + 1;
            String newActivityId = cid + "_" + newActivityIdSuffix;
            return newActivityId;
            }
            else {
                String newActivityId = cid + "_" + 1;
                return newActivityId;
            }

        } catch (SQLException e) {
            throw new ServletException("Database operation failed", e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pstmt);
            DBUtil.closeConnection(conn);
        }
    }

    private boolean insertActivity(String aid, String activityName, String activityContent, boolean groupable, String cid,
                                String activityStartTime, String activityDeadline) throws ServletException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;


        try {
            conn = DBUtil.getConnection();
            String insertQuery = "INSERT INTO Activity (AID, ANAME, ACONTENT, CID, type, BEGINTIME, ENDTIME) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, aid);
            pstmt.setString(2, activityName);
            pstmt.setString(3, activityContent);
            pstmt.setString(4, cid);
            pstmt.setBoolean(5, groupable);
            pstmt.setTimestamp(6, Timestamp.valueOf(activityStartTime));
            pstmt.setTimestamp(7, Timestamp.valueOf(activityDeadline));
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            throw new ServletException("Database operation failed", e);
        } finally {
            DBUtil.closeStatement(pstmt);
            DBUtil.closeConnection(conn);
        }
        return success;
    }
}
