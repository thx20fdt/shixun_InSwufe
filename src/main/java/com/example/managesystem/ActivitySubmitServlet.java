package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "ActivitySubmit", value = "/ActivitySubmit")
public class ActivitySubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        HttpSession session =request.getSession();
        String AID = request.getParameter("AID");
        String SID = (String) session.getAttribute("id");
        String CONTENT = request.getParameter("CONTENT");
        String sql = "select * from Activity_Submit where AID=? and SID=?";
        try {
            Connection con = DBUtil.getConnection();
            //从分组表中查询是否存在分组
            String sql0 = "select GID from stu_group where SID=? and GID = ?";
            PreparedStatement ps0 = con.prepareStatement(sql0);
            ps0.setString(1,SID);
            ps0.setString(2,AID);
            ResultSet resultSet = ps0.executeQuery();
            String GID;
            if(resultSet.next()){
                GID = resultSet.getString(1);
            }else{
                GID = null;
            }
            resultSet.close();
            ps0.close();

            //判断是否已经提交过了
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,AID);
            ps.setString(2,SID);
            ResultSet rs0 = ps.executeQuery();
            if(rs0.next()){
                String msg ="已提交过了，无需再次提交";
                rs0.close();
                ps.close();
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("AnswerActivity.jsp").forward(request,response);
            }else{
                String sql1 = "Insert into Activity_Submit values(?,?,?,?,?,?,?) ";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1,SID);
                ps1.setString(2,AID);
                ps1.setString(3,GID);
                java.util.Date date = new java.util.Date();
                ps1.setDate(4,new java.sql.Date(date.getTime()));
                String condition ="未批阅";
                ps1.setString(5,condition);
                ps1.setDouble(6,0);
                ps1.setString(7,CONTENT);
                ps1.executeUpdate();
                ps1.close();

            }
            con.close();
            request.getRequestDispatcher("MyCourseInfoServlet").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
