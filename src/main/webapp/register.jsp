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
    <title>��ӭʹ�ÿγ̹���ϵͳ</title>
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
            <h2 class="ui center aligned header m-text m-teal">�û�ע��</h2>
            <form class="ui form" method="post" action="register">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" class="tooltip" placeholder="ѧ�š���ְ����">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="tag icon"></i>
                        <input type="text" name="nickname" class="tooltip" placeholder="����" data-content="����Ϊ��">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" class="tooltip" placeholder="����"
                               data-content="���ȱ���Ϊ8-16���ַ�">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="repeatpassword" class="tooltip" placeholder="ȷ������">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="text" name="phone" class="tooltip" placeholder="�绰����">
                    </div>
                </div>
                <div class="field container">
                    <div class="ui two column grid">
                        <div class="ui column">
                            <div class="ui left icon input">
                                <i class="check icon"></i>
                                <input type="text" name="checkcode" class="tooltip" placeholder="��֤��" data-content="����д��֤��">
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
                                    <label><i class="user icon"></i> ��ʦ</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox">
                                    <input type="radio" name="type" value="student">
                                    <label><i class="student icon"></i> ѧ��</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button id="register-btn" class="ui fluid large teal submit button" type="submit">ע ��</button>
                <div class="ui error message"></div>
            </form>
        </div>
        <div class="ui container m-margin-top-small">
            <div class="ui bottom attached warning message container center aligned">
                <i class="icon help"></i>
                �Ѿ����˺��ˣ� <a href="#" th:href="@{/login}">�����¼</a>��
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
<script>
    $.fn.form.settings.rules.checkUsername = function(value) {
        // ��������֤�߼�
        var valid = (value !== '�Ѵ��ڵ��û���');
        return valid;
    };
    $.fn.form.settings.rules.checkCode = function(value) {
        // ��������֤����֤�߼�
        var valid = (value === '1234'); // ����'1234'����ȷ����֤��
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
                        prompt: '���벻�Ϸ�������������'
                    }
                ]
            },
            repeatpassword: {
                identifier: 'repeatpassword',
                rules: [
                    {
                        type: 'match[password]',
                        prompt: '���벻ƥ��'
                    }
                ]
            },
            nickname: {
                identifier: 'nickname',
                rules: [
                    {
                        type: 'empty',
                        prompt: '��������Ϊ��'
                    }
                ]
            },
            username: {
                identifier: 'username',
                rules: [
                    {
                        type: 'regExp',
                        value: /^\w{8,16}$/i,
                        prompt: '�û������Ϸ�'
                    },
                    {
                        type: 'checkUsername',
                        prompt: '�û����Ѿ���ע�� <a href=[[@{/login}]]>�����¼?</a> '
                    }
                ]
            },
            checkcode: {
                identifier: 'checkcode',
                rules: [
                    {
                        type: 'empty',
                        prompt: '����д��֤��'
                    },
                    {
                        type: 'checkCode',
                        prompt: '��֤����д����'
                    }
                ]
            }
        }
    });
</script>
</body>
</html>

