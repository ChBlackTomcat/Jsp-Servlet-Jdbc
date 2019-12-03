<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    

  </head>
  
  <body>
  <div align="center">
  	<h1>用户登录</h1>
  	<form action="/HomeWork/UserAction" method="post">
  	<table border="1">
		  		<tr><td>用户名</td><td><input type="text" placeholder="请输入用户名" name="username"/></td></tr>
				<tr><td>密码</td><td><input type="password" placeholder="请输入密码" name="password"/></td></tr>
				<tr><td colspan="2"><input type="hidden" value = "login" name="choose"><input type = "submit" value="登录"></td></tr>
				<tr><td><a href="regist.jsp">点击注册</a></td><td><a href="managerRegist.jsp">管理员登录</a></td></tr>
	</table>
	</form>
  </div>
  </body>
</html>
