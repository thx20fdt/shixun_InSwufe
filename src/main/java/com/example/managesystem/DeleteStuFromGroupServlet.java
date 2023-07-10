package com.example.managesystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.managesystem.db.DBUtil;

@WebServlet("/DeleteStuFromGroupServlet")
public class DeleteStuFromGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String AID = request.getParameter("AID");
        String GID = request.getParameter("GID");
        String memberId = request.getParameter("memberId");

        // 检查学生是否在该组中
        if (!isStudentInGroup(memberId, GID)) {
            response.getWriter().println("该学生未在此组中");
            return;
        }

        // 在stu_group表中删除对应的行数据
        if (removeStudentFromGroup(memberId, GID)) {
            response.getWriter().println("成功将学生从该组中移出");
        } else {
            response.getWriter().println("删除学生失败");
        }
    }

    // 检查学生是否在该组中
    private boolean isStudentInGroup(String memberId, String GID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isInGroup = false;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "SELECT * FROM stu_group WHERE SID = ? AND GID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberId);
            stmt.setString(2, GID);
            isInGroup = stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            DBUtil.closeConnection(conn);
        }

        return isInGroup;
    }

    // 从stu_group表中删除学生对应的行数据
    private boolean removeStudentFromGroup(String memberId, String GID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DBUtil.getConnection(); // 获取数据库连接
            String sql = "DELETE FROM stu_group WHERE SID = ? AND GID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberId);
            stmt.setString(2, GID);
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
