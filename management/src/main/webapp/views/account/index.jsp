<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>
<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/demo/demo.css">
<style type="text/css">
		#fm{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			color:#666;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
	</style>

<%@ include file="../common/common.jsp"  %>
<script src="${contextPath }/resources/jquery-1.8.3/jquery.min.js"></script>
<script src="${contextPath }/resources//jquery-easyui-1.4.4/jquery.easyui.min.js"></script>

<script type="text/javascript">
		var url;
		function add(){
			$('#dlg').dialog('open').dialog('setTitle','ADD');
			$('#fm').form('clear');
			url = '${contextPath }/account/saveByAjax';
		}
		function edit(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('setTitle','EDIT');
				$('#fm').form('load',row);
				url = "${contextPath }/account/saveByAjax?id=" + row.id;
			}
		}
		function saveAmount(){
			$('#fm').form('submit',{
				url: url,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
					var result = eval('('+result+')');
					if (result.success){
						$('#dlg').dialog('close');		// close the dialog
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({
							title: 'Error',
							msg: result.msg
						});
					}
				}
			});
		}
		function removeAccount(){
			var requestUrl = "${contextPath }/account/removeByAjax/";
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to remove the selected one?',function(r){
					if (r){
						$.post(requestUrl + row.id,{id:row.id},function(result){
							if (result.success){
								$.messager.show({	// show message
									title: 'Success',
									msg: result.msg
								});
								$('#dg').datagrid('reload');	// reload
							} else {
								$.messager.show({	// show error message
									title: 'Error',
									msg: result.msg
								});
							}
						},'json');
					}
				});
			}
		}
	</script>

</head>
<body>
<!-- 
	<h2>Basic CRUD Application</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip">&nbsp;</div>
		<div>Click the buttons on datagrid toolbar to do crud actions.</div>
	</div>
 -->
	<table id="dg" title="Account" class="easyui-datagrid" style="width:700px;height:250px"
			url="${contextPath }/account/listByAjax"
			toolbar="#toolbar" pagination="true"
			rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="name" width="50">账户名称</th>
				<th field="username" width="50">登陆用户</th>
				<th field="password" width="50">登陆密码</th>
				<th field="address" width="50">登陆地址</th>
				<th field="prompt" width="50">提示信息</th>
				<th field="remark" width="50">备注信息</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">Add</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">Edit</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeAccount()">Remove</a>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:480px;height:360px;padding:10px 20px"
			closed="true" buttons="#dlg-buttons">
		<div class="ftitle">Information</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>账户名称:</label>
				<input name="name" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>登陆用户:</label>
				<input name="username" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>登陆密码:</label>
				<input name="password" class="easyui-validatebox" required="true">
			</div>
		
			<div class="fitem">
				<label>登陆地址:</label>
				<input name="address" class="easyui-validatebox" />
			</div>
		
			<div class="fitem">
				<label>提示信息:</label>
				<input name="prompt" class="easyui-validatebox" />
			</div>
			<div class="fitem">
				<label>备注信息:</label>
				<!-- 
				<input name="remark" class="easyui-validatebox"/>
				 -->
				<textarea rows="5" cols="20" name="remark" class="easyui-validatebox"></textarea>
			</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAmount()">Save</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
</body>
</html>