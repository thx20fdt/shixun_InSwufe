package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.*;

@WebServlet(name = "DownloadFile", value = "/DownloadFile")
public class DownloadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String AID = request.getParameter("AID");
        String SID = request.getParameter("SID");
        Connection con = null;
        try {
            con = DBUtil.getConnection();

            // 在查询语句中同时获取提交的文件和文件名
            PreparedStatement ps = con.prepareStatement("SELECT SUBMITFILE, FILE_NAME FROM Activity_Submit WHERE SID = ? and AID=?");
            ps.setString(1, SID);
            ps.setString(2, AID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] fileBytes = rs.getBytes(1);
                // 从查询结果中获取文件名
                String fileName = rs.getString(2);

                ServletOutputStream out = response.getOutputStream();
                if (fileBytes != null) {
                    // 获取文件扩展名
                    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

                    // 使用文件扩展名来获取 MIME 类型
                    String mimeType = getMimeType(fileExtension);
                    response.setContentType(mimeType);

                    // 对文件名进行 URL 编码，以处理特殊字符
                    String encodedFileName = URLEncoder.encode(fileName, "UTF-8");

                    // 将编码后的文件名用于 Content-Disposition 头
                    response.setHeader("Content-disposition", "attachment; filename=" + encodedFileName);

                    out.write(fileBytes);
                } else {
                    out.write("No file found for student.".getBytes());
                    response.setContentType("text/plain");
                }
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve files from DB", e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    // 通过文件扩展名返回 MIME 类型
    private String getMimeType(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "doc":
                return "application/msword";
            case "docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            // TODO: 在此处添加更多文件类型的 MIME 类型
            default:
                return "application/octet-stream";  // 未知类型默认为 "application/octet-stream"
        }
    }
}
