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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取表单数据
        String memberId = request.getParameter("memberId");
        String memberPhone = request.getParameter("memberPhone");
        String activityId = request.getParameter("AID");
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");

        // 验证学号和电话是否匹配
        // 验证学号和电话是否匹配
        if (validateStudent(memberId, memberPhone)) {
            // 判断该门活动是否可创建小组
            if (!isGroupCreatable(activityId)) {
                // 设置失败消息并返回给前端，该活动不可创建小组
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"success\": false, \"message\": \"该活动不可创建小组\"}");
                return;
            }

            // 判断学生是否选了该门活动对应的课程
            if (isStudentEnrolled(memberId, activityId)) {
                // 判断学生是否已经在操作者的小组中
                if (isStudentInOperatorGroup(memberId, studentSid, activityId)) {
                    // 设置失败消息并返回给前端，学生已经在操作者的小组中
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"success\": false, \"message\": \"学生已经在您的小组中\"}");
                } else {
                    // 判断表单中输入的其他学生在此活动是否有小组
                    if (hasGroup(activityId, memberId)) {
                        // 操作者本人此活动有小组，不进行任何操作
                        if (hasGroup(activityId, studentSid)) {
                            // 设置失败消息并返回给前端，学生已有其他小组
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{\"success\": false, \"message\": \"学生已加入其他小组\"}");
                        } else {
                            // 操作者本人此活动无小组，生成新的小组号
                            String groupId = generateGroupId(activityId);

                            // 插入操作者本人的SID和活动AID
                            insertGroup(groupId, studentSid, activityId);

                            // 设置成功消息并返回给前端
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{\"success\": true, \"message\": \"小组创建成功\"}");
                        }
                    } else {
                        if (hasGroup(activityId, studentSid)) {
                            // 操作者本人此活动有小组，将输入的学生插入到该组中
                            String groupId = findGroupByStudent(activityId, studentSid);
                            insertGroupMember(groupId, memberId, activityId);

                            // 设置成功消息并返回给前端
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{\"success\": true, \"message\": \"邀请成员成功\"}");
                        } else {
                            // 操作者本人此活动无小组，生成新的小组号
                            String groupId = generateGroupId(activityId);

                            // 插入操作者本人的SID和活动AID
                            insertGroup(groupId, studentSid, activityId);
                            insertGroupMember(groupId, memberId, activityId);

                            // 设置成功消息并返回给前端
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{\"success\": true, \"message\": \"小组创建并邀请成员成功\"}");
                        }
                    }
                }
            } else {
                // 设置失败消息并返回给前端，学生未选该门课程
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"success\": false, \"message\": \"学生未选该门课程\"}");
            }
        } else {
            // 学号和电话不匹配，处理异常情况

            // 设置失败消息并返回给前端
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"success\": false, \"message\": \"学号和电话不匹配\"}");
        }


    }




    // 判断学生在指定活动下是否有小组
    // 判断学生在指定活动下是否有小组
    // 判断学生在指定活动下是否有小组
    private boolean hasGroup(String activityId, String memberId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean hasGroup = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM stu_group WHERE AID = ? AND SID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, activityId);
            statement.setString(2, memberId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 判断学生是否有小组
            hasGroup = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return hasGroup;
    }
    private boolean isGroupCreatable(String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isCreatable = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT type FROM Activity WHERE AID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, activityId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 获取活动类型
            if (resultSet.next()) {
                boolean type = resultSet.getBoolean("type");
                isCreatable = type;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return isCreatable;
    }



    private String findGroupByStudent(String activityId, String studentSid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String groupId = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT GID FROM stu_group WHERE AID = ? AND SID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, activityId);
            statement.setString(2, studentSid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 获取小组ID
            if (resultSet.next()) {
                groupId = resultSet.getString("GID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return groupId;
    }
    private boolean isStudentInOperatorGroup(String memberId, String operatorSid, String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isInGroup = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM stu_group WHERE AID = ? AND SID = ? AND GID IN (SELECT GID FROM stu_group WHERE SID = ? AND AID = ?)";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, activityId);
            statement.setString(2, memberId);
            statement.setString(3, operatorSid);
            statement.setString(4, activityId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 判断学生是否在操作者的小组中
            isInGroup = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return isInGroup;
    }



    private boolean isStudentEnrolled(String studentId, String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isEnrolled = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM Student_Class WHERE SID = ? AND CID IN (SELECT CID FROM Activity WHERE AID = ?)";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentId);
            statement.setString(2, activityId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 验证学生是否选了该门课程
            isEnrolled = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return isEnrolled;
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
            String sql = "SELECT GID FROM stu_group WHERE AID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, activityId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            int maxGroupIdSuffix = 0;
            if (resultSet.next()) {
                String groupId = resultSet.getString("GID");
                String[] parts = groupId.split("_");
                if (parts.length > 1) {
                    String suffix = parts[2];
                    int groupIdSuffix = Integer.parseInt(suffix);
                    if (groupIdSuffix > maxGroupIdSuffix) {
                        maxGroupIdSuffix = groupIdSuffix;
                    }
                }
                while (resultSet.next()) {
                    groupId = resultSet.getString("GID");
                    parts = groupId.split("_");
                    if (parts.length > 1) {
                        String suffix = parts[2];
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
            }

            else {
                String newGroupId = activityId + "_" + 1;

                return newGroupId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        throw new RuntimeException("无法生成新的小组ID");
    }



    // 插入小组信息
    private void insertGroup(String groupId, String studentSid, String activityId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "INSERT INTO stu_group (GID, SID, AID) VALUES (?, ?, ?)";
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
            String sql = "INSERT INTO stu_group (GID, SID, AID) VALUES (?, ?, ?)";
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
