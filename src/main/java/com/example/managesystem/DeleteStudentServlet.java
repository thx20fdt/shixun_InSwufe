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
        // 获取传递的学生ID（SID）和班级ID（CID）
        String studentId = request.getParameter("sid");
        String classId = request.getParameter("cid");

        // 在这里执行删除学生的操作，根据学生ID和班级ID进行数据库操作
        // 假设使用JDBC进行删除操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
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
                response.getWriter().write("success");
            } else {
                response.getWriter().write("failure");
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
