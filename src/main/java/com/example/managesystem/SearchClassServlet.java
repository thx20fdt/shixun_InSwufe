package com.example.managesystem;

import DB.DBUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchClassServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        req.setCharacterEncoding("utf-8");
        String ser = (String) req.getParameter("search");
        String serType = (String) req.getParameter("searchtype");
        if(serType.equals("CID")){
            Connection conn;
            try {
                conn = DBUtil.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(isExsitClass(ser,conn)){
                HttpSession session = req.getSession();
                session.setAttribute("CID",ser);
                resp.sendRedirect("selectClass.jsp");
            }

        }
    }

    public boolean isExsitClass(String CID, Connection conn){
        boolean exists = false;
        try {
            String sql = "SELECT * FROM Class  WHERE CID = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, CID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }
}
