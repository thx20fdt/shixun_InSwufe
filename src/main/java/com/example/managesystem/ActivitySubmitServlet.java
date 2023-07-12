package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import java.io.InputStream;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "ActivitySubmit", value = "/ActivitySubmit")
public class ActivitySubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String AID =request.getParameter("AID");
        String SID = (String) request.getSession().getAttribute("id");
        String CONTENT = request.getParameter("CONTENT");
        String sql0 = "select GID from stu_group where SID=? and GID = ?";
        try {
            Connection con = DBUtil.getConnection();
            //从分组表中查询是否存在分组

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
            con.close();
            request.getRequestDispatcher("CourseDetail").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
