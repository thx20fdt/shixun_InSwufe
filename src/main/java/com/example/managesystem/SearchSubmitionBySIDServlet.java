package com.example.managesystem;

import com.example.managesystem.activity.submit;
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

@WebServlet(name = "SearchSubmitionBySID", value = "/SearchSubmitionBySID")
public class SearchSubmitionBySIDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SID = request.getParameter("SID");
        List<submit> submitions =new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select Activity_Submit.AID,Activity.ANAME, Activity_Submit.SID, Student.NAME,Activity_Submit.GID, CONVERT(nvarchar(max), Activity_Submit.CONTENT),Activity.ACONTENT ,Activity_Submit.SCORE from Activity_Submit ,Activity ,Student where Activity_Submit.AID = Activity.AID and Activity_Submit.SID = Student.SID and Activity_Submit.SID =" + "'" + SID + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                submit submit = new submit();
                submit.setAID(rs.getString(1));
                submit.setANAME(rs.getString(2));
                submit.setSID(rs.getString(3));
                submit.setSNAME(rs.getString(4));
                submit.setGID(rs.getString(5));
                submit.setCONTENT(rs.getString(6));
                submit.setACONTENT(rs.getString(7));
                submit.setSCORE(rs.getDouble(8));
                submitions.add(submit);
            }
            rs.close();
            stmt.close();
            con.close();
            request.setAttribute("submitions", submitions);
            request.getRequestDispatcher("StuScore.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
