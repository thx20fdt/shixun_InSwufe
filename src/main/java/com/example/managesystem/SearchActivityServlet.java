package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.activity.activity;

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

@WebServlet("/SearchActivityServlet")
public class SearchActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cid = request.getParameter("cid");
        String activityName = request.getParameter("activityName");
        HttpSession session = request.getSession();
        // 在这里执行查询活动的操作，根据活动名称和班级ID进行数据库查询
        // 假设使用JDBC进行查询操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT * FROM Activity WHERE CID = ? AND ANAME = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, cid);
            statement.setString(2, activityName);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 存储查询结果
            List<activity> activityList = new ArrayList<>();

            while (resultSet.next()) {
                activity activity = new activity();
                activity.setAID(resultSet.getString("AID"));
                activity.setANAME(resultSet.getString("ANAME"));
                // 其他属性的设置...

                activityList.add(activity);
            }
            if (activityList.isEmpty()) {
                String message = "本班中没有您所要查询的活动";
                request.setAttribute("message", message);
            }
            else {
            // 将查询结果存储到request的属性中

            session.setAttribute("activityList", activityList);
            session.setAttribute("cid", cid);
            }
            // 转发到ManageActivity.jsp页面
            request.getRequestDispatcher("ManageActivity.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}
