<%@ page language="java" import="java.util.*" import="com.aode.bean.*" import="com.aode.userDao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bookShop.jsp' starting page</title>
    
	

  </head>
  
  <body>
  	<%
  		Shop[] shopList =  UserDao.selectBook();
  	%>
  	<div align="center">
  	<h1>管理员商城管理</h1>
  		<table border="1">
  			<tr><th colspan="4">商品列表</th></tr>
  			<tr><td>商品名</td><td>价格</td><td>上架时间</td><td>操作</td></tr>
  			<% if(shopList!=null){
  				for(int i = 0;i<shopList.length;i++){
  			 %>
  			 <tr><td><%=shopList[i].getShopName() %></td>
  			 	 <td><%=shopList[i].getPrvice() %></td>
  			 	 <td><%=shopList[i].getTime() %></td>
  			 	 <td><a href="/HomeWork/ManagerAction?choose=deletShop&id=<%=shopList[i].getId() %>">下架</a></td></tr>
  			 <% }} %>
  			 <tr><td height="100px" colspan="4">
  			 	<form action="/HomeWork/ManagerAction" method="post">
  			 		商品名<input type="text" name="shopName">
  			 		价格<input type="text" name="prvice">
  			 		<input type="hidden" value="addShop" name="choose">
  			 			<input type="submit" value="添加商品">
  			 	</form>
  			 	<a href="managerHome.jsp">返回上一页</a>
  			 </td></tr>
  		</table>
  	</div>
  </body>
</html>
