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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchClassByTNAME", value = "/SearchClassByTNAME")
public class SearchClassByTNAME extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String TNAME=(String) request.getParameter("TNAME");
        List<course> courseList = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            String sql = "select Class.CID ,Class.CNAME,Teacher.NAME,Class.CLASSTIME from Class,Teacher where Class.TID = Teacher.TID and Teacher.NAME="+"'"+TNAME+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                course c = new course();
                c.setCID(rs.getString(1));
                c.setCNAME(rs.getString(2));
                c.setTNAME(rs.getString(3));
                c.setCLASSTIME(rs.getString(4));
                courseList.add(c);
            }
            DBUtil.closeConnection(con);
            stmt.close();
            rs.close();
            request.setAttribute("courses",courseList);
            request.getRequestDispatcher("SelectClass.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
