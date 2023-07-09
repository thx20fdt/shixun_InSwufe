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

@WebServlet(name = "AddIntoMyClassServlet", value = "/AddIntoMyClassServlet")
public class AddIntoMyClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] CIDS = request.getParameterValues("CID");
        HttpSession session = request.getSession();
        String SID = (String)session.getAttribute("id");
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt= con.createStatement();
            String sql;
            String sql0;
            ResultSet rs;
            for(String CID :CIDS){
                sql = "select SID from [Student_Class] where SID="+"'"+SID+"'"+"and CID = "+"'"+CID+"'";
                rs=stmt.executeQuery(sql);
                if(rs.next()){
                    rs.close();
                    continue;
                }
                rs.close();
                sql0 = "insert into [Student_Class] values("+"'"+SID+"'"+","+"'"+CID+"'"+")";
                stmt.executeUpdate(sql0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } response.sendRedirect("MyCourseInfoServlet");
    }
}
