<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<%@ include file="../common/common.jsp"  %>

<script type="text/javascript" src='${jsPath }XiuCai.index.js'> </script>
<script type="text/javascript" src="${jsPath }common/ChineseCalendar.js"></script>
<script type="text/javascript" src="${jsPath }home.js"></script>
<style>
	#d-noscript{
		position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;
	}
	#loading-mask{
		position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000
	}
	#pageloading{
		position:absolute; top:50%; left:50%; 
		margin:-120px 0px 0px -120px; text-align:center;  
		border:2px solid #8DB2E3; width:200px; height:40px;  
		font-size:14px;padding:10px; font-weight:bold; 
		background:#fff; color:#15428B;
	}
	#area-north{
		overflow: hidden; height: 30px;
        background: url(${imagesPath}layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体
	}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<noscript>
		<div id="noscript">
			<img src="${imagesPath }noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div id="loading-mask">
		<div id="pageloading">
			<img src="${imagesPath }/loading.gif" align="absmiddle" />
			正在加载中,请稍候...
		</div>
	</div>
	<div region="north" split="true" border="false"
		id="area-north">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			${user.name } <a href="#" id="editpass">修改密码</a> <a href="#"
			id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;"><img
			src="${imagesPath }logo.gif" width="20" height="20"
			align="absmiddle" /> 管理系统 </span>
	</div>

	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer"></div>
	</div>

	<div region="west" split="true" title="导航菜单" style="width: 180px;"
		id="west">
		<div id="nav">
			<!--  导航内容 -->
		</div>
	</div>
	<div id="opt_info" border="false" region="center" title="小广告"
		style="background: #eee; overflow-y: hidden">

		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="padding: 20px; overflow: hidden; color: red;">
				<h1 style="font-size: 24px;">
					
				</h1>
			</div>
		</div>
	</div>

	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="refresh">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭此页</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="closeleft">关闭左侧</div>
		<div id="closeright">关闭右侧</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
</body>
</html>
