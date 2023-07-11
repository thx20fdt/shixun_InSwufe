<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/7/10
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--  <title>管理您的教学班</title>--%>
<%--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">--%>
<%--  <link rel="stylesheet" href="./me.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="ui inverted attached segment m-padded-tb-mini">--%>
<%--  <div class="ui container">--%>
<%--    <div class="ui inverted secondary menu">--%>
<%--      <h2 class="ui teal header item">T4_课程管理系统</h2>--%>
<%--      <a href="CourseToughtByMe" class="item"><i class="home icon"></i>首页</a>--%>
<%--      <a href="ClassManage.jsp" class="item"><i class="keyboard icon"></i>管理班级</a>--%>
<%--      <a href="StuScore.jsp" class="item"><i class="clipboard icon"></i>学生成绩</a>--%>
<%--      <a href="PersonalInfoForTeaServlet" class="item"><i class="id card icon"></i>个人信息</a>--%>
<%--      <div class="right item">--%>
<%--        <div class="ui left icon inverted input">--%>
<%--          <input type="text" placeholder="搜索……">--%>
<%--          <i class="search link icon"></i>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</nav>--%>

<%--<!--mid content-->--%>


<%--<div class="m-padded-tb-larger m-content">--%>
<%--  <div class="ui container">--%>
<%--    <div class="ui grid">--%>
<%--      <!-- 这是左边的列表-->--%>
<%--      <div class="three wide column">--%>
<%--        <div class="ui segment">--%>
<%--          <div class="ui vertical fluid tabular menu">--%>
<%--            <a class="item">--%>
<%--              <i class="book icon" style="margin-right: 5px;"></i>课程--%>
<%--            </a>--%>
<%--            <a class="item">--%>
<%--              <i class="star icon" style="margin-right: 5px;"></i>收藏--%>
<%--            </a>--%>
<%--            <a class="item">--%>
<%--              <i class="lightbulb icon" style="margin-right: 5px;"></i>创作--%>
<%--            </a>--%>
<%--            <a href="StuGroup.html" class="item">--%>
<%--              <i class="users icon" style="margin-right: 5px;"></i>小组--%>
<%--            </a>--%>
<%--            <a class="item">--%>
<%--              <i class="pencil alternate icon" style="margin-right: 5px;"></i>笔记--%>
<%--            </a>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--      <!-- 这是右边的列表-->--%>
<%--      <div class="thirteen wide column">--%>
<%--        <div class="ui top attached segment">--%>
<%--          <div class="ui middle aligned four column grid">--%>
<%--            <div class="six wide column">--%>
<%--              <h3 class="ui black header">班级信息管理</h3>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--        <div class="ui attached segment">--%>
<%--          <div class="ui grid">--%>
<%--            <div class="eight wide column">--%>
<%--              <form action="AddStudentIntoMyClass" class="ui form" method="post">--%>
<%--                <div class="ui action input">--%>
<%--                  <input type="text" placeholder="学生学号" name="SID">--%>
<%--                  <button class="ui blue button" type="submit">--%>
<%--                    <i class="search icon"></i>--%>
<%--                    添加--%>
<%--                  </button>--%>
<%--                </div>--%>
<%--              </form>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--      <!-- 这是右边部分结束  -->--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</div>--%>



<%--<!-- foot content -->--%>
<%--<footer class="ui inverted vertical segment">--%>
<%--  <div class="ui center aligned container">--%>
<%--    <div class="ui inverted section divider">--%>
<%--      <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</footer>--%>
<%--</body>--%>
<%--<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>--%>
<%--<script>--%>
<%--  function deleteStudent(studentId, cid) {--%>
<%--    if (confirm("确认删除该学生吗？")) {--%>
<%--      $.ajax({--%>
<%--        type: "POST",--%>
<%--        url: "DeleteStudentServlet",--%>
<%--        data: {sid: studentId, cid: cid}, // 传递两个值--%>
<%--        success: function(response) {--%>
<%--          // 根据删除结果进行相应的处理--%>
<%--          if (response === "success") {--%>
<%--            alert("删除成功！");--%>
<%--            // 刷新学生列表--%>
<%--            location.reload();--%>
<%--          } else {--%>
<%--            alert("删除失败！");--%>
<%--          }--%>
<%--        },--%>
<%--        error: function() {--%>
<%--          alert("请求失败，请重试！");--%>
<%--        }--%>
<%--      });--%>
<%--    }--%>
<%--  }--%>
<%--</script>--%>
<%--</html>--%>

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
              <h3 class="ui black header">班级信息管理</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <div class="ui grid">
            <div class="eight wide column">
              <form action="AddStudentIntoMyClass" class="ui form" method="post">
                <div class="ui action input">
                  <input type="text" placeholder="学生学号" name="SID">
                  <button class="ui blue button" type="submit">
                    <i class="search icon"></i>
                    添加
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- 这是右边部分结束  -->
    </div>
  </div>
</div>

<div class="ui small modal">
  <div class="ui icon header">
    <i class="check circle outline icon"></i>
    添加成功
  </div>
  <div class="content">
    <p>新的学生已成功添加。</p>
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button">
      知道了
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
  // $(document).ready(function() {
  //   $('.ui.form').on('submit', function(e) {
  //     var inputValue = $(this).find('input[type="text"]').val(); // 获取输入框的值
  //     if (inputValue) { // 如果输入框中有值
  //       $('.ui.modal').modal('show'); // 显示模态框
  //     }
  //   });
  // });


  $(document).ready(function() {
    $('.ui.form').on('submit', function(e) {
      e.preventDefault(); // 阻止默认提交行为

      var inputValue = $(this).find('input[type="text"]').val(); // 获取输入框的值

      if (inputValue) { // 如果输入框中有值
        $.ajax({
          type: 'POST',
          url: 'AddStudentIntoMyClass',
          data: $(this).serialize(),
          success: function(response) {
            // 你可以在这里添加处理服务器响应的代码
            $('.ui.modal').modal('show'); // 显示模态框
          },
          error: function() {
            // 处理错误的请求
            console.log('AJAX 请求失败');
          }
        });
      }
    });
  });
</script>
</html>

