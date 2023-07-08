<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/8
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>选择课程</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="me.css">
</head>
<body>
<nav class="ui inverted attached segment m-padded-tb-mini">
  <div class="ui container">
    <div class="ui inverted secondary menu">
      <h2 class="ui teal header item">T4_课程管理系统</h2>
      <a href="IndexStu.jsp" class="item"><i class="home icon"></i>首页</a>
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
            <div class="column">
              <h3 class="ui black header">课程列表</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <form action="AddIntoMyClassServlet" class="ui form" method="post">
            <table class="ui celled table">
              <thead>
              <tr>
                <th>课程名称</th>
                <th>任课教师</th>
                <th>上课时间</th>
                <th>选择课程</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="course" items="${courses}">
                <tr>
                  <td>${course.CNAME}</td>
                  <td>${course.TNAME}</td>
                  <td>${course.CLASSTIME}</td>
                  <td><input type="checkbox" name="CID" value="${course.CID}"></td>
                </tr>
              </c:forEach>
              <!-- 更多课程 -->
              </tbody>
            </table>
            <button class="ui green button submit-button" type="submit">
              <i class="check icon"></i>
              提交选课
            </button>
          </form>
        </div>
      </div>
      <!-- 这是右边部分结束  -->
    </div>
  </div>
</div>
<!--弹出提示框-->

<!-- 选课成功提示框 -->
<div class="ui small modal" id="success-modal">
  <div class="header">
    提示
  </div>
  <div class="content">
    <p>选课成功！</p>
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button">
      确定
      <i class="checkmark icon"></i>
    </div>
  </div>
</div>

<!-- 没有选择任何课程提示框 -->
<div class="ui small modal" id="no-selection-modal">
  <div class="header">
    提示
  </div>
  <div class="content">
    <p>还没有选择任何一门课程！</p>
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button">
      确定
      <i class="checkmark icon"></i>
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
  $('form').on('submit', function(event) {
    // 阻止表单的默认提交行为
    event.preventDefault();

    if ($('input[type="checkbox"]:checked').length > 0) {
      // 如果有课程被选中，显示"选课成功！"的提示框
      $('#success-modal')
              .modal({
                closable: false,
                onApprove: function() {
                  // 在点击"确定"后，提交表单
                  event.target.submit();
                }
              })
              .modal('show');
    } else {
      // 如果没有课程被选中，显示"还没有选择任何一门课程！"的提示框
      $('#no-selection-modal').modal('show');
    }
  });
</script>
</html>