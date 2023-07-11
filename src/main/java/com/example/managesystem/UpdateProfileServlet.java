package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.user.Student;


import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 从请求参数中获取要更新的信息
        String gender = request.getParameter("gender");
        String phone = request.getParameter("new_phone");
        String major = request.getParameter("new_major");
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");
        // 根据实际情况进行数据库更新操作
        boolean success = updateProfile(gender, phone, major, studentSid);

        // 返回响应结果
        if (success) {
            // 更新成功，获取最新的student对象
            Student updatedStudent = getUpdatedStudent(studentSid);


            session.setAttribute("student", updatedStudent); // 将更新后的student对象设置到request中
            response.getWriter().println("修改个人信息成功");

        } else {
            response.getWriter().println("修改个人信息失败，您所输的电话号码已被注册");
        }
    }

    // 根据实际情况实现数据库更新操作
    // 根据实际情况实现数据库更新操作
    private boolean updateProfile(String gender, String phone, String major, String studentSid) {
        // 在此处编写更新数据库的代码
        Connection connection = null;
        PreparedStatement statement = null;
        boolean success = false;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 查询数据库中是否存在相同的电话号码
            String existingPhone = getExistingPhone(connection, phone);
            if (existingPhone != null) {
                // 存在相同的电话号码，更新失败
                return false;
            }

            // 准备SQL语句
            StringBuilder sqlBuilder = new StringBuilder("UPDATE Student SET GENDER = ?");
            boolean hasChanges = true; // 标记是否有需要更新的字段

            // 添加非空字段到SQL语句和参数中
            if (phone != null && !phone.isEmpty()) {
                sqlBuilder.append(", PHONE = ?");
                hasChanges = true;
            }
            if (major != null && !major.isEmpty()) {
                sqlBuilder.append(", MAJOR = ?");
                hasChanges = true;
            }
            sqlBuilder.append(" WHERE SID = ?");
            statement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数值
            int parameterIndex = 1;
            statement.setBoolean(parameterIndex++, gender.equals("男")); // 将"男"转换为true，"女"转换为false
            if (phone != null && !phone.isEmpty()) {
                statement.setString(parameterIndex++, phone);
            }
            if (major != null && !major.isEmpty()) {
                statement.setString(parameterIndex++, major);
            }
            statement.setString(parameterIndex, studentSid); // studentSid为之前获取的学号

            // 执行更新操作
            int rowsAffected = statement.executeUpdate();

            // 根据更新影响的行数判断更新是否成功
            success = (rowsAffected > 0 && hasChanges);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return success;
    }


    // 查询数据库中是否存在相同的电话号码
    private String getExistingPhone(Connection connection, String phone) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String existingPhone = null;

        try {
            // 准备SQL语句
            String sql = "SELECT PHONE FROM Student WHERE PHONE = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, phone);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            if (resultSet.next()) {
                existingPhone = resultSet.getString("phone");
            }
        } finally {
            // 关闭Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
        }

        return existingPhone;
    }


    // 获取更新后的最新student对象
    private Student getUpdatedStudent(String studentSid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM Student WHERE SID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentSid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            if (resultSet.next()) {
                // 创建Student对象并设置属性值
                student = new Student();
                student.setSid(resultSet.getString("sid"));
                student.setName(resultSet.getString("name"));
                student.setGender(resultSet.getBoolean("gender"));
                student.setPhone(resultSet.getString("phone"));
                student.setMajor(resultSet.getString("major"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return student;
    }
}
