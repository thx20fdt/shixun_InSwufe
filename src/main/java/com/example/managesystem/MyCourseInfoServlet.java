package com.example.managesystem;

import com.example.managesystem.course.course;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyCourseInfoServlet", value = "/MyCourseInfoServlet")
public class MyCourseInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String SID = (String) session.getAttribute("id");
        Connection conn;
        List<course> cs = new ArrayList<>();
        try {
            conn= DBUtil.getConnection();
            String sql = "SELECT [Class].CNAME,[Teacher].NAME,[Class].CLASSTIME FROM Class,[Student_Class],Teacher WHERE Class.TID = Teacher.TID and Class.CID = [Student_Class].CID AND [Student_Class].SID="+"'"+SID+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                course c = new course();
                c.setCNAME(rs.getString(1));
                c.setTNAME(rs.getString(2));
                c.setCLASSTIME(rs.getString(3));
                cs.add(c);
            }
            rs.close();
            stmt.close();
            DBUtil.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("cs", cs);
        // 转发请求到网页主界面
        request.getRequestDispatcher("StuClass.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
