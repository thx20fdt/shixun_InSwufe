<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/10
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>活动提交情况</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="me.css">
</head>
<body>
<nav class="ui inverted attached segment m-padded-tb-mini">
  <div class="ui container">
    <div class="ui inverted secondary menu">
      <h2 class="ui teal header item">T4_课程管理系统</h2>
      <a href="CourseToughtByMe" class="item"><i class="home icon"></i>首页</a>
      <a href="ClassManage.jsp" class="item"><i class="users icon"></i>管理课程</a>
      <a href="StuScore.jsp" class="item"><i class="clipboard icon"></i>学生成绩</a>
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
              <i  class="users icon" style="margin-right: 5px;"></i>小组
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
            <div class="column">
              <h3 class="ui black header">活动提交情况</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <div class="ui grid">
            <div class="eight wide column">
            </div>
          </div>
          <div class="ui grid">
            <div class="column" style="padding-top: 20px; padding-bottom: 20px;">
              <h4 class="ui dividing header">学生提交情况</h4>
            </div>
          </div>
          <table class="ui celled table">
            <thead>
            <tr>
              <th class="two wide">姓名</th>
              <th class="three wide">学号</th>
              <th class="three wide">组号</th>
              <th class="two wide">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="submit" items="${submitList}">
              <tr>
                <td>${submit.SNAME}</td>
                <td>${submit.SID}</td>
                <td>${submit.GID}</td>
                <td><a href="SubmitionDetail?AID=${submit.AID}&SID=${submit.SID}">查看</a></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- foot content -->
<footer class="ui inverted vertical segment">
  <div class="ui center aligned container">
    <div class="ui inverted section divider">
      <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
</html>
