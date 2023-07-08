<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/8
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<nav class="ui inverted attached segment m-padded-tb-mini">
  <div class="ui container">
    <div class="ui inverted secondary menu">
      <h2 class="ui teal header item">T4_课程管理系统</h2>
      <a href="/MyCourse" class="item"><i class="home icon"></i>首页</a>
      <a href="StuClass.jsp" class="item"><i class="users icon"></i>我的课程</a>
      <a href="#" class="item"><i class="tags icon"></i>标签</a>
      <a href="PersonnelInformationStu.jsp" class="item"><i class="id card icon"></i>个人信息</a>
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
          <div class="ui grid">
            <div class="six wide column">
              <div class="ui fluid input">
                <input type="text" placeholder="课程名称" id="cname">
              </div>
            </div>
            <div class="six wide column">
              <div class="ui fluid input">
                <input type="text" placeholder="任课教师" id="tname">
              </div>
            </div>
            <div class="four wide column">
              <button class="ui blue button" type="submit"id="search">
                <i class="search icon"></i>
                查询
              </button>
            </div>
          </div>
          <div class="ui grid">
            <div class="column">
              <button class="ui green button">
                <i class="plus icon"></i>
                添加新的课程
              </button>
            </div>
          </div>
          <table class="ui celled table">
            <thead>
            <tr>
              <th>序号</th>
              <th>课程名称</th>
              <th>任课教师</th>
              <th>上课时间</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>1</td>
              <td>课程1</td>
              <td>教师1</td>
              <td>时间1</td>
            </tr>
            <tr>
              <td>2</td>
              <td>课程2</td>
              <td>教师2</td>
              <td>时间2</td>
            </tr>
            <!-- 更多课程 -->
            </tbody>
          </table>
        </div>
      </div>
      <!-- 这是右边部分结束  -->
    </div>
  </div>
</div>
<!--弹出的下拉列表框-->
<div class="ui small modal">
  <i class="close icon"></i>
  <div class="header">
    添加课程
  </div>
  <div class="content">
    <div class="ui form">
      <div class="field">
        <label>授课教师</label>
        <select class="ui dropdown">
          <option value="">选择教师</option>
          <option value="1">教师1</option>
          <option value="2">教师2</option>
          <!-- 更多选项 -->
        </select>
      </div>
      <div class="field">
        <label>课程名称</label>
        <select class="ui dropdown">
          <option value="">选择课程</option>
          <option value="1">课程1</option>
          <option value="2">课程2</option>
          <!-- 更多选项 -->
        </select>
      </div>
    </div>
  </div>
  <div class="actions">
    <div class="ui black deny button">
      取消
    </div>
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
  $('.ui.green.button').click(function() {
    $('.ui.small.modal').modal('show');
  });
</script>
<script>
  var searchbutton = document.getElementById("search")
  searchbutton.addEventListener("click",function (){
    var cname = document.getElementById("cname").value
    var tname = document.getElementById("tname").value
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST","SearchClassServlet",true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("cname=" + cname + "&tname=" + tname);
  })
</script>
</html>