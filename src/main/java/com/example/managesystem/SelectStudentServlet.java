package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.user.Student;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SelectStudentServlet")
public class SelectStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 获取传递的CID参数
        String cid = request.getParameter("CID");

        // 调用方法获取指定班级的学生信息，返回一个学生列表
        List<Student> studentList = getStudentListByClass(cid);

        // 将学生列表存储到请求属性中

        HttpSession session = request.getSession();
        session.setAttribute("cid",cid);
        session.setAttribute("studentList", studentList);

        // 转发到SelectStudent.jsp页面
        request.getRequestDispatcher("SelectStudent.jsp").forward(request, response);
    }

    // 根据班级CID获取学生列表的方法
    private List<Student> getStudentListByClass(String cid) {
        List<Student> studentList = new ArrayList<>();

        // 在这里执行数据库查询操作，根据CID获取学生列表
        // 假设使用JDBC进行查询操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT s.SID, s.NAME, s.MAJOR " +
                    "FROM Student s " +
                    "JOIN Student_Class sc ON s.SID = sc.SID " +
                    "WHERE sc.CID = ?";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, cid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                // 创建学生对象
                Student student = new Student();
                student.setSid(resultSet.getString("SID"));
                student.setName(resultSet.getString("NAME"));
                student.setMajor(resultSet.getString("MAJOR"));

                // 将学生对象添加到学生列表中
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return studentList;
    }
}
