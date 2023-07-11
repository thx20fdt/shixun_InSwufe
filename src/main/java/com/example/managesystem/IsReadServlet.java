package com.example.managesystem;

import com.example.managesystem.activity.submit;
import com.example.managesystem.db.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "IsRead", value = "/IsRead")
public class IsReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String SID = request.getParameter("SID");
        String AID = request.getParameter("AID");
        String sql = "select condition from activity_submit where SID=? and aid = ?";
        String sql1 = "select Activity_Submit.AID,Activity.ANAME, Activity_Submit.SID, Student.NAME, Activity_Submit.CONTENT,Activity.ACONTENT ,Activity_Submit.SCORE,Activity_Submit.CONDITION ,Activity_Submit.GID from Activity_Submit ,Activity ,Student where Activity_Submit.AID ="+"'"+AID+"'"+"and Activity_Submit.AID = Activity.AID and Activity_Submit.SID = Student.SID and Activity_Submit.SID ="+"'"+SID+"'";
        submit submit = new submit();

        try {
            Connection con  = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,SID);
            ps.setString(2,AID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals("已批阅")){
                    Statement stmt = con.createStatement();
                    ResultSet rs1 = stmt.executeQuery(sql1); //执行查询
                    if (rs.next()){
                        submit.setAID(rs1.getString("AID"));
                        submit.setANAME(rs1.getString("ANAME"));
                        submit.setSID(rs1.getString("SID"));
                        submit.setSNAME(rs1.getString("NAME"));
                        submit.setCONTENT(rs1.getString("CONTENT"));
                        submit.setACONTENT(rs1.getString("ACONTENT"));
                        submit.setSCORE(rs1.getDouble("SCORE"));
                        submit.setCONDITION(rs1.getString("CONDITION"));
                        if(rs1.getString("GID").isEmpty()){
                            submit.setGID("未分组");

                        }else{
                            submit.setGID(rs1.getString("GID"));
                        }
                    }
                    rs.close();
                    stmt.close();
                    con.close();
                    request.getSession().setAttribute("submit",submit);
                    request.getRequestDispatcher("SubmitionHaveBeenRead.jsp").forward(request,response);
                }else{
                    request.setAttribute("SID",SID);
                    request.setAttribute("AID",AID);
                    request.getRequestDispatcher("SubmitionDetail").forward(request,response);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
