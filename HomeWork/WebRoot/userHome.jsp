<%@ page language="java" import="java.util.*" import="com.aode.userDao.*" import="com.aode.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userHome.jsp' starting page</title>
	

  </head>
  <% 
  String username = (String)request.getSession().getAttribute("username");
  String password = (String)request.getSession().getAttribute("password");
  User user = UserDao.userLogin(username, password);
  int[] checkbox ={0,0,0,0};
  for(int i = 0;i<user.getHobby().length;i++){
  	switch(user.getHobby()[i]){
  	case "篮球": checkbox[0] = 1;break;
  	case "乒乓球": checkbox[1] = 1;break;
  	case "排球": checkbox[2] = 1;break;
  	case "跑步": checkbox[3] = 1;break;
  	}
  }
  request.getSession().setAttribute("userId", user.getId());
  %>
  <body>
  	<div align="center">
  	<h1>个人主页</h1>
  		<form action="/HomeWork/UserAction" name="myform" onsubmit="return text()" method="post">
  		<table border="1">
				<tr><td>用户名</td><td><%=user.getUsername() %></td></tr>
				<% if(user.getSex().equals("男")){ %>
				<tr><td>性别</td><td><input type="radio" name="sex" value="男" checked="checked" />男&nbsp;&nbsp;
				<input type="radio" name="sex" value="女" />女</td></tr>
				<%}else{ %>
				<tr><td>性别</td><td><input type="radio" name="sex" value="男" />男&nbsp;&nbsp;
				<input type="radio" name="sex" value="女" checked="checked" />女</td></tr>
				<%}  if(checkbox[0]==1){%>
				<tr><td>爱好</td><td><input type="checkbox" name="hobby" value="篮球" checked="checked"/>篮球&nbsp;
				<%}else{ %>
				<tr><td>爱好</td><td><input type="checkbox" name="hobby" value="篮球"/>篮球&nbsp;
				<%} if(checkbox[1]==1){%>
						<input type="checkbox" name="hobby" value="乒乓球" checked="checked"/>乒乓球&nbsp;
				<%}else{ %>
						<input type="checkbox" name="hobby" value="乒乓球" />乒乓球&nbsp;
				<%} if(checkbox[2]==1){%>
						<input type="checkbox" name="hobby" value="排球" checked="checked"/>排球&nbsp;
				<%}else{ %>
						<input type="checkbox" name="hobby" value="排球" />排球&nbsp;
				<%} if(checkbox[3]==1){%>
						<input type="checkbox" name="hobby" value="跑步" checked="checked"/>跑步</td></tr>
				<%}else{ %>
						<input type="checkbox" name="hobby" value="跑步" />跑步</td></tr>
				<%} %>
				<tr><td colspan="2">
				<a href="updateMessage.jsp">信息修改页面</a>
				<a href="userShop.jsp">进入商店</a>&nbsp;&nbsp;&nbsp;
				<a href="shopCart.jsp">进入购物车</a>&nbsp;&nbsp;&nbsp;
				<a href="main.jsp">注销</a></td></tr>
				</table>
			</form>
			
  	</div>
  </body>
</html>
