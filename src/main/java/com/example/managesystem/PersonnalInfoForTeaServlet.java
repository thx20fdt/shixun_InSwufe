package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.user.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "PersonnalInfoForTea", value = "/PersonnalInfoForTea")
public class PersonnalInfoForTeaServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从会话中获取学生的SID
        HttpSession session = request.getSession();
        String TID = (String) session.getAttribute("id");

        // 根据学生SID查询数据库，获取学生的个人信息
        Teacher teacher = getTeacherInfo(TID);

        // 将学生信息存储在请求属性中，以便在JSP中使用
        session.setAttribute("teacher", teacher);

        // 转发到PersonalInfo.jsp显示个人基本信息
        response.sendRedirect("PersonnalInfoForTea.jsp");
    }

    private Teacher getTeacherInfo(String TID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 构造SQL查询语句
            String query = "SELECT TID, NAME, GENDER, PHONE FROM Teacher WHERE TID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, TID);

            // 执行查询语句
            resultSet = statement.executeQuery();

            // 如果查询到结果，则构造Student对象
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTID(resultSet.getString("TID"));
                teacher.setNAME(resultSet.getString("NAME"));
                teacher.setGENDER(resultSet.getBoolean("GENDER"));
                teacher.setPHONE(resultSet.getString("PHONE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常情况
        } finally {
            // 关闭数据库连接和资源
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return teacher;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
