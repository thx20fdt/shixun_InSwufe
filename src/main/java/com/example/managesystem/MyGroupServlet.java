package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.score.Score;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyGroupServlet")
public class MyGroupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");

        List<Score> scorelist = getStudentActivity(studentSid); // 从数据库中获取学生的成绩列表
        session.setAttribute("scoreList", scorelist);

        request.getRequestDispatcher("MyGroup.jsp").forward(request, response); // 转发到 MyScores.jsp 显示成绩页面
    }

    // 根据学生ID从数据库中获取学生的成绩列表
    // 在 MyScoresServlet 类中的 getStudentScores 方法中添加以下代码
    private List<Score> getStudentActivity(String studentId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Score> scorelist = new ArrayList<>();

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT c.CNAME, a.ANAME " +
                    "FROM Student_Class sc " +
                    "JOIN Class c ON sc.CID = c.CID " +
                    "JOIN Activity a ON c.CID = a.CID"+
                    "WHERE sc.SID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, studentId);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                Score score = new Score();
                score.setCNAME(resultSet.getString("CNAME"));
                score.setANAME(resultSet.getString("ANAME"));

                scorelist.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return scorelist;
    }

}
