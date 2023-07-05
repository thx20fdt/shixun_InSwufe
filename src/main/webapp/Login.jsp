<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>登录页面</title>
</head>
<body>
<form action="LoginServlet" method="post">
  <label>用户ID:</label>
  <input type="text" name="username" />
  <br/><br/>
  <label>密码:</label>
  <input type="password" name="password"/>
  <br/><br/>
  <input type="submit" value="登录"/>
</form>

<h3>test line</h3>

<%
  String msg = (String)request.getAttribute("error");
  if(msg != null){
%>
<label style="color:red"><%=msg %></label>
<%
  }
%>

</body>
</html>

</body>
</html>
