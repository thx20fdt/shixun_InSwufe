package com.example.managesystem;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        // 连接数据库
        String url = "jdbc:sqlserver://rm-2vctxb20ce77uelfy2o.mssql.cn-chengdu.rds.aliyuncs.com:3433;databaseName=ClassManagement_T4";
        String user = "t4_cap";
        String pwd = "Thx20021120@";
        try(Connection conn = DriverManager.getConnection(url, user, pwd)){

            String sql = "select * from users where userid =? and password =?";

            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, userid);
                pstmt.setString(2, password);

                try(ResultSet rs = pstmt.executeQuery()){

                    if(rs.next()) {
                        response.sendRedirect("LoginSuccess.jsp");
                    }else {
                        request.setAttribute("error", "用户名或密码错误");
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }
}