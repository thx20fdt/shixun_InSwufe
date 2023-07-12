<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/7/8
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>创建你的小组</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="./me.css">
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
              <h3 class="ui black header">我的小组</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <form id="groupForm" class="ui form">
            <div class="field">
              <label>小组成员学号</label>
              <input type="text" id="memberId" name="memberId" placeholder="输入小组成员学号">
            </div>
            <div class="field">
              <label>小组成员电话</label>
              <input type="text" id="memberPhone" name="memberPhone" placeholder="输入该成员对应电话">
            </div>
            <div class="field">
              <%
                String aid = request.getParameter("AID");

              %>

              <input type="hidden" id="AID" name="AID" value="<%= aid %>">
            </div>
            <button class="ui blue button" type="button" id="submitButton">提交</button>
          </form>
        </div>
      </div>
      <!-- 这是右边部分结束  -->
    </div>
  </div>
</div>

<!--提示框弹出 -->
<div class="ui small modal" id="alertModal">
  <div class="header">
    提示
  </div>
  <div class="content" id="alertContent">
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
      <p class="m-text-thin m-opacity-mini">Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
<script>
  $('#submitButton').click(function() {
    var memberId = $('#memberId').val();
    var memberPhone = $('#memberPhone').val();
    var activityId = $('#AID').val(); // 获取AID的值

    if (memberId == "" || memberPhone == "") {
      // 如果输入框中没有任何内容就点击了提交按钮，弹出提示框
      $('#alertContent').text('请输入小组成员学号和电话！');
      $('#alertModal').modal('show');
    } else {
      // 提交表单数据到Servlet
      $.post("CreateGroupServlet", $("#groupForm").serialize())
              .done(function(data) {
                if (data.success) {
                  // 创建成功，显示成功提示框
                  $('#alertContent').text(data.message);
                } else {
                  // 创建失败，显示失败提示框
                  $('#alertContent').text(data.message);
                }
                $('#alertModal').modal('show');
              });
    }
  });
</script>

<script>
  $(document).ready(function() {
    $('.ui.dropdown').dropdown();
  });
</script>

</html>
