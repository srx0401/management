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
	
	
	<div class="easyui-panel" title="查询条件" style="width:100%">
		<div style="padding:10px 60px 10px 60px">
	    <form id="ff" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>Name:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		
	    			<td>Email:</td>
	    			<td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"></input></td>
	    		
	    			<td>Subject:</td>
	    			<td><input class="easyui-textbox" type="text" name="subject" data-options="required:true"></input></td>
	    		
	    			
	    			<td>Language:</td>
	    			<td>
	    				<select class="easyui-combobox" name="language"><option value="ar">Arabic</option><option value="bg">Bulgarian</option><option value="ca">Catalan</option><option value="zh-cht">Chinese Traditional</option><option value="cs">Czech</option><option value="da">Danish</option><option value="nl">Dutch</option><option value="en" selected="selected">English</option><option value="et">Estonian</option><option value="fi">Finnish</option><option value="fr">French</option><option value="de">German</option><option value="el">Greek</option><option value="ht">Haitian Creole</option><option value="he">Hebrew</option><option value="hi">Hindi</option><option value="mww">Hmong Daw</option><option value="hu">Hungarian</option><option value="id">Indonesian</option><option value="it">Italian</option><option value="ja">Japanese</option><option value="ko">Korean</option><option value="lv">Latvian</option><option value="lt">Lithuanian</option><option value="no">Norwegian</option><option value="fa">Persian</option><option value="pl">Polish</option><option value="pt">Portuguese</option><option value="ro">Romanian</option><option value="ru">Russian</option><option value="sk">Slovak</option><option value="sl">Slovenian</option><option value="es">Spanish</option><option value="sv">Swedish</option><option value="th">Thai</option><option value="tr">Turkish</option><option value="uk">Ukrainian</option><option value="vi">Vietnamese</option></select>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
	    </div>
	    </div>
	</div>
	
	<div style="padding:10px"></div>
	<table id="dg" class="easyui-datagrid" style="padding:50px 60px 50px 60px;" align="center">
		<thead>
			<tr>
				<th data-options="field:'itemid'">Item ID</th>
				<th data-options="field:'productid'">Product</th>
				<th data-options="field:'listprice',align:'right'">List Price</th>
				<th data-options="field:'attr1'">Attribute</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>EST-1</td><td>FI-SW-01</td><td>36.50</td><td>Large</td>
			</tr>
		</tbody>
	</table>
	<table id="list-data" class="easyui-datagrid" style="padding:50px 60px 50px 60px;" align="center"></table>
	
	<div id="form-div" class="easyui-dialog" style="width:400px;height:320px;padding:20px 0px 20px 60px"
		closed="true" buttons="#dlg-buttons">
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
<script type="text/javascript">
	//	默认的FORM ID
	var FORM_DIV = "form-div";
	var FORM_ID = "form-data";
	
	var LIST_ID = "list-data";
	
	var TIP_TITLE = "温馨提示";
	
	var SAVE_URL = "${contextPath }account/saveByAjax";
	var UPDATE_URL = "${contextPath }account/updateByAjax";
	var REMOVE_URL = "${contextPath }account/removeLinesByAjax";
	var LIST_URL = "${contextPath }account/listByAjax";
	
	$(function(){
		initList();
	});
	//	初始化数据结果
	function initList(){
		$("#" + LIST_ID).datagrid({ 
	        title:'查询结果', 
	        //iconCls:'icon-edit',//图标 
	        width: '80%', 
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
			$.messager.alert(TIP_TITLE,"请选择一条数据记录.","warning");
			return;
		}
    	$.messager.confirm(TIP_TITLE, "您确定要删除所选记录[" + rows.length + "]吗?", function(r){
			if (r){
				//alert('confirmed:'+r);
				var ids = [];
				for(var i=0; i<rows.length; i++){
					ids.push(rows[i].id);
				}
				
			    $.ajax( {
			        url : REMOVE_URL,
			        type : "POST", 
			        dataType:"json",
			        contentType:'application/json;charset=UTF-8',
			        data:JSON.stringify(ids),
			        success : function(res) {
			        	if (res.success){
			        		$.messager.show({
		    					title:TIP_TITLE,
		    					msg:res.msg,
		    					timeout:5000,
		    					showType:'slide'
		    				});
							$("#" + LIST_ID).datagrid('reload');
						} else {
							$.messager.alert(TIP_TITLE,res.msg,"error");
						}
			        },
			    error:function(e){
			        alert("err");   
			        }});
			}
		});
    }
    function save(){
    	var form = $("#" + FORM_ID);
    	/**/
    	form.form('submit',{
    		url: form.attr("action"),
    		onSubmit: function(){
    			//return $(this).form('validate');
    			return $(this).form('enableValidation').form('validate');
    		},
    		success: function(result){
    			
    			var res = eval('('+result+')');
    			if (res.success){
    				/*
    				$.messager.alert('提示','保存成功');
    				*/
    				$.messager.show({
    					title:TIP_TITLE,
    					msg:res.msg,
    					timeout:5000,
    					showType:'slide'
    				});
    				closeForm();		
    				$("#" + LIST_ID).datagrid('reload');	
    			} else {
    				/*
    				$.messager.show({
    					title: 'Error',
    					msg: res.msg
    				});
    				*/
    				$.messager.alert(TIP_TITLE,res.msg,"error");
    			}
    			/**/
    		},error:function(){
    			alert("error");
    		}
    	});
    	
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