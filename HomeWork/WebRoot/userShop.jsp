<%@ page language="java" import="java.util.*" import="com.aode.bean.*" import="com.aode.userDao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userShop.jsp' starting page</title>
    
	

  </head>
  
  <body>
  <%
  		Shop[] shopList = (Shop[])request.getSession().getAttribute("UserShop");
  		if(shopList==null){
  		shopList =  UserDao.selectBook();
  		}
  		int userId = (int)request.getSession().getAttribute("userId");
  	%>
  	<div >
			<h1>奥德搜索</h1>
			<form action="/HomeWork/UserAction" method="post">
				<input type="text" name="shopName"/>
				<input type="hidden" name="choose" value="selectShop">
				<input type="submit" value="查询"/>
			</form>
		</div>
		<div align="left">
			<h3>商品名&nbsp;&nbsp;价格&nbsp;&nbsp;上架时间</h3>
			<% 
				for(int i = 0;i<shopList.length;i++){
			 %>
			<h4><a><%=shopList[i].getShopName() %></a>
			&nbsp;&nbsp;&nbsp;<%=shopList[i].getPrvice() %>
			&nbsp;&nbsp;&nbsp;<%=shopList[i].getTime() %>
			&nbsp;&nbsp;&nbsp;<a href="/HomeWork/UserAction?choose=addUserShop&shopId=<%=shopList[i].getId() %>&userId=<%=userId%>">加入购物车</a></h4>
			<% } %>
			<a href="userHome.jsp">返回主页</a>
		</div>
  </body>
</html>
