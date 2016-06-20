<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta charset="UTF-8">
	<title></title>
<%@ include file="../../views/common/common.jsp"  %>
	<link rel="stylesheet" type="text/css" href="${easyuiPath }themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${easyuiPath }themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${cssPath }/list.css"/>
	<script type="text/javascript" src="${jqueryPath }jquery.min.js"></script>
	<script type="text/javascript" src="${easyuiPath }jquery.easyui.min.js"></script>
</head>
<body>
	<div style="margin:20px 0;">查询条件</div>
	<table id="list-data" class="easyui-datagrid"></table>
	<div id="form-div" class="easyui-dialog" style="width:400px;height:280px;padding:20px 0px 20px 60px"
		closed="true" buttons="#dlg-buttons">
<!-- 	<div class="ftitle" style="color:red">温馨提示:</div> -->
	<form id="form-data" class="easyui-form" method="post" action="#" data-options="novalidate:true">
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
<script type="text/javascript">
	//	默认的FORM ID
	var FORM_DIV = "form-div";
	var FORM_ID = "form-data";
	
	var LIST_ID = "list-data";
	
	var SAVE_URL = "${contextPath }account/saveByAjax";
	var UPDATE_URL = "${contextPath }account/saveByAjax";
	var REMOVE_URL = "${contextPath }account/removeByAjax";
	var LIST_URL = "${contextPath }account/listByAjax";
	
	$(function(){
		initList();
	});
	//	初始化数据结果
	function initList(){
		$("#" + LIST_ID).datagrid({ 
	        title:'查询结果', 
	        //iconCls:'icon-edit',//图标 
	        width: '100%', 
	        height: '350px', 
	        url:LIST_URL, 
	        //sortName: 'code', 
	        //sortOrder: 'desc', 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        frozenColumns:[[ 
	            {field:'ck',checkbox:true} 
	        ]], 
	        columns:[[
	                 { field: 'name', title: '账户名称', width: '15%' },
	                 { field: 'username', title: '登陆用户', width: '15%'},
	                 { field: 'password', title: '登陆密码', width: '15%'},
	                 { field: 'address', title: '登陆地址', width: '15%'},
	                 { field: 'prompt', title: '提示信息', width: '15%'},
	                 { field: 'remark', title: '备注', width: '20%'}
	                 ]],
	        toolbar: [{ 
	            text: '添加', 
	            iconCls: 'icon-add', 
	            handler: function() { 
	                //openDialog("add_dialog","add"); 
	            	//$.messager.alert('My Title','Here is a message!');
	            	add();
	            } 
	        }, '-', { 
	            text: '修改', 
	            iconCls: 'icon-edit', 
	            handler: function() { 
	                //openDialog("add_dialog","edit"); 
	                edit();
	            } 
	        }, '-',{ 
	            text: '删除', 
	            iconCls: 'icon-remove', 
	            handler: remove
	        }], 
	    }); 
	}
	function add(){
		openForm("添加");
		$("#" + FORM_ID).form('clear');
		$("#" + FORM_ID).attr("action",SAVE_URL);
		alert("#action");
	}
	function edit(){
		var rows = getSelections();
		if(!rows || rows.length < 1){
			$.messager.alert("温馨提示","请选择一条数据记录.","warning");
			return;
		}
		if (rows.length != 1){
			$.messager.alert("温馨提示","本系统暂时不支持多条数据记录同时编.","warning");
			return;
		}
		openForm("编辑");
		$("#" + FORM_ID).form('load',rows[0]);
		$("#" + FORM_ID).attr("action",UPDATE_URL);
	}
	//	删除	
    function remove(){
    	var rows = getSelections();
    	if(!rows || rows.length < 1){
			$.messager.alert("温馨提示","请选择一条数据记录.","warning");
			return;
		}
    	$.messager.confirm("提示", "您确定要删除所选记录[" + rows.length + "条]吗?", function(r){
			if (r){
				//alert('confirmed:'+r);
				//location.href = 'http://www.google.com';
				var ids = [];
				for(var i=0; i<rows.length; i++){
					ids.push(rows[i].id);
				}
				$.post(REMOVE_URL,ids,function(result){
					if (result.success){
						$("#" + LIST_ID).datagrid('reload');
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
			}
		});
    }
    function save(){
    	var form = $("#" + FORM_ID);
    	/**/
    	form.form('submit',{
    		url: form.attr("action"),
    		onSubmit: function(){
    			return $(this).form('validate');
    		},
    		success: function(result){
    			var result = eval('('+result+')');
    			if (result.errorMsg){
    				$.messager.show({
    					title: 'Error',
    					msg: result.errorMsg
    				});
    			} else {
    				closeForm();		
    				$("#" + LIST_ID).datagrid('reload');	
    			}
    		}
    	});
    	
    	alert("save");
    }
	    /**
	     * 
	     **/
	    function getSelections(tableid){
	    	tableid = tableid ? tableid : "list-data";
			var res = [];
			var rows = $('#' + tableid).datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				res.push(rows[i]);
			}
			return rows;
		}
	   
		function openForm(title){
			$('#' + FORM_DIV).dialog('open').dialog('setTitle',title);
		}
		function closeForm(){
			$('#' + FORM_DIV).dialog('close');
		}
	</script>
</html>