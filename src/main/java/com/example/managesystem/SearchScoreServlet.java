package com.example.managesystem;


import com.example.managesystem.db.DBUtil;
import com.example.managesystem.score.Score;

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

@WebServlet("/SearchScoreServlet")
public class SearchScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取要查询的课程名称
        String courseName = request.getParameter("CNAME");
        HttpSession session = request.getSession();
        String studentSid = (String) session.getAttribute("id");

        // 执行查询操作
        List<Score> scoreList = getScoresByCourseName(courseName,studentSid);


        session.setAttribute("scoreList", scoreList);
        request.setAttribute("CNAME",courseName);

        // 转发到显示成绩的页面
        request.getRequestDispatcher("SearchScorePage.jsp").forward(request, response);
    }

    private List<Score> getScoresByCourseName(String courseName,String studentSid) {
        List<Score> scoreList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT a.ANAME, s.SCORE " +
                    "FROM Activity_Submit s " +
                    "JOIN Activity a ON s.AID = a.AID " +
                    "JOIN Class c ON a.CID = c.CID " +
                    "WHERE c.CNAME = ? AND s.SID = ?";
            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, courseName);
            statement.setString(2, studentSid);


            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                Score score = new Score();
                score.setANAME(resultSet.getString("ANAME"));
                score.setSCORE(resultSet.getDouble("SCORE"));
                scoreList.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return scoreList;
    }
}
