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

@WebServlet(name = "CourseToughtByMe", value = "/CourseToughtByMe")
public class CourseToughtByMeServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String TID = (String) session.getAttribute("id");
        List<course> courses = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            String sql = "select Class.CID,Class.CNAME,Class.CLASSTIME from Class where TID = "+"'"+TID+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                course c = new course();
                c.setCID(rs.getString(1));
                c.setCNAME(rs.getString(2));
                c.setCLASSTIME(rs.getString(3));
                courses.add(c);
            }
            rs.close();
            st.close();
            con.close();
            request.setAttribute("courses",courses);
            request.getRequestDispatcher("IndexTea.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
