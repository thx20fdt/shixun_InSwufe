package com.example.managesystem;

import com.example.managesystem.course.course;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyCourse", value = "/MyCourse")
public class MyCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String SID = (String) session.getAttribute("id");
        Connection conn;
        List<course> courses = new ArrayList<>();
        try {
            conn= DBUtil.getConnection();
            String sql = "SELECT [Class].[CID],[Class].[CNAME],[Class].[TID] FROM [Class],[Student-Class] WHERE [Class].[CID] = [Student-Class].[CID] AND [Student-Class].SID="+"'"+SID+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                course course = new course();
                course c = new course();
                c.setCID(rs.getString(1));
                c.setCNAME(rs.getString(2));
                c.setTID(rs.getString(3));
                courses.add(c);
            }
            rs.close();
            stmt.close();
            DBUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }//加载驱动并建立数据库连接

        request.setAttribute("courses", courses);
        // 转发请求到网页主界面
        request.getRequestDispatcher("IndexStu.jsp").forward(request, response);
    }
}
