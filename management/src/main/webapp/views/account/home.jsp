<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta charset="UTF-8">
	<title></title>
<%@ include file="../../views/common/common.jsp"  %>
<style>
	.datagrid-header-row td {
	/*
		background-color: blue;
		color: #fff;
		*/
		font-weight: bolder;
	}
</style>
<script type="text/javascript">
/*
alert(CONTEXT_PATH);
alert(RESOURCES_PATH);
alert(JQUERY_PATH);
alert(EASYUI_PATH);
alert(IMAGES_PATH);
alert(JS_PATH);
alert(CSS_PATH);
alert(MODULE_PATH);
*/
var MODULE = "account";

</script>
<script type="text/javascript" src="${modulePath}account/home.js"></script>
	
</head>
<body>
	<div class="easyui-panel" title="查询条件" style="width:100%">
		<div style="padding:10px 20px 10px 60px">
	    <form id="form-query" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>账户名称:</td>
	    			<td>
	    				<input class="easyui-textbox" name="name" ></input>
	    			</td>
	    		
	    			<td>登陆用户:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="username"></input>
	    			</td>
	    		
	    			<td>登陆地址:</td>
	    			<td>
	    				<input class="easyui-textbox" type="text" name="address"></input>
	    			</td>
	    		
	    			<td>
<!-- 	    			关 键 字: -->
	    			</td>
	    			<td>
<!-- 	    				<select class="easyui-combobox" name="language"><option value="ar">Arabic</option><option value="bg">Bulgarian</option></select> -->
<!-- 	    				<input class="easyui-textbox" type="text" name="keyword"></input> -->
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>创建时间(>=):</td>
	    			<td>
	    				<input class="easyui-datetimespinner" name="createTime1" data-options="showSeconds:true"></input>
	    			</td>
	    		
	    			<td>创建时间(<):</td>
	    			<td>
    					<input class="easyui-datetimespinner" name="createTime2" data-options="showSeconds:true"></input>
	    			</td>
	    		
	    			<td>更新时间(>=):</td>
	    			<td>
	    				<input class="easyui-datetimebox" name="modifyTime1" editable="false" data-options="showSeconds:true,formatter:formatter1,parser:parser1"></input>
	    			</td>
	    		
	    			<td>更新时间(<):</td>
	    			<td>
	    				<input class="easyui-datetimebox" name="modifyTime2" editable="false" data-options="showSeconds:true,formatter:formatter1,parser:parser1"></input>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:right;padding:10px 0px 10px 150px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="_search()">查 询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" onclick="_clear()">重 置</a>
	    </div>
	    </div>
	</div>
	
	<div style="padding:10px"></div>
	<table id="table-list" class="easyui-datagrid" style="width:100%;height:350px"
		url="${contextPath }/account/listByAjax" toolbar="#toolbar"
		title="查询结果" iconCls="icon-save"
		rownumbers="true" pagination="true" fitColumns="true" striped="true" 
		data-options="onLoadSuccess: onLoadSuccess">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th field="name" width="15" halign="center" style="font-weight: bolder;">账户名称</th>
			<th field="username" width="15" halign="center">登陆用户</th>
			<th field="password" width="15" halign="center">登陆密码</th>
			<th field="address" width="15" halign="center">登陆地址</th>
			<th field="prompt" width="15" halign="center">提示信息</th>
			<th field="remark" width="20" halign="center">备注</th>
			<th field="createTime" width="20" halign="center">创建时间</th>
			<th field="modifyTime" width="20" halign="center">更新时间</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" title="添加一个账户信息" onclick="add()">添加</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" title="修改当前选择的账户信息" onclick="edit()">修改</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" title="删除当前选择的账户信息" onclick="rm()">删除</a>
</div>
	
	
	<div id="form-div" class="easyui-dialog" style="width:400px;height:320px;padding:20px 0px 20px 60px"
		closed="true" buttons="#dlg-buttons" iconCls="icon-add">
<!-- 	<div class="ftitle" style="color:red">温馨提示:</div> -->
	<form id="form-data" class="easyui-form" method="post" action="#" data-options="novalidate:true">
		<input type="hidden" name="id" id="id"/>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>账户名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>登陆用户:</td>
	    			<td><input class="easyui-textbox" type="text" name="username" data-options="required:true,validType:'username'"></input></td>
	    		</tr>
	    		<tr>
	    			<td>登陆密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>登陆地址:</td>
	    			<td><input class="easyui-textbox" type="text" name="address" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>提示信息:</td>
	    			<td><input class="easyui-textbox" type="text" name="prompt" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>备注内容:</td>
	    			<td><input class="easyui-textbox" name="remark" data-options="multiline:true" style="height:60px"></input></td>
	    		</tr>
	    		<!-- 
	    		<tr>
	    			<td>Language:</td>
	    			<td>
	    				<select class="easyui-combobox" name="language"><option value="ar">Arabic</option><option value="bg">Bulgarian</option><option value="ca">Catalan</option><option value="zh-cht">Chinese Traditional</option><option value="cs">Czech</option><option value="da">Danish</option><option value="nl">Dutch</option><option value="en" selected="selected">English</option><option value="et">Estonian</option><option value="fi">Finnish</option><option value="fr">French</option><option value="de">German</option><option value="el">Greek</option><option value="ht">Haitian Creole</option><option value="he">Hebrew</option><option value="hi">Hindi</option><option value="mww">Hmong Daw</option><option value="hu">Hungarian</option><option value="id">Indonesian</option><option value="it">Italian</option><option value="ja">Japanese</option><option value="ko">Korean</option><option value="lv">Latvian</option><option value="lt">Lithuanian</option><option value="no">Norwegian</option><option value="fa">Persian</option><option value="pl">Polish</option><option value="pt">Portuguese</option><option value="ro">Romanian</option><option value="ru">Russian</option><option value="sk">Slovak</option><option value="sl">Slovenian</option><option value="es">Spanish</option><option value="sv">Swedish</option><option value="th">Thai</option><option value="tr">Turkish</option><option value="uk">Ukrainian</option><option value="vi">Vietnamese</option></select>
	    			</td>
	    		</tr>
	    		 -->
	    	</table>
	    </form>
	    <div style="text-align:center;padding:20px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" id="btn-save" onclick="save()">保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" id="btn-cancel" onclick="javascript:closeForm()">取消</a>
	    </div>
</div>
</body>

</html>