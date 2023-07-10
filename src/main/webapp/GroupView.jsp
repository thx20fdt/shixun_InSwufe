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
                            <h3 class="ui black header">小组信息管理</h3>
                        </div>
                    </div>
                </div>
                <div class="ui attached segment">
                    <div class="ui grid">
                        <div class="eight wide column">
                            <form action="SearchGroupBySNAME" class="ui form" method="post">
                                <div class="ui action input">
                                    <input type="text" placeholder="学生姓名" name="SNAME">
                                    <button class="ui blue button" type="submit">
                                        <i class="search icon"></i>
                                        查询
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <table class="ui celled table">
                        <thead>
                        <tr>
                            <th class="six wide">小组ID</th>
                            <th class="six wide">小组成员</th>
                            <th class="six wide">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="group" items="${groupList}">
                            <tr>
                                <td>${group.GID}</td>
                                <td>
                                    <c:forEach var="member" items="${group.MEMBERS}" varStatus="memberStatus">
                                        ${member}<c:if test="${not memberStatus.last}">, </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <button class="ui blue button" onclick="location.href=''">添加成员</button>
                                    <button class="ui red button" onclick="location.href=''">删除成员</button>
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
            <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>
        </div>
    </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
</html>
