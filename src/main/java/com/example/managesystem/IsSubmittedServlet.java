package com.example.managesystem;

import com.example.managesystem.activity.activity;
import com.example.managesystem.activity.submit;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IsSubmitted", value = "/IsSubmitted")
public class IsSubmittedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        HttpSession session =request.getSession();

        activity activity = (activity)request.getAttribute("activity");
        String SID = (String) session.getAttribute("id");
        String sql = "select CONTENT from Activity_Submit where AID=? and SID=?";
        submit submit = new submit();

        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,activity.AID);
            ps.setString(2,SID);
            ResultSet rs0 = ps.executeQuery();
            if(rs0.next()){
                submit.setANAME(activity.ANAME);
                submit.setAID(activity.AID);
                submit.setCONTENT(rs0.getString(1));
                rs0.close();
                ps.close();

                request.setAttribute("submit",submit);
                request.setAttribute("activity",activity);
                request.getRequestDispatcher("YourAnswer.jsp").forward(request,response);
            }else{
                request.setAttribute("activity",activity);
                request.getRequestDispatcher("AnswerActivity.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        HttpSession session =request.getSession();

        activity activity = (activity)request.getAttribute("activity");
        String SID = (String) session.getAttribute("id");
        String sql = "select CONTENT from Activity_Submit where AID=? and SID=?";
        submit submit = new submit();

        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,activity.AID);
            ps.setString(2,SID);
            ResultSet rs0 = ps.executeQuery();
            if(rs0.next()){
                String msg ="已提交过了，无需再次提交";
                rs0.close();
                ps.close();
                submit.setANAME(activity.ANAME);
                submit.setAID(activity.AID);
                submit.setCONTENT(rs0.getString(1));
                request.setAttribute("submit",submit);
                request.getRequestDispatcher("YourAnswer.jsp").forward(request,response);
            }else{
                request.setAttribute("activity",activity);
                request.getRequestDispatcher("AnswerActivity.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
