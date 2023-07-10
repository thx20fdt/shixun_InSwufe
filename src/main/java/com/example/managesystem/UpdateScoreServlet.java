package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "UpdateScore", value = "/UpdateScore")
public class UpdateScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String AID = request.getParameter("AID");
        String SID = request.getParameter("SID");
        float score = Float.parseFloat(request.getParameter("score"));
        try {
            Connection con = DBUtil.getConnection();
            Statement statement = con.createStatement();
            String sql = "Update Activity_Submit set SCORE ="+score +" where SID ="+"'"+SID+"'"+"and AID="+"'"+AID+"'";
            statement.executeUpdate(sql);
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("AID",AID);
        request.setAttribute("SID",SID);
        request.getRequestDispatcher("/SubmitionDetail").forward(request,response);
    }
}
