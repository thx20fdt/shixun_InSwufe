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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui-calendar/dist/calendar.min.css">
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

      <!--提示框弹出 -->
      <div class="ui small modal" id="alertModal">
        <div class="header">
          提示
        </div>
        <div class="content" id="alertContent">
        </div>
        <div class="actions">
          <div class="ui positive right labeled icon button" id="confirmButton">
            确定
            <i class="checkmark icon"></i>
          </div>
        </div>
      </div>

      <!-- 这是右边的列表-->
      <div class="thirteen wide column">
        <div class="ui top attached segment">
          <div class="ui middle aligned four column grid">
            <div class="six wide column">
              <h3 class="ui black header">发布活动</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <form class="ui form" id="activityForm">
            <div class="field">
              <label>活动名称</label>
              <input type="text" name="activityName" placeholder="输入活动名称" required>
            </div>
            <div class="field">
              <label>活动内容</label>
              <textarea  wrap="hard" name="activityContent" placeholder="输入活动内容" required></textarea>
            </div>
            <div class="fields">
              <div class="eight wide field">
                <label>活动开始时间</label>
                <div class="ui calendar" id="startDatetimePicker">
                  <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input type="text" name="activityStartTime" placeholder="选择开始时间" required>
                  </div>
                </div>
              </div>
              <div class="eight wide field">
                <label>活动截止时间</label>
                <div class="ui calendar" id="endDatetimePicker">
                  <div class="ui input left icon">
                    <i class="calendar icon"></i>
                    <input type="text" name="activityDeadline" placeholder="选择截止时间" required>
                  </div>
                </div>
              </div>
            </div>
            <div class="field">
              <label>是否可分组</label>
              <div class="ui radio checkbox">
                <input type="radio" name="groupable" value="true">
                <label>是</label>
              </div>
              <div class="ui radio checkbox">
                <input type="radio" name="groupable" value="false" checked>
                <label>否</label>
              </div>
            </div>
            <input type="hidden" name="cid" value="<%= request.getSession().getAttribute("cid") %>">
            <button class="ui blue button" type="submit" id="submitButton">发布活动</button>
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
      <p class="m-text-thin m-opacity-mini">Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui-calendar/dist/calendar.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>

<script>
  $(document).ready(function() {
    $('#submitButton').click(function(event) {
      event.preventDefault(); // 阻止表单默认的提交行为

      var activityName = $('input[name="activityName"]').val();
      var activityContent = $('textarea[name="activityContent"]').val();
      var activityStartTime = $('input[name="activityStartTime"]').val();
      var activityDeadline = $('input[name="activityDeadline"]').val();
      var groupable = $('input[name="groupable"]:checked').val();
      var cid = $('input[name="cid"]').val();

      if (activityName === '' || activityContent === '' || activityStartTime === '' || activityDeadline === '') {
        // 如果输入框中有任何内容为空，弹出提示框
        $('#alertContent').text('请输入完整的活动信息！');
        $('#alertModal').modal('show');
      } else {
        // 构造表单数据对象
        var formData = {
          activityName: activityName,
          activityContent: activityContent,
          activityStartTime: activityStartTime,
          activityDeadline: activityDeadline,
          groupable: groupable,
          cid: cid
        };

        // 使用 AJAX 异步提交表单数据到Servlet
        $.post('ReleaseActivityServlet', formData)
                .done(function(data) {
                  // 根据Servlet返回的消息进行处理
                  if (data.indexOf('成功') !== -1) {
                    // 活动发布成功
                    $('#alertContent').text(data);
                    $('#alertModal').modal('show');

                    // 点击确定按钮后跳转到ManageActivityServlet
                    $('#confirmButton').click(function() {
                      window.location.href = 'ManageActivityServlet?CID=<%= session.getAttribute("cid") %>';
                    });
                  } else {
                    // 活动发布失败或其他错误
                    $('#alertContent').text(data);
                    $('#alertModal').modal('show');
                  }
                });
      }
    });
  });


</script>

<script>
  $('#startDatetimePicker').calendar({
    type: 'datetime',
    formatter: {
      datetime: function (date, settings) {
        if (!date) return '';
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
      }
    }
  });

  $('#endDatetimePicker').calendar({
    type: 'datetime',
    formatter: {
      datetime: function (date, settings) {
        if (!date) return '';
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
      }
    }
  });
</script>
</html>
