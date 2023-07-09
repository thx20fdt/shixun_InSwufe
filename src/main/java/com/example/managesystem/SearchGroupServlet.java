package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.group.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchGroupServlet")
public class SearchGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取查询参数
        String courseName = request.getParameter("CNAME");
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");

        // 查询小组信息
        List<Group> groupDetailList = searchGroup(courseName,studentSid);
//        Group group=new Group();
//        group.setANAME(groupId);
//        groupDetailList.add(group);

        // 存储查询结果到会话属性
        session.setAttribute("groupDetailList", groupDetailList);
        request.setAttribute("CNAME",courseName);
        // 转发到SearchGroup.jsp显示查询结果
        request.getRequestDispatcher("SearchGroup.jsp").forward(request, response);
    }

    private List<Group> searchGroup(String courseName, String studentSid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Group> groupDetailList = new ArrayList<>();

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT a.ANAME, sg.GID, a.AID " +
                    "FROM Student_Class sc " +
                    "JOIN Class c ON sc.CID = c.CID " +
                    "JOIN Activity a ON c.CID = a.CID " +
                    "LEFT JOIN stu_group sg ON sc.SID = sg.SID AND a.AID = sg.AID " +
                    "WHERE c.CNAME = ? AND sc.SID = ? ";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, courseName);
            statement.setString(2, studentSid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                Group group = new Group();
                group.setANAME(resultSet.getString("ANAME"));
                group.setAID(resultSet.getString("AID"));
                if (resultSet.getString("GID")==null){
                    group.setGID("暂无小组");
                }
                else{
                    group.setGID(resultSet.getString("GID"));
                }

                // 查询小组成员
                List<String> members = getGroupMembers(group.getGID());
                group.setMembers(members);

                groupDetailList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return groupDetailList;
    }

    private List<String> getGroupMembers(String groupId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> members = new ArrayList<>();

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT s.NAME " +
                    "FROM stu_group sg " +
                    "JOIN Student s ON sg.SID = s.SID " +
                    "WHERE sg.GID = ? ";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, groupId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                String memberName = resultSet.getString("NAME");
                members.add(memberName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return members;
    }



}
