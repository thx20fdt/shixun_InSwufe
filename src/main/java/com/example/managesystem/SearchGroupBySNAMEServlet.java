package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.group.Group;

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

@WebServlet(name = "SearchGroupBySNAME", value = "/SearchGroupBySNAME")
public class SearchGroupBySNAMEServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        HttpSession session =request.getSession();
        String AID = (String) session.getAttribute("aiddd1");
        String SNAME = request.getParameter("SNAME");
        String GID = null;
        List<String> Members = new ArrayList<>();
        Group group = new Group();
        List<Group> groupList = new ArrayList<>();
        String sql0="select stu_group.gid from stu_group,student where stu_group.SID=Student.SID and stu_group.aid=? and Student.name = ?";
        String sql1="select name from student,stu_group where student.sid=stu_group.sid and stu_group.gid=?";
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps0 = con.prepareStatement(sql0);
            ps0.setString(1,AID);
            ps0.setString(2,SNAME);
            ResultSet rs0 = ps0.executeQuery();
            if(rs0.next()){
                GID=rs0.getString(1);
                PreparedStatement ps1= con.prepareStatement(sql1);
                ps1.setString(1,GID);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next()){
                    Members.add(rs1.getString(1));
                }
                group.setMembers(Members);
                group.setGID(GID);
                groupList.add(group);
                request.setAttribute("groupList",groupList);
                request.getRequestDispatcher("GroupView.jsp").forward(request, response);
            }else{
                request.setAttribute("AID",AID);
                request.getRequestDispatcher("GroupViewServlet").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
