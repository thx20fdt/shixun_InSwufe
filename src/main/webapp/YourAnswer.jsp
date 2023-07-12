<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/11
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html><!DOCTYPE html>
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
            <a href="MyCourseInfoServlet" class="item"><i class="users icon"></i>我的课程</a>
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
                        <div class="column">
                            <h3 class="ui black header">${submit.ANAME}</h3>
                            <p class="ui small grey text">作答时间从${activity.BEGINTIME}至${activity.ENDTIME}</p>
                        </div>
                    </div>
                </div>
                <div class="ui attached segment">
                    <p>${activity.ACONTENT}</p>
                    <form action="ActivitySubmit" method="post">
                        <div class="ui form reply">
                            <input type="hidden" id="hiddenField" name="AID" value="${activity.AID}">
                            <div class="field">
                                <textarea placeholder="你提交的内容" disabled>${submit.CONTENT}</textarea>
                            </div>
                        </div>
                    </form>
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
</script>