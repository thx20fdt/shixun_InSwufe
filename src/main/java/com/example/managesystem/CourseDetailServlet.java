package com.example.managesystem;

import com.example.managesystem.course.course;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "CourseDetail", value = "/CourseDetail")
public class CourseDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String CID = request.getParameter("name");
        try {
            Connection con = DBUtil.getConnection();
            String sql = "Select Class.CID,Class.CNAME,Teacher.Name FROM Class,Teacher where Class.TID = Teacher.TID and CLass.CID="+"'"+CID+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            course course = new course();
            course.setCID(rs.getString(1));
            course.setCNAME(rs.getString(2));
            course.setTNAME(rs.getString(3));
            request.setAttribute("course",course);
            request.getRequestDispatcher("CourseInfo.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
