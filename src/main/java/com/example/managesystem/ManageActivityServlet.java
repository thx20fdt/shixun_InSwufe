package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.activity.Activity;

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

@WebServlet("/ManageActivityServlet")
public class ManageActivityServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取传递的班级ID（CID）
        String cid = request.getParameter("CID");

        // 在这里执行查询活动信息的操作，根据班级ID进行数据库查询
        // 假设使用JDBC进行查询操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT ANAME,AID FROM Activity WHERE CID = ?";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, cid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 创建一个List用于存储查询结果
            List<Activity> activityList = new ArrayList<>();

            // 遍历查询结果集，将每个活动名称存储到Activity对象中，并添加到activityList中
            while (resultSet.next()) {

                Activity activity = new Activity();
                activity.setANAME(resultSet.getString("ANAME"));
                activity.setAID(resultSet.getString("AID"));
                activityList.add(activity);
            }

            // 将activityList存储在request属性中，以便在JSP页面中使用
            request.setAttribute("activityList", activityList);

            // 转发到ManageActivity.jsp页面
            request.getRequestDispatcher("ManageActivity.jsp").forward(request, response);
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

