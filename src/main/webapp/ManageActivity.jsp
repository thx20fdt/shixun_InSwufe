<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/7/10
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>管理您的班级活动</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="./me.css">
</head>
<body>
<nav class="ui inverted attached segment m-padded-tb-mini">
  <div class="ui container">
    <div class="ui inverted secondary menu">
      <h2 class="ui teal header item">T4_课程管理系统</h2>
      <a href="CourseToughtByMe" class="item"><i class="home icon"></i>首页</a>
      <a href="ClassManage.jsp" class="item"><i class="keyboard icon"></i>管理班级</a>
      <a href="TeaGrade.html" class="item"><i class="clipboard icon"></i>学生成绩</a>
      <a href="PersonalInfoForTeaServlet" class="item"><i class="id card icon"></i>个人信息</a>
      <div class="right item">
        <div class="ui left icon inverted input">
          <input type="text" placeholder="搜索……">
          <i class="search link icon"></i>
        </div>
      </div>
    </div>
  </div>
</nav>

<!--mid content-->


<div class="m-padded-tb-larger m-content">
  <div class="ui container">
    <div class="ui grid">
      <!-- 这是左边的列表-->
      <div class="three wide column">
        <div class="ui segment">
          <div class="ui vertical fluid tabular menu">
            <a class="item">
              <i class="book icon" style="margin-right: 5px;"></i>课程
            </a>
            <a class="item">
              <i class="star icon" style="margin-right: 5px;"></i>收藏
            </a>
            <a class="item">
              <i class="lightbulb icon" style="margin-right: 5px;"></i>创作
            </a>
            <a href="StuGroup.html" class="item">
              <i class="users icon" style="margin-right: 5px;"></i>小组
            </a>
            <a class="item">
              <i class="pencil alternate icon" style="margin-right: 5px;"></i>笔记
            </a>
          </div>
        </div>
      </div>
      <!-- 这是右边的列表-->
      <div class="thirteen wide column">
        <div class="ui top attached segment">
          <div class="ui middle aligned four column grid">
            <div class="six wide column">
              <h3 class="ui black header">活动信息管理</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <div class="ui grid">
            <div class="eight wide column">
              <form action="#" class="ui form" method="post">
                <div class="ui action input">
                  <input type="text" placeholder="活动名称" name="">
                  <button class="ui blue button" type="submit">
                    <i class="search icon"></i>
                    查询
                  </button>
                </div>
              </form>
            </div>
            <div class="four wide column">
              <div class="ui right floated">
                <button class="ui green button" onclick="location.href='#'">
                  <i class="plus icon"></i>
                  添加新的活动
                </button>
              </div>
            </div>
          </div>
          <table class="ui celled table">
            <thead>
            <tr>
              <th class="six wide">活动名称</th>
              <th class="six wide">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="activity" items="${activityList}">
              <tr>
                <td>${activity.ANAME}</td>
                <td>
                  <button class="ui blue button" onclick="location.href='GroupViewServlet?AID=${activity.AID}'">查看分组</button>
                  <button class="ui red button" onclick="deleteActivity('${activity.AID}')">删除活动</button>

                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>

        </div>
      </div>
      <!-- 这是右边部分结束  -->
    </div>
  </div>
</div>



<!-- foot content -->
<footer class="ui inverted vertical segment">
  <div class="ui center aligned container">
    <div class="ui inverted section divider">
      <p class="m-text-thin m-opacity-mini">Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script>
  function deleteActivity(aid) {
    if (confirm("确认删除该活动吗？")) {
      // 发送AJAX请求到后端进行活动删除操作
      $.ajax({
        type: "POST",
        url: "DeleteActivityServlet",
        data: {aid: aid},
        success: function(response) {
          // 根据删除结果进行相应的处理
          if (response === "success") {
            alert("活动删除成功！");
            // 刷新页面或执行其他操作
            location.reload();
          } else {
            alert("活动删除失败！");
          }
        },
        error: function() {
          alert("请求失败，请重试！");
        }
      });
    }
  }
</script>


</html>
