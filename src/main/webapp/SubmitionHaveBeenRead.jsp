<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: FX506H--%>
<%--  Date: 2023/7/11--%>
<%--  Time: 16:49--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--  <title>我的课程</title>--%>
<%--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">--%>
<%--  <link rel="stylesheet" href="me.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="Tea.jsp" />--%>

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
<%--            <a class="item">--%>
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
<%--              <h3 class="ui black header">${sessionScope.submit.ANAME}</h3>--%>
<%--              <h3 class="ui black header">得分：${sessionScope.submit.SCORE}</h3>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--        <div class="ui attached segment">--%>
<%--          <p>${sessionScope.submit.ACONTENT}</p>--%>
<%--          <div class="ui form reply" id="gradeForm">--%>
<%--            <div class="field">--%>
<%--              <textarea placeholder="学生的作答" disabled>${sessionScope.submit.CONTENT}</textarea>--%>
<%--            </div>--%>
<%--            <div class="ui error message"></div>--%>
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
<%--  $(document).ready(function() {--%>
<%--    $('#gradeForm').form({--%>
<%--      fields: {--%>
<%--        grade: {--%>
<%--          identifier  : 'grade',--%>
<%--          rules: [--%>
<%--            {--%>
<%--              type   : 'empty',--%>
<%--              prompt : '请输入成绩'--%>
<%--            },--%>
<%--            {--%>
<%--              type   : 'integer[0..100]',--%>
<%--              prompt : '成绩应在0-100之间'--%>
<%--            }--%>
<%--          ]--%>
<%--        },--%>
<%--      }--%>
<%--    });--%>
<%--  });--%>
<%--</script>--%>
<%--</html>--%>

<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/11
  Time: 16:49
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
              <h3 class="ui black header">${sessionScope.submit.ANAME}</h3>
              <h3 class="ui black header">得分：${sessionScope.submit.SCORE}</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <p>${sessionScope.submit.ACONTENT}</p>
          <div class="ui form reply" id="gradeForm">
            <div class="field">
              <textarea placeholder="学生的作答" disabled>${sessionScope.submit.CONTENT}</textarea>
            </div>
            <a href="DownloadFile?AID=${sessionScope.submit.AID}&SID=${sessionScope.submit.SID}" class="ui button">
              下载学生文件
            </a>
            <div class="ui error message"></div>
          </div>
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

