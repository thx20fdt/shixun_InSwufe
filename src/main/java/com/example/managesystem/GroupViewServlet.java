package com.example.managesystem;

import com.example.managesystem.db.DBUtil;
import com.example.managesystem.group.Group;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GroupViewServlet")
public class GroupViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取传递的AID参数
        String aid = request.getParameter("AID");
        HttpSession session = request.getSession();
        session.setAttribute("AID",aid);

        // 在这里执行查询小组信息的操作，根据AID进行数据库查询
        // 假设使用JDBC进行查询操作，具体实现方式可能会有所不同

        // 假设使用JDBC的示例代码
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();

            // 准备SQL语句
            String sql = "SELECT GID, SID FROM stu_group WHERE AID = ?";

            statement = connection.prepareStatement(sql);

            // 设置参数值
            statement.setString(1, aid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 创建一个Map存储小组成员，key为GID，value为成员姓名列表
            Map<String, List<String>> groupMembers = new HashMap<>();

            // 遍历查询结果集，构建groupMembers
            while (resultSet.next()) {
                String gid = resultSet.getString("GID");
                String sid = resultSet.getString("SID");

                // 根据SID查询学生姓名
                String studentName = getStudentName(connection, sid);

                // 如果该小组的成员列表已存在，则添加成员姓名
                if (groupMembers.containsKey(gid)) {
                    groupMembers.get(gid).add(studentName);
                } else {
                    // 如果该小组的成员列表还不存在，则创建新的成员列表并添加成员姓名
                    List<String> members = new ArrayList<>();
                    members.add(studentName);
                    groupMembers.put(gid, members);
                }
            }

            // 创建一个List存储Group对象
            List<Group> groupList = new ArrayList<>();

            // 构建Group对象并添加到groupList中
            for (Map.Entry<String, List<String>> entry : groupMembers.entrySet()) {
                String gid = entry.getKey();
                List<String> members = entry.getValue();

                Group group = new Group();
                group.setGID(gid);
                group.setMembers(members);

                groupList.add(group);
            }

            // 将groupList存储在request属性中，以便在JSP页面中使用
            request.setAttribute("groupList", groupList);

            // 转发到GroupView.jsp页面
            request.getRequestDispatcher("GroupView.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接、Statement和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
            DBUtil.closeConnection(connection);
        }
    }

    private String getStudentName(Connection connection, String sid) throws SQLException {
        String studentName = "";

        // 准备SQL语句
        String sql = "SELECT NAME FROM Student WHERE SID = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sid);

            // 执行查询操作
            resultSet = statement.executeQuery();

            // 提取学生姓名
            if (resultSet.next()) {
                studentName = resultSet.getString("NAME");
            }
        } finally {
            // 关闭Statement和ResultSet对象
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(statement);
        }

        return studentName;
    }
}
