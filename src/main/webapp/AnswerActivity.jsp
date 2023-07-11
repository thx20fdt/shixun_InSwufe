<%--
  Created by IntelliJ IDEA.
  User: FX506H
  Date: 2023/7/9
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html><!DOCTYPE html>
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
            <a href="MyCourse" class="item"><i class="home icon"></i>首页</a>
            <a href="MyCourseInfoServlet" class="item"><i class="users icon"></i>我的课程</a>
            <a href="MyScoreServlet" class="item"><i class="clipboard icon"></i>查看成绩</a>
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
                        <a href="StuGroup.html" class="item">
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
                            <h3 class="ui black header">${activity.ANAME}</h3>
                            <p class="ui small grey text">作答时间从${activity.BEGINTIME}至${activity.ENDTIME}</p>
                        </div>
                    </div>
                </div>
                <div class="ui attached segment">
                    <p>${activity.ACONTENT}</p>
                    <form action="ActivitySubmit" method="post">
                    <div class="ui form reply">
                        <div class="field">
                            <textarea name="CONTENT" placeholder="输入你的作答"></textarea>
                        </div>
                        <div class="field">
                            <input type="file" id="fileUpload" multiple style="display:none">
                            <label for="fileUpload" class="ui button">
                                <i class="file icon"></i>上传附件
                            </label>
                            <div id="fileList"></div>
                        </div>
                        <input type="hidden" id="hiddenField" name="AID" value="${activity.AID}">
                        <input type="hidden" name="CID" value="${activity.CID}">
                        <button class="ui blue labeled submit icon button" type="submit">
                            <i class="icon edit"></i> 提交
                        </button>
                    </div>
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
            <p class="m-text-thin m-opacity-mini">Copyright © 2022-2023 Designed by T4_Group</p>
        </div>
    </div>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/gh/jquery/jquery@3.6/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.5.0/dist/semantic.min.js"></script>
</html>
<script>
    $('#fileUpload').on('change', function() {
        // 获取文件列表
        var files = $(this)[0].files;
        // 清空显示文件列表的元素
        $('#fileList').empty();
        // 遍历每个文件
        $.each(files, function(i, file) {
            // 创建一个新的文件名元素
            var fileElem = $('<a>', {
                text: ' 上传的文件: ' + file.name,
                href: '#',
                class: 'file-link',
                onclick: 'previewFile(event, ' + i + ')',
                onmouseover: 'highlightFile(event, true)',
                onmouseout: 'highlightFile(event, false)'
            });
            // 添加新元素到文件列表中
            $('#fileList').append(fileElem);
            $('#fileList').append('<br>'); // 添加换行
        });
    });
    function previewFile(event, index) {
        event.preventDefault();
        // 获取文件列表
        var files = $('#fileUpload')[0].files;
        if (files.length > index) {
            var file = files[index];
            // 以下只对图片文件预览有效
            var reader = new FileReader();
            reader.onload = function(e) {
                window.open(e.target.result, '_blank');
            };
            reader.readAsDataURL(file);
        }
    }
    function highlightFile(event, highlight) {
        var color = highlight ? 'blue' : 'black';
        $(event.target).css('color', color);
    }
</script>












