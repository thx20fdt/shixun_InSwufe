package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
        String cid = request.getParameter("cid");
        String activityName = request.getParameter("activityName");
        String activityContent = request.getParameter("activityContent");
        boolean groupable = Boolean.parseBoolean(request.getParameter("groupable"));
        String activityStartTime = request.getParameter("activityStartTime");
        String activityDeadline = request.getParameter("activityDeadline");

        // Generate new AID based on existing AIDs
        String aid = generateNewAID(cid);

        // Insert new activity into the database
        insertActivity(aid, activityName, activityContent, groupable, cid, activityStartTime, activityDeadline);
        request.setAttribute("CID", cid);
        // Redirect back to the original page or display success message
        request.getRequestDispatcher("ManageActivityServlet").forward(request, response);
    }

    private String generateNewAID(String cid) throws ServletException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DBUtil.getConnection();
            String existingAIDQuery = "SELECT TOP 1 AID FROM Activity WHERE AID LIKE ? ORDER BY AID DESC ";
            pstmt = conn.prepareStatement(existingAIDQuery);
            pstmt.setString(1, cid + "%");
            resultSet = pstmt.executeQuery();

            int suffix = 1;
            if (resultSet.next()) {
                String existingAID = resultSet.getString("AID");
                String[] parts = existingAID.split("_");
                if (parts.length > 1) {
                    int existingSuffix = Integer.parseInt(parts[1]);
                    suffix = Math.max(suffix, existingSuffix + 1);
                }
            }

            return cid + "_" + suffix;
        } catch (SQLException e) {
            throw new ServletException("Database operation failed", e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pstmt);
            DBUtil.closeConnection(conn);
        }
    }

    private void insertActivity(String aid, String activityName, String activityContent, boolean groupable, String cid,
                                String activityStartTime, String activityDeadline) throws ServletException {
        Connection conn = null;
        PreparedStatement pstmt = null;

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
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Database operation failed", e);
        } finally {
            DBUtil.closeStatement(pstmt);
            DBUtil.closeConnection(conn);
        }
    }
}
