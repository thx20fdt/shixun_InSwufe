<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理您的教学班</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <link rel="stylesheet" href="./me.css">
</head>
<body>
<jsp:include page="Tea.jsp" />

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
                        <a class="item">
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
                            <h3 class="ui black header">课程信息管理</h3>
                        </div>
                    </div>
                </div>
                <div class="ui attached segment">
                    <table class="ui celled table">
                        <thead>
                        <tr>
                            <th class="two wide">课程号</th>
                            <th class="three wide">课程名称</th>
                            <th class="four wide">教学班级</th>
                            <th class="three wide">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty sessionScope.classList}">
                            <c:forEach var="classObj" items="${sessionScope.classList}">
                                <tr>
                                    <td>${classObj.CID}</td>
                                    <td>${classObj.CNAME}</td>
                                    <td>${classObj.CLASSTIME}</td>
                                    <td>
                                        <div style="display: flex; flex-direction: column; align-items: center; justify-content: center;">
                                            <button class="ui blue button" style="margin-bottom: 5px;width:140px" onclick="location.href='SelectStudentServlet?CID=${classObj.CID}'">查看本班学生</button>
                                            <button class="ui green button" style="width:140px"onclick="location.href='ManageActivityServlet?CID=${classObj.CID}'">管理活动和分组</button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

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
