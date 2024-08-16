<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功页面</title>
</head>
<body>

登陆成功

	<shiro:hasPermission name="/useradd">
		我就是我
	</shiro:hasPermission>
	

	<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>addr</th>
			<th>age</th>
			<th>修改</th>
			<th>删除</th>
		</tr>
		
		<tr>
			<th>1</th>
			<th>a</th>
			<th>a</th>
			<th>1</th>
			<th><shiro:hasPermission name="/aaa"><a href="bbb">修改</a></shiro:hasPermission></th>
			<th><shiro:hasPermission name="/bbb"> 删除</shiro:hasPermission></th>
		</tr>
		
		
		<tr>
			<th>2</th>
			<th>b</th>
			<th>b</th>
			<th>2</th>
			<th><shiro:hasPermission name="/aaa"> <a href="aaa">修改</a></shiro:hasPermission></th>
			<th><shiro:hasPermission name="/bbb"> 删除</shiro:hasPermission></th>
		</tr>
	</table>

	<a href="shiro-logout">注销</a>

	<%-- ==<shiro:principal></shiro:principal>==
	
	<table border="1">
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>性别</td>
			<td>操作</td>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td><shiro:hasPermission name="user/update">修改</shiro:hasPermission>
			<shiro:hasPermission name="user/delete">删除</shiro:hasPermission>
				<shiro:hasRole name="系统管理员" >系统管理员</shiro:hasRole>
				<shiro:hasRole name="管理员" >管理员</shiro:hasRole>
			</td>
		</tr>		
	</table> --%>
	
</body>
</html>