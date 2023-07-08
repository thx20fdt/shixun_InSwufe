package com.example.managesystem;


import com.example.managesystem.course.course;
import com.example.managesystem.db.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchClassServlet")
public class SearchClassServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        req.setCharacterEncoding("utf-8");
        String cname = req.getParameter("cname");
        String tname = req.getParameter("tname");
        List<course> courses = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            String sql = "select Class.CNAME,Teacher.NAME,Class.ClASSTIME from Class,Teacher where Class.TID = Teacher.TID and Teacher.NAME = "+"'"+tname+"'" +"and Class.CNAME="+"'"+cname+"'";
            Statement stmt =  con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                course c = new course();
                c.setCNAME(rs.getString(1));
                c.setTNAME(rs.getString(2));
                c.setCLASSTIME(rs.getString(3));
                courses.add(c);
            }
            DBUtil.closeConnection(con);
            stmt.close();
            rs.close();
            req.setAttribute("courses",courses);
            req.getRequestDispatcher("StuClass.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }


}
