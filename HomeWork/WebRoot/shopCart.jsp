<%@ page language="java" import="java.util.*" import="com.aode.userDao.*" import="com.aode.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shopCart.jsp' starting page</title>
    

  </head>
  
  <body>
  <% 
  	String username = (String)request.getSession().getAttribute("username");
  	int userId = (int)request.getSession().getAttribute("userId");
  	Shop[] shopList = UserDao.selectToCart(userId);
  	%>
  	<h1 align="center"><%=username %>的购物车</h1>
  	<div align="left">
			<h3>商品名&nbsp;&nbsp;价格&nbsp;&nbsp;上架时间</h3>
			<% 
				if(shopList!=null){
				for(int i = 0;i<shopList.length;i++){
			 %>
			<h4><a><%=shopList[i].getShopName() %></a>
			&nbsp;&nbsp;&nbsp;<%=shopList[i].getPrvice() %>
			&nbsp;&nbsp;&nbsp;<%=shopList[i].getTime() %>
			&nbsp;&nbsp;&nbsp;<a href="/HomeWork/UserAction?choose=deletCart&shopId=<%=shopList[i].getId() %>&userId=<%=userId%>">删除</a></h4>
			<% }}else{
				out.print("你还没有添加任何商品");			
			}
			 %>
			 <a href="userHome.jsp">返回主页</a>
		</div>
  </body>
</html>
