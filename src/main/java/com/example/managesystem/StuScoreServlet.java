package com.example.managesystem;

import com.example.managesystem.activity.submit;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StuScore", value = "/StuScore")
public class StuScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");
        String TID =(String) request.getSession().getAttribute("id");
        String sql ="Select cid from class where tid =? ";
        String sql1 ="select class.cname,Activity_Submit.sid,Student.name,Activity.aname,Activity_Submit.score from class,student,activity_submit,Activity where  class.cid=Activity.CID and Activity.aid=Activity_Submit.AID and student.sid = activity_submit.sid and activity.cid=? ";
        List<submit> submitions = new ArrayList<>();
        List<String> CIDS = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,TID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String CID = rs.getString(1);
                CIDS.add(CID);
            }
            rs.close();
            ps.close();

            for(String cid :CIDS){
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1,cid);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next()){
                    submit submit = new submit();
                    submit.setCNAME(rs1.getString(1));
                    submit.setSID(rs1.getString(2));
                    submit.setSNAME(rs1.getString(3));
                    submit.setANAME(rs1.getString(4));
                    submit.setSCORE(rs1.getDouble(5));
                    submitions.add(submit);
                }
                rs1.close();
                ps1.close();
            }
            con.close();
            request.setAttribute("submitions",submitions);
            request.getRequestDispatcher("StuScore.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
