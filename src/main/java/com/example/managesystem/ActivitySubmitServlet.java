package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@WebServlet(name = "ActivitySubmit", value = "/ActivitySubmit")
@MultipartConfig
public class ActivitySubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String AID =request.getParameter("AID");
        String SID = (String) request.getSession().getAttribute("id");
        String CONTENT = request.getParameter("CONTENT");
        Part submitfile = request.getPart("submitfile");
        String sql0 = "select GID from stu_group where SID=? and GID = ?";
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement ps0 = con.prepareStatement(sql0);
            ps0.setString(1,SID);
            ps0.setString(2,AID);
            ResultSet resultSet = ps0.executeQuery();
            String GID;
            if(resultSet.next()){
                GID = resultSet.getString(1);
            }else{
                GID = null;
            }
            resultSet.close();
            ps0.close();


            String sql1 = "Insert into Activity_Submit(sid, aid, gid, submittime, condition, score, content) values(?,?,?,?,?,?,?) ";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1,SID);
            ps1.setString(2,AID);
            ps1.setString(3,GID);
            java.util.Date date = new java.util.Date();
            ps1.setDate(4,new Date(date.getTime()));
            String condition ="未批阅";
            ps1.setString(5,condition);
            ps1.setDouble(6,0);
            ps1.setString(7,CONTENT);
            ps1.executeUpdate();

            if (submitfile != null) {
                // 获取文件名
                String fileName = submitfile.getSubmittedFileName();

                InputStream fileContent = submitfile.getInputStream();
                PreparedStatement ps2 = con.prepareStatement("UPDATE Activity_Submit SET SUBMITFILE = ?, FILE_NAME = ? WHERE SID = ? and aid=?");
                ps2.setBinaryStream(1, fileContent);
                ps2.setString(2, fileName);
                ps2.setString(3, SID);
                ps2.setString(4,AID);
                ps2.executeUpdate();
                ps2.close();
            }
            ps1.close();
            con.close();
            request.getRequestDispatcher("CourseDetail").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 从请求头"Content-Disposition"中提取文件名
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("Content-Disposition header = " + contentDisp); // Add this line
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length()-1);
                System.out.println("Extracted file name = " + fileName); // Add this line
                return fileName;
            }
        }
        return "";
    }
}


