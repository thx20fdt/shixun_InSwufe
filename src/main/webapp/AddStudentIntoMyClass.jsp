<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
              <form id="addStudentForm" class="ui form" method="post">
                <div class="ui action input">
                  <input type="text" placeholder="学生学号" name="SID">
                  <button class="ui blue button" id="submit-add" type="button">
                    <i class="search icon"></i>
                    添加学生
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

<div class="ui small modal" id="addConfirmationResultModal">
  <div class="header">
    提示
  </div>
  <div class="content" id="addConfirmationResultContent">
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button" id="confirmResultButton">
      确定
      <i class="checkmark icon"></i>
    </div>
  </div>
</div>

<div class="ui small modal" id="addConfirmationModal">
  <div class="header">
    提示
  </div>
  <div class="content">
    确定要添加该学生吗？
  </div>
  <div class="actions">
    <div class="ui positive right labeled icon button" id="confirmAddButton">
      确定
      <i class="checkmark icon"></i>
    </div>
    <div class="ui negative right labeled icon button" id="cancelAddButton">
      取消
      <i class="close icon"></i>
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

<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
<script>
  $(document).ready(function() {
    function showAddConfirmation() {
      $('#addConfirmationModal').modal('show');
    }

    $('#submit-add').click(function() {
      showAddConfirmation();
    });

    $('#confirmAddButton').click(function() {
      $('#addConfirmationModal').modal('hide');
      var formData = $('#addStudentForm').serialize();

      $.ajax({
        type: 'POST',
        url: 'AddStudentIntoMyClassServlet',
        data: formData,
        success: function(response) {
          if (response.indexOf("成功") !== -1) {
            $('#addConfirmationResultContent').text(response);
            $('#addConfirmationResultModal').modal('show');

            $('#confirmResultButton').click(function() {
              window.location.href = 'SelectStudentServlet?CID=<%= session.getAttribute("cid") %>';
            });
          } else {
            $('#addConfirmationResultContent').text(response);
            $('#addConfirmationResultModal').modal('show');
          }

          setTimeout(function() {
            location.reload();
          }, 2000);
        },
        error: function() {
          console.log('AJAX 请求失败');
        }
      });
    });

    $('#cancelAddButton').click(function() {
      $('#addConfirmationModal').modal('hide');
    });
  });
</script>
</html>
