package com.example.managesystem;

import DB.DBUtil;
import user.Student;

import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/PersonalInfoServlet")
public class PersonalInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从会话中获取学生的SID
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");

        // 根据学生SID查询数据库，获取学生的个人信息
        Student student = getStudentInfo(studentSid);

        // 将学生信息存储在请求属性中，以便在JSP中使用
        request.setAttribute("student", student);

        // 转发到PersonalInfo.jsp显示个人基本信息
        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonalInfo.jsp");
        dispatcher.forward(request, response);
    }

    private Student getStudentInfo(String studentSid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 构造SQL查询语句
            String query = "SELECT SID, NAME, GENDER, MAJOR, GRADE, PHONE FROM Student WHERE SID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, studentSid);

            // 执行查询语句
            resultSet = statement.executeQuery();

            // 如果查询到结果，则构造Student对象
            if (resultSet.next()) {
                student = new Student();
                student.setSid(resultSet.getString("SID"));
                student.setName(resultSet.getString("NAME"));
                student.setGender(resultSet.getInt("GENDER"));
                student.setMajor(resultSet.getString("MAJOR"));
                student.setGrade(resultSet.getInt("GRADE"));
                student.setPhone(resultSet.getString("PHONE"));
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

        return student;
    }

}