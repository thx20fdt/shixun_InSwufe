package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddGroupServlet")
public class AddGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取传递的参数
        String memberName = request.getParameter("memberName");
        String memberId = request.getParameter("memberId");
        String AID = request.getParameter("AID");

        // 检查学生姓名和学号是否匹配
        if (!isStudentMatch(memberId, memberName)) {
            response.getWriter().write("该学生学号和姓名不匹配");
            return;
        }

        // 检查学生是否在班级中
        if (!isStudentInClass(memberId, AID)) {
            response.getWriter().write("该学生未在您的班级中");
            return;
        }

        // 检查学生是否已有小组
        if (hasGroup(memberId, AID)) {
            // 删除学生在该活动中的小组记录
            deleteStudentGroup(memberId, AID);
        }

        // 获取新的GID
        String newGID = generateGroupId(AID);

        // 创建新小组记录
        if (createGroup(memberId, newGID, AID)) {
            response.getWriter().write("新增小组成功");
        } else {
            response.getWriter().write("新增小组失败");
        }
    }

    // 检查学生姓名和学号是否匹配
    private boolean isStudentMatch(String SID, String studentName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isMatch = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Student WHERE SID = ? AND NAME = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, studentName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                isMatch = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return isMatch;
    }

    // 检查学生是否在班级中
    private boolean isStudentInClass(String SID, String AID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isInClass = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM Student_Class WHERE SID = ? AND CID = (SELECT CID FROM Activity WHERE AID = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, AID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                isInClass = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return isInClass;
    }

    // 检查学生是否已有小组
    private boolean hasGroup(String SID, String AID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean hasGroup = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM stu_group WHERE SID = ? AND AID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, AID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                hasGroup = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return hasGroup;
    }

    // 删除学生在该活动中的小组记录
    private void deleteStudentGroup(String SID, String AID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM stu_group WHERE SID = ? AND AID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, AID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }

    // 获取新的GID
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
            else{
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

    // 创建新小组记录
    private boolean createGroup(String SID, String GID, String AID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO stu_group (SID, GID, AID) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, GID);
            stmt.setString(3, AID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return success;
    }
}
