<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>欢迎使用课程管理系统</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.css">
  <style>
    body {
      background-image: url("bg.png");
      background-size: cover;
    }
    .centered-form {
      display: flex;
      justify-content: center;
      align-items: center;
      max-width:400px;
      margin:0 auto;
      min-height: 100vh;
    }
  </style>
</head>
<body>
<div class="centered-form">
  <div class="ui container">
    <div class="ui segment">
      <h2 class="ui center aligned header m-text m-teal">用户登录</h2>
      <form class="ui form" method="post" action="LoginServlet">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="userid" class="tooltip" placeholder="学号、教职工号">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" class="tooltip" placeholder="密码"
                   data-content="长度必须为8-16个字符">
          </div>
        </div>
        <div class="field">
          <div class="ui form">
            <div class="inline fields">
              <div class="field">
                <div class="ui radio checkbox">
                  <input type="radio" name="user_role" value="student" checked>
                  <label><i class="user icon"></i> 学生登录</label>
                </div>
              </div>
              <div class="field">
                <div class="ui radio checkbox">
                  <input type="radio" name="user_role" value="teacher">
                  <label><i class="student icon"></i> 教师登录</label>
                </div>
              </div>
            </div>
          </div>
        </div>
        <button id="register-btn" class="ui fluid large teal submit button" type="submit">登 录</button>
      </form>
    </div>
    <div class="ui container">
      <div class="ui bottom attached warning message container center aligned">
        <i class="icon help"></i>
        新用户？ <a href="register.jsp" >这里注册</a>。
      </div>
    </div>
  </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
<script>
  $('.ui.form').form({
    inline:true,
    keyboardShortcuts:false,
    fields:{
      userid:{
        identifier:'userid',
        on:`blur`,
        rules:[
          {
            type:'empty',
            prompt:'请输入学号或者教职工号'
          }
        ]
      },
      password:{
        identifier: 'password',
        rules:[
          {
            type:'empty',
            prompt:'请输入密码'
          }
        ]
      },
    }
  })
</script>
</html>