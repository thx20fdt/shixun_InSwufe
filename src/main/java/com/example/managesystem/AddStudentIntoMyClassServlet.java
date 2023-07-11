package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AddStudentIntoMyClass")
public class AddStudentIntoMyClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取传递的学生ID（SID）
        String studentId = request.getParameter("SID");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM Student WHERE SID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // 学生存在，可以将学生添加到班级
                String classId = (String) request.getSession().getAttribute("cid"); // 从Session获取班级ID

                // 插入学生ID和班级ID到Student_Class表
                String insertSql = "INSERT INTO Student_Class (SID, CID) VALUES (?, ?)";
                statement = connection.prepareStatement(insertSql);
                statement.setString(1, studentId);
                statement.setString(2, classId);
                statement.executeUpdate();

                response.getWriter().write("添加学生成功！");
            } else {
                // 学生不存在
                response.getWriter().write("没有此学生！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}
