package com.example.managesystem;

import com.example.managesystem.db.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/DeleteActivityServlet")
public class DeleteActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取传递的活动ID（AID）
        String activityId = request.getParameter("aid");

        // 在这里执行删除活动的操作，根据活动ID进行数据库操作
        // 假设使用JDBC进行删除操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 开启事务
            connection.setAutoCommit(false);

            // 删除Activity表中的记录
            String deleteActivitySql = "DELETE FROM Activity WHERE AID = ?";
            statement = connection.prepareStatement(deleteActivitySql);
            statement.setString(1, activityId);
            statement.executeUpdate();

            // 删除Activity_Submit表中的记录
            String deleteActivitySubmitSql = "DELETE FROM Activity_Submit WHERE AID = ?";
            statement = connection.prepareStatement(deleteActivitySubmitSql);
            statement.setString(1, activityId);
            statement.executeUpdate();

            // 删除stu_group表中的记录
            String deleteStuGroupSql = "DELETE FROM stu_group WHERE AID = ?";
            statement = connection.prepareStatement(deleteStuGroupSql);
            statement.setString(1, activityId);
            statement.executeUpdate();

            // 提交事务
            connection.commit();

            // 返回删除成功的响应
            response.getWriter().write("success");
        } catch (SQLException e) {
            // 发生异常时回滚事务
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            // 返回删除失败的响应
            response.getWriter().write("failure");
        } finally {
            // 关闭连接和Statement对象
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }
}
