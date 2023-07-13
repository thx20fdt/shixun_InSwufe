<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>首页</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="me.css">
  <style>
    #mobile-menu-button {
      display: none !important; /* 默认隐藏 */
    }

    @media screen and (max-width: 768px) {

      #mobile-menu-button {
        display: block !important; /* 在小屏幕设备上显示 */
      }
    }
  </style>
</head>
<body>

<button class="ui button" id="mobile-menu-button">
  <i class="sidebar icon"></i>
</button>

<div class="ui sidebar inverted vertical menu" id="mobile-menu">
  <h2 class="ui teal header item">T4_课程管理系统</h2>
  <a href="MyCourse" class="item"><i class="home icon"></i>首页</a>
  <div class="ui dropdown item">
    <i class="users icon"></i>我的课程
    <i class="dropdown icon"></i>
    <div class="menu">
      <a class="item" href="MyCourseInfoServlet">课程列表</a>
      <a class="item" href="SearchClassServlet">选择课程</a>
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

<div class="pusher">
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
            <a class="item" href="SelectCourseServlet">选择课程</a>
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
            <h3 class="ui black header">我的课程信息</h3>
            <div class="ui middle aligned four column grid">
              <div class="column">
              </div>
            </div>
          </div>
          <div class="ui attached segment">
            <div class="ui cards">
              <c:forEach var="course" items="${courses}">
                <div class="card">
                  <a href="CourseDetail?CID=${course.CID}" >
                    <div class="image">
                      <img src="">
                    </div>
                  </a>
                  <div class="content">
                    <a href="CourseDetail?CID=${course.CID}" class="header" style="font-size: 1.2em; color: black;">
                        ${course.CNAME}
                    </a>
                    <div class="meta">${course.TID}</div>
                  </div>
                </div>
              </c:forEach>
              <!-- 更多课程卡片 -->
            </div>
          </div>
        </div>
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
</div>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
<script>
  $(document).ready(function() {
    $('.ui.dropdown').dropdown();
  });
  $(document).ready(function() {
    $('.ui.dropdown').dropdown();
    $('#mobile-menu-button').click(function() {
      $('#mobile-menu').sidebar('toggle');
    });
  });
</script>
</html>




<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--  <meta charset="UTF-8">--%>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--  <title>首页</title>--%>
<%--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">--%>
<%--  <link rel="stylesheet" href="me.css">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="ui inverted attached segment m-padded-tb-mini">--%>
<%--  <div class="ui container">--%>
<%--    <div class="ui inverted secondary menu">--%>
<%--      <h2 class="ui teal header item">T4_课程管理系统</h2>--%>
<%--      <a href="MyCourse" class="item"><i class="home icon"></i>首页</a>--%>
<%--      <div class="ui dropdown item">--%>
<%--        <i class="users icon"></i>我的课程--%>
<%--        <i class="dropdown icon"></i>--%>
<%--        <div class="menu">--%>
<%--          <a class="item" href="MyCourseInfoServlet">课程列表</a>--%>
<%--          <a class="item" href="SelectCourse.jsp">选择课程</a>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--      <a href="MyScoreServlet" class="item"><i class="clipboard icon"></i>我的成绩</a>--%>
<%--      <a href="PersonalInfoServlet" class="item"><i class="id card icon"></i>个人信息</a>--%>
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
<%--            <a href="MyGroupServlet" class="item">--%>
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
<%--          <h3 class="ui black header">我的课程信息</h3>--%>
<%--          <div class="ui middle aligned four column grid">--%>
<%--            <div class="column">--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--        <div class="ui attached segment">--%>
<%--          <div class="ui cards">--%>
<%--            <c:forEach var="course" items="${courses}">--%>
<%--              <div class="card">--%>
<%--                <a href="CourseDetail?CID=${course.CID}" >--%>
<%--                  <div class="image">--%>
<%--                    <img src="">--%>
<%--                  </div>--%>
<%--                </a>--%>
<%--                <div class="content">--%>
<%--                  <a href="CourseDetail?CID=${course.CID}" class="header" style="font-size: 1.2em; color: black;">--%>
<%--                      ${course.CNAME}--%>
<%--                  </a>--%>
<%--                  <div class="meta">${course.TID}</div>--%>
<%--                </div>--%>
<%--              </div>--%>
<%--            </c:forEach>--%>
<%--            <!-- 更多课程卡片 -->--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>
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
<%--    $('.ui.dropdown').dropdown();--%>
<%--  });--%>
<%--</script>--%>



<%--</html>--%>

<%--<script>--%>
<%--  $(document).ready(function() {--%>
<%--    $('.ui.dropdown').dropdown();--%>
<%--  });--%>
<%--</script>--%>