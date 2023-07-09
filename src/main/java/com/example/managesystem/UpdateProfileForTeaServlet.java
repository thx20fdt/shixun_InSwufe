package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.user.Student;
import com.example.managesystem.user.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "UpdateProfileForTea", value = "/UpdateProfileForTea")
public class UpdateProfileForTeaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求参数中获取要更新的信息
        String gender = request.getParameter("gender");
        String phone = request.getParameter("new_phone");
        HttpSession session = request.getSession();
        String TID = (String) session.getAttribute("id");
        // 根据实际情况进行数据库更新操作
        boolean success = updateProfileForTea(gender, phone, TID);

        // 返回响应结果
        if (success) {
            // 更新成功，获取最新的student对象
            Teacher updatedTeacher = getUpdatedTeacher(TID);


            session.setAttribute("teacher", updatedTeacher); // 将更新后的student对象设置到request中
            session.setAttribute("updateSuccess", true); // 设置updateSuccess为true
            request.getRequestDispatcher("PersonalInfoForTea.jsp").forward(request, response); // 重定向到PersonalInfo.jsp
        } else {
            session.setAttribute("updateSuccess", false); // 设置updateSuccess为false
            request.getRequestDispatcher("PersonalInfoForTea.jsp").forward(request, response); // 更新失败，重定向到PersonalInfo.jsp
        }
    }

    // 根据实际情况实现数据库更新操作
    // 根据实际情况实现数据库更新操作
    private boolean updateProfileForTea(String gender, String phone,String TID) {
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
            StringBuilder sqlBuilder = new StringBuilder("UPDATE Teacher SET GENDER = ?");
            boolean hasChanges = true; // 标记是否有需要更新的字段
            // 添加非空字段到SQL语句和参数中
            if (phone != null && !phone.isEmpty()) {
                sqlBuilder.append(", PHONE = ?");
                hasChanges = true;
            }
            sqlBuilder.append(" WHERE TID = ?");
            statement = connection.prepareStatement(sqlBuilder.toString());

            // 设置参数值
            int parameterIndex = 1;
            statement.setBoolean(parameterIndex++, gender.equals("男")); // 将"男"转换为true，"女"转换为false
            if (phone != null && !phone.isEmpty()) {
                statement.setString(parameterIndex++, phone);
            }

            statement.setString(parameterIndex, TID);

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
            String sql = "SELECT PHONE FROM Teacher WHERE PHONE = ?";
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
    private Teacher getUpdatedTeacher(String TID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM Teacher WHERE TID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, TID);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            if (resultSet.next()) {
                // 创建Student对象并设置属性值
                teacher = new Teacher();
                teacher.setTID(resultSet.getString("TID"));
                teacher.setNAME(resultSet.getString("NAME"));
                teacher.setGENDER(resultSet.getBoolean("GENDER"));
                teacher.setPHONE(resultSet.getString("PHONE"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return teacher;
    }
}
