<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>删除小组成员</title>
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
              <h3 class="ui black header"><%= request.getParameter("GID") %>小组</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <form id="groupForm" class="ui form">
            <div class="field">
              <label>需要删除成员的学号</label>
              <input type="text" id="memberId" name="memberId" placeholder="输入学号">
              <input type="hidden" name="AID" value="<%= request.getParameter("AID") %>">
              <input type="hidden" name="GID" value="<%= request.getParameter("GID") %>">
            </div>
            <button class="ui red button" type="button" id="submitButton">删除</button>
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
      <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
<script>
  $(document).ready(function() {
    $('#submitButton').click(function() {
      var memberId = $('#memberId').val();
      var AID = $('input[name="AID"]').val();
      var GID = $('input[name="GID"]').val();

      if (memberId == "") {
        // 如果输入框中没有任何内容就点击了提交按钮，弹出提示框
        $('#alertContent').text('请输入您需要删除的小组成员学号！');
        $('#alertModal').modal('show');
      } else {
        // 构造表单数据对象
        var formData = {
          AID: AID,
          GID: GID,
          memberId: memberId
        };

        // 发送POST请求到DeleteStuFromGroupServlet
        $.post("DeleteStuFromGroupServlet", formData)
                .done(function(data) {
                  // 在点击表单中的提交按钮之后，根据Servlet返回的消息进行处理
                  if (data.indexOf("成功") !== -1) {
                    // 成功将学生移出小组
                    $('#alertContent').text('删除成功！');
                    $('#alertModal').modal('show');
                  } else {
                    // 学生未在该组中或其他错误
                    $('#alertContent').text(data);
                    $('#alertModal').modal('show');
                  }

                  // 2秒后刷新页面
                  setTimeout(function() {
                    window.location.reload();
                  }, 2000);
                });
      }
    });
  });


</script>
</html>