<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="gbk"%>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>��ӭʹ�ÿγ̹���ϵͳ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="ui eight column centered stackable grid">
    <div class="ui column m-margin-top-large" style="min-width:500px">
        <div class="ui container m-margin">
            <h2 class="ui center aligned header m-text m-teal">�û�ע��</h2>
        </div>
        <div class="ui container m-shadow-small">
            <form class="ui form" method="post" action="hello-servlet">
                <div class="ui  segment">
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
                    <div class="field container">

                        <div class="ui two column grid">
                            <div class="ui column">
                                <div class="ui left icon input">
                                    <i class="check icon"></i>
                                    <input type="text" name="checkcode" class="tooltip" placeholder="��֤��"
                                           data-content="����д��֤��">
                                </div>
                            </div>
                            <div class="ui column">
                            </div>
                        </div>
                    </div>
                    <input type="submit" value="ע��"/>
                </div>
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
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
<script>
    $('.ui.form').form({
        // inline: true,
        fields: {
            password: {
                identifier: 'password',
                rules: [
                    {
                        type: 'regExp',  //
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
                        prompt: '�ǳƲ���Ϊ��'
                    }
                ]
            },
            username: {
                identifier: 'username',
                rules: [
                    {
                        type: 'regExp',
                        value: /^[a-zA-Z]\w{5,15}$/i,
                        prompt: '�û������Ϸ�'
                    },
                    {
                        type: 'checkUsername',
                        prompt: '�û����Ѿ���ע�� <a href=[[@{/login}]]>�����¼?</a> '
                    }
                ]
            },
            email: {
                identifier: 'email',
                rules: [
                    {
                        type: 'email',
                        prompt: '������Ϸ�������'
                    },
                    {
                        type: 'checkEmail',
                        prompt: '�����Ѿ�ע�ᣬ <a href=[[@{/login}]]>�����¼?</a> '
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
    })
</script>
</body>
</html>