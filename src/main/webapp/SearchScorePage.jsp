<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>我的课程</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="./me.css">
</head>
<body>
<nav class="ui inverted attached segment m-padded-tb-mini">
  <div class="ui container">
    <div class="ui inverted secondary menu">
      <h2 class="ui teal header item">T4_课程管理系统</h2>
      <a href="MyCourse" class="item"><i class="home icon"></i>首页</a>
      <a href="MyCourseInfoServlet" class="item"><i class="users icon"></i>我的课程</a>
      <a href="MyScoreServlet" class="item"><i class="clipboard icon"></i>我的成绩</a>
      <a href="PersonalInfoServlet" class="item"><i class="id card icon"></i>个人信息</a>
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
            <a href="MyGroupServlet" class="item">
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
            <div class="column">
              <h3 class="ui black header">我的成绩</h3>
            </div>
          </div>
        </div>
        <div class="six wide column">
          <form action="SearchScoreServlet" class="ui form" method="post">
            <div class="ui action input">
              <input type="text" placeholder="课程名称" name="CNAME">
              <button class="ui blue button" type="submit">
                <i class="search icon"></i>
                查询
              </button>
            </div>
          </form>
        </div>

        <div class="ui grid">
          <div class="column" style="padding-top: 20px; padding-bottom: 20px;">
            <h4 class="ui dividing header">已选课程成绩</h4>
          </div>
        </div>
        <table class="ui celled table">
          <thead>
          <tr>
            <th>活动名称</th>
            <th>活动分数</th>
          </tr>
          <c:forEach var="score" items="${sessionScope.scoreList}">
            <tr>
              <td>${score.ANAME}</td>
              <td>${score.SCORE}</td>
            </tr>
          </c:forEach>
          </thead>
          <tbody>
          <!-- 更多课程 -->
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
      <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
</html>