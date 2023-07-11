package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.Class.Class;

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

@WebServlet("/ClassManageServlet")
public class ClassManageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        // 从会话中获取TID
        HttpSession session = request.getSession();
        String teacherId = (String) session.getAttribute("id");

        // 查询班级信息
        List<Class> classList = searchClasses(teacherId);

        // 存储查询结果到请求属性
        session.setAttribute("classList", classList);

        // 转发到ClassManage.jsp显示查询结果
        request.getRequestDispatcher("ClassManage.jsp").forward(request, response);
    }

    private List<Class> searchClasses( String teacherId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Class> classList = new ArrayList<>();

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT CID, CNAME, CLASSTIME " +
                    "FROM Class " +
                    "WHERE TID = ?";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, teacherId);


            // 执行查询操作
            resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                Class classObj = new Class();
                classObj.setCID(resultSet.getString("CID"));
                classObj.setCNAME(resultSet.getString("CNAME"));
                classObj.setCLASSTIME(resultSet.getString("CLASSTIME"));

                classList.add(classObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement对象和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }

        return classList;
    }
}
