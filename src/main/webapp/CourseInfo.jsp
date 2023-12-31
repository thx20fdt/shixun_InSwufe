<%@ page import="com.example.managesystem.course.course" %>
<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/7
  Time: 17:27
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
            <a href="MyCourse" class="item"><i class="home icon"></i>首页</a>
            <div class="ui dropdown item">
                <i class="users icon"></i>我的课程
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item" href="MyCourseInfoServlet">课程列表</a>
                    <a class="item" href="SelectCourse.jsp">选择课程</a>
                </div>
            </div>
            <a href="MyScoreServlet" class="item"><i class="clipboard icon"></i>查看成绩</a>
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
                        <div class="six wide column">
                            <h3 class="ui black header">${course.CNAME}</h3>
                        </div>
                    </div>
                </div>
                <div class="ui attached segment">
                    <div class="ui grid">
                        <div class="column" style="padding-top: 20px; padding-bottom: 20px;">
                            <h4 class="ui dividing header">教师：${course.TNAME}  ${course.CLASSTIME}</h4>
                        </div>
                    </div>
                    <div class="ui list">
                        <c:forEach var="activity" items="${acs}">
                            <div class="ui items">
                                <div class="item" onclick="goTask('YourServletURL')" onkeydown="if(event.keyCode == 13){goTask('YourServletURL');}">
                                    <div class="content">
                                        <a href="PrepareActivityServlet?AID=${activity.AID}&ANAME=${activity.ANAME}" class="header" style="color: black;">${activity.ANAME}</a>
                                        <div class="ui small feed">
                                            <div class="event">
                                                <div class="content">
                                                    <div class="summary">
                                                        <i class="clock outline icon"></i>
                                                        <span id="deadline-${activity.AID}" style="color: grey;">截止日期: ${activity.ENDTIME}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script>
                                //获取活动的截止日期
                                var endTime = new Date("${activity.ENDTIME}");
                                //获取当前日期
                                var now = new Date();
                                //比较日期
                                if(endTime < now) {
                                    document.getElementById("deadline-${activity.AID}").innerHTML = "已截止";
                                    document.getElementById("deadline-${activity.AID}").style.color = "red";
                                }
                            </script>
                        </c:forEach>
                        <!-- 更多作业 -->
                    </div>
                    <!-- 更多课程 -->
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
    function goTask(url) {
        window.location.href = url;
    }
</script>
<script>
    $(document).ready(function() {
        $('.ui.dropdown').dropdown();
    });
</script>

</html>