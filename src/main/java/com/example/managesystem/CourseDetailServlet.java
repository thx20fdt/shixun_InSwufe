package com.example.managesystem;

import com.example.managesystem.activity.Activity;
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

@WebServlet(name = "CourseDetail", value = "/CourseDetail")
public class CourseDetailServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String CID = request.getParameter("CID");
        try {
            Connection con = DBUtil.getConnection();
            String sql = "Select Class.CNAME,Teacher.Name ,Class.CLASSTIME FROM Class,Teacher where Class.TID = Teacher.TID and CLass.CID= " + ""+"'"+CID+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            course course = new course();
            if (rs.next()){
                course.setCNAME(rs.getString(1));
                course.setTNAME(rs.getString(2));
                course.setCLASSTIME(rs.getString(3));
            }
            rs.close();
            request.setAttribute("course",course);
            List<Activity> acs = new ArrayList<>();
            String sql1 = "select AID,ANAME,ACONTENT,BEGINTIME,ENDTIME from Activity where CID =" +"'"+CID+"'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()){
                Activity a =  new Activity();
                a.setAID(rs1.getString(1));
                a.setANAME(rs1.getString(2));
                a.setACONTENT(rs1.getString(3));
                a.setBEGINTIME(rs1.getDate(4).toString());
                a.setENDTIME(rs1.getDate(5).toString());
                acs.add(a);
            }
            rs1.close();
            stmt.close();
            request.setAttribute("acs",acs);
            request.getRequestDispatcher("CourseInfo.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
