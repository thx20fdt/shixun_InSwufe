package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.user.Student;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SelectStuNameServlet")
public class SelectStuNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取学生姓名和cid参数值
        String studentName = request.getParameter("studentName");
        HttpSession session = request.getSession();
        String cid = (String)session.getAttribute("cid");

        // 在这里执行查询学生信息的操作，根据学生姓名和cid进行数据库查询
        // 假设使用JDBC进行查询操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT SID, NAME, MAJOR FROM Student WHERE NAME = ? AND SID IN (SELECT SID FROM Student_Class WHERE CID = ?)";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentName);
            statement.setString(2, cid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 创建一个List用于存储查询结果
            List<Student> studentList = new ArrayList<>();

            // 遍历查询结果集，将每个学生信息存储到Student对象中，并添加到studentList中
            while (resultSet.next()) {

                Student student = new Student();
                student.setSid(resultSet.getString("SID"));
                student.setName(resultSet.getString("NAME"));
                student.setMajor(resultSet.getString("MAJOR"));
                studentList.add(student);
            }

            // 将studentList存储在request属性中，以便在JSP页面中使用
            request.setAttribute("studentList", studentList);
            request.setAttribute("cid", cid);

            // 转发到SelectStudent.jsp页面
            request.getRequestDispatcher("SelectStudent.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}
