<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'managerRegist.jsp' starting page</title>
    
	

  </head>
  
  <body>
  	<div align="center">
  		<h1>管理员登录</h1>
  		<form action="/HomeWork/ManagerAction" method="post">
  		<table border="1">
  			<tr><td>账号</td><td><input type="text" name="account"></td></tr>
  			<tr><td>密码</td><td><input type="password" name="password"></td></tr>
  			<tr><td><input type="hidden" name="choose" value="login">
  			    	<input type="submit" value="登录"></td><td><a href = "main.jsp">返回登录</a></td></tr>
  		</table>
  		</form>
  	</div>
  </body>
</html>
