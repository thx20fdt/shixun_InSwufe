<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>添加小组成员</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
  <link rel="stylesheet" href="./me.css">
</head>
<body>
<jsp:include page="Tea.jsp" />
<!--mid content-->
<div class="m-padded-tb-larger m-content">
  <div class="ui container">
    <div class="ui grid">
      <!-- ...左边列表... -->
      <div class="thirteen wide column">
        <div class="ui top attached segment">
          <div class="ui middle aligned four column grid">
            <div class="column">
              <h3 class="ui black header">新建小组</h3>
            </div>
          </div>
        </div>
        <div class="ui attached segment">
          <form id="groupForm" class="ui form">
            <div class="field">
              <label>新建小组成员姓名</label>
              <input type="text" id="memberName" name="memberName" placeholder="输入姓名">
            </div>
            <div class="field">
              <label>新建小组成员学号</label>
              <input type="text" id="memberId" name="memberId" placeholder="输入学号">
            </div>
            <input type="hidden" name="AID" value="<%= request.getParameter("AID") %>">
            <button class="ui blue button" type="button" id="submitButton">创建</button>
          </form>
        </div>
      </div>
      <!-- ...右边部分结束... -->
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
  $(document).ready(function() {
    $('#submitButton').click(function() {
      var memberName = $('#memberName').val();
      var memberId = $('#memberId').val();
      var AID = $('input[name="AID"]').val();

      if (memberName == "" || memberId == "") {
        // 如果输入框中没有任何内容就点击了提交按钮，弹出提示框
        $('#alertContent').text('请输入您所添加的小组成员姓名和学号！');
        $('#alertModal').modal('show');
      } else {
        // 构造表单数据对象
        var formData = {
          memberName: memberName,
          memberId: memberId,
          AID: AID
        };

        // 提交表单数据到Servlet
        $.post("AddGroupServlet", formData)
                .done(function(data) {
                  // 在点击表单中的提交按钮之后，根据Servlet返回的消息进行处理
                  if (data.indexOf("成功") !== -1) {
                    // 成功加入小组
                    $('#alertContent').text(data);
                    $('#alertModal').modal('show');

                    // 点击确定按钮后跳转到GroupViewServlet
                    $('#confirmButton').click(function() {
                      window.location.href = 'GroupViewServlet?AID=<%= request.getParameter("AID") %>';
                    });
                  } else {
                    // 新增小组失败或其他错误
                    $('#alertContent').text(data);
                    $('#alertModal').modal('show');
                  }

                  // 2秒后刷新页面
                  setTimeout(function() {
                    location.reload();
                  }, 2000);
                });
      }
    });
  });

</script>
</html>
