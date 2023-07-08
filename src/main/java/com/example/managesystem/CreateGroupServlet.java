package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

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

@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String memberName = request.getParameter("memberName");
        String memberId = request.getParameter("memberId");
        String activityId = request.getParameter("AID");
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");

        // 验证学号和电话是否匹配
        if (validateStudent(memberId, activityId)) {
            // 生成小组ID
            String groupId = generateGroupId(activityId);

            if (groupId != null) {
                if (isGroupExist(groupId)) {
                    // 小组已存在，仅插入表单中输入的其他学生的ID和活动AID
                    insertGroupMember(groupId, memberId, activityId);
                } else {
                    // 小组不存在，先插入操作者本人的SID和活动AID，再插入表单中输入的其他学生的ID和活动AID
                    insertGroup(groupId, studentSid, activityId);
                    insertGroupMember(groupId, memberId, activityId);
                }

                // 小组创建成功，进行后续操作或跳转页面
                // ...
                response.sendRedirect("MyGroup.jsp");
                // 设置成功消息并返回给前端
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"success\": true, \"message\": \"小组创建成功\"}");
            } else {
                // 生成小组ID失败，处理异常情况

                // 设置失败消息并返回给前端
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"success\": false, \"message\": \"生成小组ID失败\"}");
            }
        } else {
            // 学号和电话不匹配，处理异常情况

            // 设置失败消息并返回给前端
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"学号和电话不匹配\"}");
        }
    }

    // 验证学号和电话是否匹配
    private boolean validateStudent(String studentSid, String memberPhone) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isValid = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM Student WHERE SID = ? AND PHONE = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentSid);
            statement.setString(2, memberPhone);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 验证是否存在匹配的学生记录
            isValid = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return isValid;
    }

    // 生成新的小组ID
    private String generateGroupId(String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT GID FROM group WHERE AID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, activityId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            int maxGroupIdSuffix = 0;
            while (resultSet.next()) {
                String groupId = resultSet.getString("GID");
                String[] parts = groupId.split("_");
                if (parts.length > 1) {
                    String suffix = parts[1];
                    int groupIdSuffix = Integer.parseInt(suffix);
                    if (groupIdSuffix > maxGroupIdSuffix) {
                        maxGroupIdSuffix = groupIdSuffix;
                    }
                }
            }

            // 生成新的小组ID
            int newGroupIdSuffix = maxGroupIdSuffix + 1;
            String newGroupId = activityId + "_" + newGroupIdSuffix;

            return newGroupId;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return null;
    }

    // 判断小组是否已存在
    private boolean isGroupExist(String groupId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exist = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM group WHERE GID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, groupId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 判断小组是否已存在
            exist = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return exist;
    }

    // 插入小组信息
    private void insertGroup(String groupId, String studentSid, String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "INSERT INTO group (GID, SID, AID) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, groupId);
            statement.setString(2, studentSid);
            statement.setString(3, activityId);

            // 执行插入操作
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    // 插入小组成员信息
    private void insertGroupMember(String groupId, String memberId, String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "INSERT INTO group (GID, SID, AID) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, groupId);
            statement.setString(2, memberId);
            statement.setString(3, activityId);

            // 执行插入操作
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}
