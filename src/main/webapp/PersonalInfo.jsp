<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>个人信息</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="me.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
      <!-- 这是右边的列表 -->
      <div class="thirteen wide column">
        <div class="ui top attached segment">
          <div class="ui middle aligned four column grid">
            <div class="column">
              <h3 class="ui black header">我的信息</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <div class="ui grid">
            <div class="row">
              <div class="four wide column">
                <h3 class="ui medium black header"><i class="user icon"></i>姓名 ：</h3>
              </div>
              <div class="eight wide column">
                <p class="info-text styled-info" style="font-size: 0.9em;">${sessionScope.student.name}</p>
              </div>
            </div>
            <div class="row">
              <div class="four wide column">
                <h3 class="ui medium black header"><i class="genderless icon"></i>性别 ：</h3>
              </div>
              <div class="eight wide column">
                <div class="ui form">
                  <div class="inline fields">
                    <div class="field">
                      <div class="ui radio checkbox">
                        <input type="radio" id="male" name="gender" value="男" ${sessionScope.student.gender == true ? 'checked' : ''}>
                        <label for="male"><i class="mars icon"></i>男</label>
                      </div>
                    </div>
                    <div class="field">
                      <div class="ui radio checkbox">
                        <input type="radio" id="female" name="gender" value="女" ${sessionScope.student.gender == false ? 'checked' : ''}>
                        <label for="female"><i class="venus icon"></i>女</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="four wide column">
                <h3 class="ui medium black header"><i class="id card icon"></i>学号 ：</h3>
              </div>
              <div class="eight wide column">
                <p class="info-text styled-info" style="font-size: 0.9em;">${sessionScope.student.sid}</p>
              </div>
            </div>
            <div class="row">
              <div class="four wide column">
                <h3 class="ui medium black header"><i class="phone icon"></i>电话号码 ：</h3>
              </div>
              <div class="eight wide column">
                <p class="info-text styled-info" style="font-size: 0.9em;">${sessionScope.student.phone}</p>
              </div>
              <div class="four wide right aligned column">
                <a href="#" id="change-phone" class="styled-link">修改电话号码</a>
                <div class="ui small input" id="new-phone-form" style="display: none;">
                  <input type="text" name="new_phone" placeholder="新电话号码">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="four wide column">
                <h3 class="ui medium black header"><i class="book icon"></i>专业 ：</h3>
              </div>
              <div class="eight wide column">
                <p class="info-text styled-info" style="font-size: 0.9em;">${sessionScope.student.major}</p>
              </div>
              <div class="four wide right aligned column">
                <a href="#" id="change-major" class="styled-link">修改专业</a>
                <div class="ui small input" id="new-major-form" style="display: none;">
                  <input type="text" name="new_major" placeholder="新专业">
                </div>
              </div>
            </div>
            <div class="row">
              <div class="sixteen wide column">
                <button class="ui primary button" id="submit-changes" type="button">提交修改</button>
                <button class="ui red button" id="submit-logout" type="button">退出登录</button>

                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
                <script>
                  console.log('Document ready!');
                  $(document).ready(function() {
                    $('#submit-changes').click(function() {
                      var gender = $('input[name="gender"]:checked').val();
                      var phone = $('#new-phone-form input[name="new_phone"]').val();
                      var major = $('#new-major-form input[name="new_major"]').val();

                      var data = {
                        gender: gender,
                        new_phone: phone,
                        new_major: major
                      };

                      $.ajax({
                        url: 'UpdateProfileServlet',
                        type: 'POST',
                        data: data,
                        success: function(response) {
                          if (response.indexOf("成功") !== -1) {
                            // 成功
                            $('#alertContent').text(response);
                            $('#alertModal').modal('show');

                            // 在点击确定按钮后更新页面
                            $('#confirmButton').click(function() {
                              location.reload();
                            });
                          } else {
                            // 失败
                            $('#alertContent').text(response);
                            $('#alertModal').modal('show');

                            $('#confirmButton').click(function() {
                              location.reload();
                            });
                          }
                        }
                      });
                    });

                    $("#change-phone").click(function(e) {
                      e.preventDefault();
                      $("#new-phone-form").toggle();
                    });

                    $("#change-major").click(function(e) {
                      e.preventDefault();
                      $("#new-major-form").toggle();
                    });
                  });

                </script>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


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


<!-- foot content -->
<footer class="ui inverted vertical segment">
  <div class="ui center aligned container">
    <div class="ui inverted section divider">
      <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>
    </div>
  </div>
</footer>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>

<script>
  $(document).ready(function() {
    $('.ui.dropdown').dropdown();
  });
</script>
<script>
  $(document).ready(function() {
    $('#submit-logout').click(function() {
      // 发送退出登录请求
      $.ajax({
        url: 'LogoutServlet',
        type: 'GET',
        success: function(response) {
          // 重定向到登录页面
          window.location.href = 'Login.jsp';
        }
      });
    });
  });
</script>

</html>

