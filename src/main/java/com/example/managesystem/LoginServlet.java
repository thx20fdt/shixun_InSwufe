package com.example.managesystem;

import DB.DBUtil;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {

            String sql = "select * from Student where SID = ? and PASSWORD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String userRole = request.getParameter("user_role");
                HttpSession session = request.getSession();
                session.setAttribute("id", userid);
                if (userRole.equals("teacher")) {
                    request.getRequestDispatcher("Teacher.jsp").forward(request, response);
                } else if (userRole.equals("student")) {
                    request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "用户ID或密码错误!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}