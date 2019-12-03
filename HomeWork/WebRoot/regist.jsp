<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<script>
			
			function text(){
				var x = document.forms["myform"]["username"].value;
				var y = document.forms["myform"]["password"].value;
				var z = document.getElementsByName("hobby");
				var flag = 0;
				if((x==null||x=="")&&(y==null||y=="")){
						alert("用户名信息不能为空");
						return false;
				}else if(x==null||x==""){
						alert("用户名不能为空");
						return false;
				}else if(y==null||y==""){
						alert("密码不能为空");
						return false;
				}else{
					for(var i = 0;i<z.length;i++){
						if(z.item(i).checked==true){
							flag = 1;
							break;
						}
					}
					if(!flag){
						alert("爱好至少选择一个");
						return false;
					}
				}
				
				
									
			}
		</script>

  </head>
  
  <body>
  		<div align="center">
			<h1>注册页面</h1>
			<form action="/HomeWork/UserAction" name="myform" onsubmit="return text()" method="post">
			<table border="1">
				<tr><td>用户名</td><td><input type="text" name="username" /></td></tr>
				<tr><td>密码</td><td><input type="password" name="password" /><br /></td></tr>
				<tr><td>性别</td><td><input type="radio" name="sex" value="男" checked="checked" />男&nbsp;&nbsp;
				<input type="radio" name="sex" value="女" />女<br /></td></tr>
				<tr><td>爱好</td><td>&nbsp;<input type="checkbox" name="hobby" value="篮球" />篮球&nbsp;
				<input type="checkbox" name="hobby" value="乒乓球" />乒乓球&nbsp;
				<input type="checkbox" name="hobby" value="排球" />排球&nbsp;
				<input type="checkbox" name="hobby" value="跑步" />跑步<br />
				<input type="hidden" name="choose" value="regist" /></td></tr>
				<tr><td><input type="submit" value="注册" /></td><td><a href = "main.jsp">返回登录</a></td></tr>
			</table>
			</form>
		</div>
  </body>
</html>
