package com.example.managesystem;

import com.example.managesystem.activity.activity;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "PrepareActivityServlet", value = "/PrepareActivityServlet")
public class PrepareActivityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");
        String AID = request.getParameter("AID");

        try {
            Connection con = DBUtil.getConnection();
            String sql1 = "select AID,ANAME,ACONTENT,BEGINTIME,ENDTIME from Activity where AID =" +"'"+AID+"'";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql1);
            activity a = new activity();
            if(rs.next()){
                a.setAID(rs.getString(1));
                a.setANAME(rs.getString(2));
                a.setACONTENT(rs.getString(3));
                a.setBEGINTIME(rs.getDate(4).toString());
                a.setENDTIME(rs.getDate(5).toString());
            }
            request.setAttribute("activity",a);
            request.getRequestDispatcher("AnswerActivity.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
