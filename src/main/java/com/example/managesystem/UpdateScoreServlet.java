package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

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
        String updatesql ="Update Activity_submit set condition =? where SID = ? and AID=?";
        try {
            Connection con = DBUtil.getConnection();
            Statement statement = con.createStatement();
            String sql = "Update Activity_Submit set SCORE ="+score +" where SID ="+"'"+SID+"'"+"and AID="+"'"+AID+"'";
            statement.executeUpdate(sql);
            statement.close();

            PreparedStatement ps = con.prepareStatement(updatesql);//更新批阅情况
            ps.setString(1,"已批阅");
            ps.setString(2,SID);
            ps.setString(3,AID);
            ps.executeUpdate();
            ps.close();

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("IsRead?AID="+AID+"&SID="+SID);
        
    }
}
