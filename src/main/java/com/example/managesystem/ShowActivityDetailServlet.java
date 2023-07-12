package com.example.managesystem;

import com.example.managesystem.activity.submit;
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

@WebServlet(name = "ShowActivityDetail", value = "/ShowActivityDetail")
public class ShowActivityDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session =request.getSession();
        request.setCharacterEncoding("utf-8");
        String AID =  request.getParameter("AID");
        String ACONTENT = request.getParameter("ACONTENT");
        String ENDTIME = request.getParameter("ENDTIME");
        String CID = (String) session.getAttribute("CID");
        List<submit> submitList = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select Activity_Submit.AID,Activity.ANAME, Activity_Submit.SID, Student.NAME,Activity_Submit.GID,  Activity_Submit.CONTENT ,Activity_Submit.CONDITION from Activity_Submit ,Activity ,Student where Activity_Submit.AID = Activity.AID and Activity_Submit.SID = Student.SID and Activity_Submit.AID ="+"'"+AID+"'";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                submit submit = new submit();
                submit.setAID(rs.getString(1));
                submit.setANAME(rs.getString(2));
                submit.setSID(rs.getString(3));
                submit.setSNAME(rs.getString(4));

                if (rs.getString(5)==null){
                    submit.setGID("未分组");
                }else{
                    submit.setGID(rs.getString(5));
                }

                submit.setCONTENT(rs.getString(6));
                submit.setCONDITION(rs.getString(7));
                submitList.add(submit);
            }
            rs.close();
            stmt.close();
            con.close();




            request.setAttribute("acontent",ACONTENT);
            request.setAttribute("endtime",ENDTIME);
            request.setAttribute("submitList",submitList);
            request.getRequestDispatcher("ActivitySubmitionStatus.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
