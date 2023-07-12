package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String ID = (String)req.getParameter("username");
        String NAME =(String) req.getParameter("nickname");
        String PHONE=(String) req.getParameter("phone");
        String PASSWORD =(String)req.getParameter("password");
        String rePASSWORD =(String)req.getParameter("repeatpassword");
        String type = (String)req.getParameter("type");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!PASSWORD.equals(rePASSWORD)){
            try {
                req.setAttribute("msg","密码和确认密码不一致");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

        if(type.equals("student")){

            if(isExistingStudent(ID,PHONE,conn)){
                try {
                        req.setAttribute("msg","该学号、职工号或者手机号已被使用");
                        req.getRequestDispatcher("register.jsp").forward(req, resp);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
            }else{
                registerStudent(ID,NAME,PHONE,PASSWORD,conn);
            }
        }else if(type.equals("teacher")){
            if(isExistingTeacher(ID,PHONE,conn)){
                try {
                    req.setAttribute("msg","该学号、职工号或者手机号已被使用");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }else{
                registerTeacher(ID,NAME,PHONE,PASSWORD,conn);
            }

        }
        resp.sendRedirect("Login.jsp");
    }

    private boolean isExistingStudent(String id, String phone, Connection conn) {
        boolean exists = false;
        try {
            String sql = "SELECT * FROM Student  WHERE SID = ? OR PHONE = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }
    private boolean isExistingTeacher(String id, String phone, Connection conn) {
        boolean exists = false;
        try {
            String sql = "SELECT * FROM Teacher  WHERE TID = ? OR PHONE = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }
    private void registerStudent(String id, String name, String phone, String password,  Connection conn) {

        try {
            String sql = "INSERT INTO Student(SID, NAME, PHONE, PASSWORD) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void registerTeacher(String id, String name, String phone, String password,  Connection conn) {

        try {
            String sql = "INSERT INTO Teacher(TID, NAME, PHONE, PASSWORD) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

