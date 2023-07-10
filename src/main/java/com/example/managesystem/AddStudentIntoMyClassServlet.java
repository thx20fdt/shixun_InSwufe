package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AddStudentIntoMyClass", value = "/AddStudentIntoMyClass")
public class AddStudentIntoMyClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String CID = (String)session.getAttribute("cid");
        String SID = request.getParameter("SID");
        try {
            Connection con = DBUtil.getConnection();
            String sql0 ="select * from Student_Class where SID=? and CID=?";
            PreparedStatement ps0 = con.prepareStatement(sql0);
            ps0.setString(1,SID);
            ps0.setString(2,CID);
            ResultSet rs = ps0.executeQuery();
            if(rs.next()){
                rs.close();
                ps0.close();
                con.close();
                response.sendRedirect("AddStudentIntoMyClass.jsp");

            }else{
                String sql = "Insert Into Student_Class values(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,SID);
                ps.setString(2,CID);
                ps.executeUpdate();
                ps.close();
                con.close();
                response.sendRedirect("SelectStudent.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
