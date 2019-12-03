<%@ page language="java" import="java.util.*" import="com.aode.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'managet.jsp' starting page</title>
	

  </head>
  
  <body>
  	<div align="center">
		<h1>欢迎管理员</h1>
		<table border="1" width="400px">
		<tr><td>待审核用户</td></tr>
		<% 
			List<User> userList = (ArrayList)request.getSession().getAttribute("userList");
			if(userList!=null){
			for(int i = 0;i<userList.size();i++){
				String username = userList.get(i).getUsername();
				out.print("<tr><td><a href=/HomeWork/userMessage.jsp?id="+i+">"+username+"</a></td></tr>");
			}
			if(userList.size()==0){%>
			<tr><td>没有新用户</td></tr>
		<% }}%>
		<tr><td><a href="shop.jsp">商城管理</a>&nbsp;&nbsp;
		<a href="main.jsp">返回登录界面</a>
		</td></tr>
		</table>
		</div>
  </body>
</html>
