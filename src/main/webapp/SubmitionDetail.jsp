<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/10
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>我的课程</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="me.css">
</head>
<body>
<nav class="ui inverted attached segment m-padded-tb-mini">
  <div class="ui container">
    <div class="ui inverted secondary menu">
      <h2 class="ui teal header item">T4_课程管理系统</h2>
      <a href="CourseToughtByMe" class="item"><i class="home icon"></i>首页</a>
      <a href="ClassManage.jsp" class="item"><i class="users icon"></i>我教的课</a>
      <a href="#" class="item"><i class="clipboard icon"></i>####</a>
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
              <h3 class="ui black header">${submition.ANAME}</h3>
              <h3 class="ui black header">得分：${submition.SCORE}</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <p>${submition.ACONTENT}</p>
          <div class="ui form reply" id="gradeForm">
            <div class="field">
              <textarea placeholder="学生的作答" disabled>${submition.CONTENT}</textarea>
            </div>
            <form action="UpdateScore" method="post">
              <div class="field">
              <label>成绩</label>
                <input type="text" placeholder="输入学生的成绩" id="gradeInput" name="score">
                <input type="hidden" name="AID" value="${submition.AID}">
                <input type="hidden" name="SID" value="${submition.SID}">
            </div>
              <button class="ui blue labeled submit icon button" type="submit">
                <i class="icon edit"></i> 提交成绩
              </button>
            </form>

            <div class="ui error message"></div>
          </div>
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
<script>
  $(document).ready(function() {
    $('#gradeForm').form({
      fields: {
        grade: {
          identifier  : 'grade',
          rules: [
            {
              type   : 'empty',
              prompt : '请输入成绩'
            },
            {
              type   : 'integer[0..100]',
              prompt : '成绩应在0-100之间'
            }
          ]
        },
      }
    });
  });
</script>
</html>
