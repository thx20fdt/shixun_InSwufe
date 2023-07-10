package com.example.managesystem;

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
import com.example.managesystem.db.DBUtil;

@WebServlet("/AddStudentInGroupServlet")
public class AddStudentInGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String AID = request.getParameter("AID");
        String GID = request.getParameter("GID");
        String memberName = request.getParameter("memberName");
        String memberId = request.getParameter("memberId");

// 检查学生姓名和学号是否匹配
        if (!isStudentMatch(memberId, memberName)) {
            response.getWriter().println("您输入的学生姓名和学号不匹配");
            return;
        }
        // 检查学生是否选修了该门课程
        if (!isStudentInClass(memberId, AID)) {
            response.getWriter().println("该学生未选修本门课程");
            return;
        }

        // 检查学生是否已经在该小组中
        if (isStudentInGroup(memberId, GID)) {
            response.getWriter().println("该学生已经在此小组中");
            return;
        }

        // 检查学生是否已经加入其他小组，若有，则移除
        String previousGID = getStudentGroup(memberId);
        if (previousGID != null) {
            removeStudentFromGroup(memberId, previousGID);
            response.getWriter().println("您已为该学生重新分配小组");
        }

        // 将学生加入新小组
        if (addStudentToGroup(memberId, GID, AID)) {
            response.getWriter().println("该学生成功加入此小组");
        } else {
            response.getWriter().println("加入小组失败");
        }
    }

    // 检查学生是否选修了该门课程
    private boolean isStudentInClass(String SID, String AID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isInClass = false;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
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

    // 检查学生是否已经在该小组中
    private boolean isStudentInGroup(String SID, String GID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isInGroup = false;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "SELECT * FROM stu_group WHERE SID = ? AND GID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, GID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                isInGroup = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return isInGroup;
    }

    // 获取学生所在的小组
    private String getStudentGroup(String SID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String GID = null;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "SELECT GID FROM stu_group WHERE SID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                GID = rs.getString("GID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return GID;
    }
    // 检查学生姓名和学号是否匹配
    private boolean isStudentMatch(String SID, String studentName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isMatch = false;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
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


    // 将学生从小组中移除
    private void removeStudentFromGroup(String SID, String GID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "DELETE FROM stu_group WHERE SID = ? AND GID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, SID);
            stmt.setString(2, GID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }
    }

    // 将学生加入小组
    private boolean addStudentToGroup(String SID, String GID, String AID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
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
