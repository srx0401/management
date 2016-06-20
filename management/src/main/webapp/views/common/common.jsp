<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
    
    response.addHeader("Cache-Control", "no-store, must-revalidate"); 
    response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
    
//完整访问路径:http://localhost:8080/extjs/views/pages/001/001-helloworld.jsp

//	当前文件相对路径(父目录),如:/views/pages/001/
String relativePath = request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
//	当前文件名,如:/001-helloworld.jsp
String fileName = request.getServletPath().substring(request.getServletPath().lastIndexOf("/"),request.getServletPath().length());
//	当前上下文路径,如:/extjs
String contextPath = request.getContextPath()+"/";
//	当前服务器路径,如:http://localhost:8080
String serverPath = "http://" + request.getServerName() + ":" + request.getServerPort() ;
//	当前绝对路径(父目录),如:http://localhost:8080/extjs/views/pages/001/
String absolutePath = serverPath + contextPath + relativePath;   

request.setAttribute("relativePath", relativePath);
request.setAttribute("fileName", fileName);
request.setAttribute("contextPath", contextPath);
request.setAttribute("serverPath", serverPath);
request.setAttribute("absolutePath", absolutePath);

String resourcesPath = contextPath + "resources/";  
String jqueryPath = resourcesPath + "jquery-1.8.3/";  
String easyuiPath = resourcesPath + "jquery-easyui/1.4.4/";  
String imagePath = resourcesPath + "images/";  
String jsPath = resourcesPath + "js/";  
String cssPath = resourcesPath + "css/";
String modulePath = jsPath + "modules/";

request.setAttribute("resourcesPath", resourcesPath);
request.setAttribute("jqueryPath", jqueryPath);
request.setAttribute("easyuiPath", easyuiPath);
request.setAttribute("imagesPath", imagePath);
request.setAttribute("jsPath", jsPath);
request.setAttribute("cssPath", cssPath);
request.setAttribute("modulePath", modulePath);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Thu, 01 Jan 1970 00:00:01 GMT"> 
<META HTTP-EQUIV="expires" CONTENT="0">
<title>Insert title here</title>
<style type="text/css">
	#rap{
		width:1280px;
		border: 2px solid #0000FF;
		MARGIN-RIGHT: auto;
		MARGIN-LEFT: auto;
		z-index: 2;
	}
	#rap-1{
		width:1152px;
		MARGIN-RIGHT: auto;
		MARGIN-LEFT: auto;
	}
</style>
	<link rel="stylesheet" type="text/css" href="${cssPath }list.css"/>
	
<link href="${cssPath }/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${easyuiPath }themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${easyuiPath }themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${cssPath }icon-extra.css" />
<script type="text/javascript" src="${jqueryPath }jquery.min.js"></script>
<script type="text/javascript" src="${easyuiPath }jquery.easyui.min.js"></script>
<script type="text/javascript" src="${jsPath }extend/datagrid/Jquery.datagrid.js"></script>
<script type="text/javascript" src="${jsPath }common/Jquery.md5.js"></script>
<script type="text/javascript">

var CONTEXT_PATH = "${contextPath}";
var RESOURCES_PATH = "${resourcesPath}";
var JQUERY_PATH = "${jqueryPath}";
var EASYUI_PATH = "${easyuiPath}";
var IMAGES_PATH = "${imagesPath}";
var JS_PATH = "${jsPath}";
var CSS_PATH = "${cssPath}";
var MODULE_PATH = "${modulePath}";

var DEFAULT_TIP_TITLE = "温馨提示";

jQuery.ajaxSetup({cache:false});//ajax不缓存  
/*
	if ($.fn.datetimespinner) {
		$.fn.datetimespinner.defaults.selections = [ [ 0, 4 ], [ 5, 7 ],
				[ 8, 10 ], [ 11, 13 ], [ 14, 16 ], [ 17, 19 ] ]
	}
	*/
	
</script>
</head>
<body>
	<script type="text/javascript" src="${jsPath }common/common.js"></script>
	<script type="text/javascript" src="${jsPath }common/message.js"></script>
</body>
</html>
