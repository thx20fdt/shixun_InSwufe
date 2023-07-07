package com.example.managesystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String ID = (String)req.getParameter("ID");
        String NAME = (String)req.getParameter("NAME");
        String MAJOR = (String)req.getParameter("MAJOR");
        String GRADE = (String)req.getParameter("GRADE");
        String GENDER = (String)req.getParameter("GENDER");
        String PHONE = (String)req.getParameter("PHONE");
        String PASSWORD =(String)req.getParameter("password");
        String rePASSWORD =(String)req.getParameter("repassword");
        if(PASSWORD.equals(rePASSWORD)){
            try {
                System.out.println("begin");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                System.out.println("加载驱动成功");

                String url = "jdbc:sqlserver://rm-2vctxb20ce77uelfy2o.mssql.cn-chengdu.rds.aliyuncs.com:3433;DatabaseName=ClassManagement_T4;encrypt=false";
//            jdbc:sqlserver://localhost:1433;DatabaseName=
                String username = "t4_cap";
                String key = "Thx20021120@";
                Connection conn = DriverManager.getConnection(url,username,key);
                Statement stmt = conn.createStatement();
                String sql0 = "SELECT * FROM Student WHERE SID = "+ID;
                ResultSet rs = stmt.executeQuery(sql0);
                rs.last();
                int cnt = rs.getRow();
                if(cnt == 0){
                    String sql1 = "INSERT INTO Student VALUES("+ID+","+NAME+","+MAJOR+","+GRADE+","+GENDER+","+PHONE+","+PASSWORD+")";
                    stmt.executeUpdate(sql1);
                    stmt.close();
                    conn.close();
                    System.out.println("OK");
                }
                else{
                    req.setAttribute("error","该学号已被使用");
                    req.getRequestDispatcher("register.jsp").forward(req,resp);
                }
            } catch (InstantiationException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("jiazaiqudongshibai");
            } catch (IllegalAccessException | SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
