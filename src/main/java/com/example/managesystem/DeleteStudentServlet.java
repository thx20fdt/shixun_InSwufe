package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 获取传递的学生ID（SID）和班级ID（CID）
        String studentId = request.getParameter("sid");
        String classId = request.getParameter("cid");




        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "DELETE FROM Student_Class WHERE SID = ? AND CID = ?";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentId);
            statement.setString(2, classId);

            // 执行删除操作
            int rowsAffected = statement.executeUpdate();

            // 根据删除结果向客户端返回相应的信息
            if (rowsAffected > 0) {
                response.getWriter().write("删除成功");
            } else {
                response.getWriter().write("删除失败，该学生未在该班中");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}
