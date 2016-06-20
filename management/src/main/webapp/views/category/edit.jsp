<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>category</title>
	<%@ include file="../common/common.jsp"  %>
</head>

<body>
	<br/><br/><br/>	
	<div id="rap">
		<div id="rap-1">
			<br/>
			<h1 align="center">新增类型</h1>
			<form action="${contextPath }/category/save" method="post">
				<input type="hidden" id="id" name="id" value="${res.id }"/><br/>
				类别名称:<input id="name" name="name" value="${res.name }"/><br/>
				<!-- 
				<input type="hidden" id="createTime" name="createTime" value="${res.createTime }"/><br/>
				<input type="hidden" id="modifyTime" name="modifyTime" value="${res.modifyTime }"/><br/>
				 -->
				<input type="submit" />
			</form>
						<br/><br/>
			<a href="${contextPath }/category/home.asp">类别管理</a><br />
			<br/><br/><br/><br/>
		</div>
	</div>
</body>
</html>