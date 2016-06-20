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
			<h1 align="center">类型管理</h1>
			<a href="#" onclick="javascript:document.getElementById('d_form1').style.display='block'">NEW</a>
			<div id="d_form1" style="display:none">
			<br/><br/>
				<form action="${contextPath }/category/save.asp" method="post">
				类别名称:<input id="name" name="name"/><br/>
				<!-- 
				父类别:<select id="parent" name="parent">
					
				</select><br/>
				 -->
				<input type="submit" />
			</form>
			<br/><br/>
			</div>
			
			<table border=1 align="center">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>CREATETIME</th>
						<th>MODIFYTIME</th>
						<th colspan="3">OPERATION</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${res }" var="obj">
						<tr>
							<td>${obj.id }</td>
							<td>${obj.name }</td>
							<td>${obj.createTime }</td>
							<td>${obj.modifyTime }</td>
							<td><a href="${contextPath }/category/edit?id=${obj.id}">UPDATE</a></td>
							<td><a href="${contextPath }/category/remove?id=${obj.id}">DELETE</a></td>
							<td><a href="${contextPath }/category/detail?id=${obj.id}">DETAIL</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br/><br/>
			<a href="${contextPath }/category/home.asp">类别管理</a><br />
			<br/><br/><br/><br/>
		</div>
	</div>
</body>
</html>