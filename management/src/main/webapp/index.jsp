<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="views/common/common.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>HOME</title>
	
	<link rel="stylesheet" type="text/css" href="${easyuiPath }themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${easyuiPath }themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${cssPath }/default.css" />
	<script type="text/javascript" src="${jqueryPath }jquery.min.js"></script>
	<script type="text/javascript" src="${easyuiPath }jquery.easyui.min.js"></script>
	<style type="text/css">
		#area-north{
			height:100px;
			background:#B3DFDA;
			padding:10px
		}
		#area-south{
			height:60px;
			background:#A9FACD;
			padding:10px;
		}
		#area-west{
			width:250px;
			padding:10px;
		}
		#area-east{
			width:180px;
			padding:10px
		}
	</style>
	<script src="${jsPath }index.js"></script>
	<script>
	window.onload = function(){
		$('#loading-mask').fadeOut();
		alert("onload");
		InitLeftMenu();
	}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" id="area-north"></div>
	<div data-options="region:'west',split:true,title:'West'" id="area-west">
		<div id="nav">
		    <!--  导航内容 -->
		    a
		 </div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" id="area-east"></div>
	<div data-options="region:'south',border:false" id="area-south"></div>
	<div data-options="region:'center',title:'Center'">
		
	</div>
</body>
</html>