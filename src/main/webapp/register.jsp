<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/6
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="gbk"%>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
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
            <h2 class="ui center aligned header m-text m-teal">用户注册</h2>
            <form class="ui form" method="post" action="register">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" class="tooltip" placeholder="学号、教职工号">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="tag icon"></i>
                        <input type="text" name="nickname" class="tooltip" placeholder="姓名" data-content="不能为空">
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
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="repeatpassword" class="tooltip" placeholder="确认密码">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="text" name="phone" class="tooltip" placeholder="电话号码">
                    </div>
                </div>
                <div class="field container">
                    <div class="ui two column grid">
                        <div class="ui column">
                            <div class="ui left icon input">
                                <i class="check icon"></i>
                                <input type="text" name="checkcode" class="tooltip" placeholder="验证码" data-content="请填写验证码">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <div class="ui form">
                        <div class="inline fields">
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="type" value="teacher" checked>
                                    <label><i class="user icon"></i> 教师</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="type" value="student">
                                    <label><i class="student icon"></i> 学生</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button id="register-btn" class="ui fluid large teal submit button" type="submit">注 册</button>
                <div class="ui error message"></div>
            </form>
        </div>
        <div class="ui container m-margin-top-small">
            <div class="ui bottom attached warning message container center aligned">
                <i class="icon help"></i>
                已经有账号了？ <a href="#" th:href="@{/login}">这里登录</a>。
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
<script>
    $.fn.form.settings.rules.checkUsername = function(value) {
        // 添加你的验证逻辑
        var valid = (value !== '已存在的用户名');
        return valid;
    };
    $.fn.form.settings.rules.checkCode = function(value) {
        // 添加你的验证码验证逻辑
        var valid = (value === '1234'); // 假设'1234'是正确的验证码
        return valid;
    };

    $('.ui.form').form({
        inline:true,
        fields: {
            password: {
                identifier: 'password',
                rules: [
                    {
                        type: 'regExp',
                        value: /^\w{8,16}$/i,
                        prompt: '密码不合法，请重新输入'
                    }
                ]
            },
            repeatpassword: {
                identifier: 'repeatpassword',
                rules: [
                    {
                        type: 'match[password]',
                        prompt: '密码不匹配'
                    }
                ]
            },
            nickname: {
                identifier: 'nickname',
                rules: [
                    {
                        type: 'empty',
                        prompt: '姓名不能为空'
                    }
                ]
            },
            username: {
                identifier: 'username',
                rules: [
                    {
                        type: 'regExp',
                        value: /^\w{8,16}$/i,
                        prompt: '用户名不合法'
                    },
                    {
                        type: 'checkUsername',
                        prompt: '用户名已经被注册 <a href=[[@{/login}]]>点击登录?</a> '
                    }
                ]
            },
            checkcode: {
                identifier: 'checkcode',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请填写验证码'
                    },
                    {
                        type: 'checkCode',
                        prompt: '验证码填写错误'
                    }
                ]
            }
        }
    });
</script>
</body>
</html>

