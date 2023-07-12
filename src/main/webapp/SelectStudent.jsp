<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/7/10
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
              <h3 class="ui black header">班级成员管理</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <div class="ui grid">
            <div class="eight wide column">
              <form action="SelectStuNameServlet" class="ui form" method="post">
                <div class="ui action input">
                  <input type="text" placeholder="学生姓名" name="studentName">
                  <button class="ui blue button" type="submit">
                    <i class="search icon"></i>
                    查询
                  </button>
                </div>
              </form>
            </div>
            <div class="four wide column">
              <div class="ui right floated">
                <button class="ui green button" onclick="location.href='AddStudentIntoMyClass.jsp'">
                  <i class="plus icon"></i>
                  添加学生
                </button>
              </div>
            </div>
          </div>
          <table class="ui celled table">
            <thead>
            <tr>
              <th class="four wide">专业</th>
              <th class="three wide">姓名</th>
              <th class="four wide">学号</th>
              <th class="two wide">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${sessionScope.studentList}">
              <tr>
                <td>${student.major}</td>
                <td>${student.name}</td>
                <td>${student.sid}</td>
                <td>
                  <button class="ui red button" onclick="showDeleteConfirmation('${student.sid}', '${sessionScope.cid}')">删除</button>
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

<!-- 确认删除提示框 -->
<div class="ui small modal" id="confirmModal">
  <div class="header">
    提示
  </div>
  <div class="content" id="alertContent">
    您确定要删除该学生吗？
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button" id="confirmDeleteButton">
      确定
      <i class="checkmark icon"></i>
    </div>
    <div class="ui positive right labeled icon button" id="cancelDeleteButton">
      取消
      <i class="checkmark icon"></i>
    </div>
  </div>
</div>

<!-- 删除成功提示框 -->
<div class="ui small modal" id="deleteSuccessModal">
  <div class="header">
    提示
  </div>
  <div class="content" id="deleteSuccessContent">
    您已成功删除该学生！
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button" id="confirmDeleteSuccessButton">
      确定
      <i class="checkmark icon"></i>
    </div>
  </div>
</div>

<!-- 删除失败提示框 -->
<div class="ui small modal" id="deleteFailureModal">
  <div class="header">
    提示
  </div>
  <div class="content" id="deleteFailureContent">
    删除失败
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button" id="confirmDeleteFailureButton">
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
  function showDeleteConfirmation(studentId, classId) {
    $('#confirmModal').modal('show'); // 显示确认对话框

    // 确认删除按钮点击事件
    $('#confirmDeleteButton').click(function() {
      deleteStudent(studentId, classId);
    });
    // 取消按钮点击事件
    $('#cancelDeleteButton').click(function() {
      $('#confirmModal').modal('hide'); // 隐藏提示模态框
    });
  }

  function deleteStudent(studentId, classId) {
    $.ajax({
      type: 'POST',
      url: 'DeleteStudentServlet',
      data: { sid: studentId, cid: classId },
      success: function(response) {
        if (response.indexOf("成功") !== -1) {
          // 删除成功
          $('#deleteSuccessContent').text('您已成功删除该学生！');
          $('#deleteSuccessModal').modal('show');
          $('#confirmDeleteSuccessButton').click(function() {
            window.location.href = 'SelectStudentServlet?CID=${sessionScope.cid}';
          });
        } else {
          // 删除失败
          $('#deleteFailureContent').text('删除失败');
          $('#deleteFailureModal').modal('show');
        }
        $('#confirmDeleteFailureButton').click(function() {
          location.reload();
        });
      },
      error: function() {
        // 请求失败
        $('#deleteFailureContent').text('请求失败，请重试！');
        $('#deleteFailureModal').modal('show');
      }
    });
  }




</script>
</html>


