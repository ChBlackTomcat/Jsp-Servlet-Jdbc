<%@ page language="java" import="java.util.*" import="com.aode.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userMessage.jsp' starting page</title>
    
	

  </head>
  
  <body>
  <% 
			List<User> userList = (ArrayList)request.getSession().getAttribute("userList");
			String flag = request.getParameter("id");
			int id = Integer.parseInt(flag);
			String hobby = "";
			for(int i = 0;i<userList.get(id).getHobby().length;i++){
				hobby += userList.get(id).getHobby()[i];
			}
		%>
  	<div align="center">
  		<table border="1" width="200px" height="200px">
  			<tr><th colspan="2">用户基本信息</th></tr>
  			<tr><td>用户名</td><td><%=userList.get(id).getUsername() %></td></tr>
  			<tr><td>性别</td><td><%=userList.get(id).getSex() %></td></tr>
  			<tr><td>爱好</td><td><%=hobby %></td></tr>
  			<tr><td colspan="2"><a href="/HomeWork/ManagerAction?choose=audit&flag=1&id=<%=id%>">通过</a>&nbsp;&nbsp;
  								<a href="/HomeWork/ManagerAction?choose=audit&flag=0&id=<%=id%>">不通过</a>
  			</td></tr>
  		</table>
  	</div>
  </body>
</html>
