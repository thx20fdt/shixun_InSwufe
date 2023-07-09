package com.example.managesystem;


import com.example.managesystem.db.DBUtil;

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
        String userRole = request.getParameter("user_role");
        if(userRole.equals("student")){
            try (Connection conn = DBUtil.getConnection()) {

                String sql = "select * from Student where SID = ? and PASSWORD = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, userid);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("id", userid);
                    request.getRequestDispatcher("MyCourse").forward(request, response);

                } else {
                    request.setAttribute("msg", "用户ID或密码错误!");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (userRole.equals("teacher")) {
            try (Connection conn = DBUtil.getConnection()) {

                String sql = "select * from Teacher where TID = ? and PASSWORD = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, userid);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("id", userid);
                    response.sendRedirect("CourseToughtByMe");

                } else {
                    request.setAttribute("msg", "用户ID或密码错误!");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}