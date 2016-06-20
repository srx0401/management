<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<%@ include file="views/common/common.jsp"  %>
	<style type="text/css">
		a{text-decoration:none;color:black;}
		body{background-color:#DDD;}
		</style>
        
<script>
	var LOGIN_FORM_ID = "form-login";
    function checkInfo(){
    	var form = $("#" + LOGIN_FORM_ID);
    	var username = $("#username").val();
    	var password = $("#password").val();
    	var verifycode = $("#verifycode").val();
    	
    	if(!username){
    		$.messager.alert(DEFAULT_TIP_TITLE,"请输入用户名","warning");
    		return false;
    	}
    	if(!password){
    		$.messager.alert(DEFAULT_TIP_TITLE,"请输入密码","warning");
    		return false;
    	}
    	
    	if(!verifycode){
    		$.messager.alert(DEFAULT_TIP_TITLE,"请输入验证码","warning");
    		return false;
    	}
    	
		if(!checkCode(verifycode)){
			changeImg();
			return false;
		}
		return true;
    }
    /*登陆*/
    function login(){
    	if(!checkInfo()){
    		return;
    	}
    	var form = $("#" + LOGIN_FORM_ID);
    	
    	/*
    	ajaxSubmit($("#" + LOGIN_FORM_ID),function(result){
    		var res = eval('('+result+')');
			if (res.success){
				location.href = res.url;
			} else {
				$.messager.alert(DEFAULT_TIP_TITLE,res.msg,"error");
				changeImg();
			}
        });
    	*/
    	$.ajax({
			url : form.attr("action"),
			type : "POST",
			async:false,
			data : {
			      'username' : $("#username").val(),
			      'password' :$.md5($("#password").val())
			     },  
			dataType: "json",  
			success : function(res) {
				if (res.success){
					location.href = res.url;
				} else {
					$.messager.alert(DEFAULT_TIP_TITLE,res.msg,"error");
					changeImg();
				}
			},
			error : function(e) {
				alert("err");
			}
		});
    }
    /*校验验证码*/
	function checkCode(code){
		var r = true;
		$.ajax({
			url : CONTEXT_PATH + "code/verify?verifycode="+code,
			type : "POST",
			async:false,
			success : function(res) {
				if (!res.success) {
					$.messager.alert(DEFAULT_TIP_TITLE, res.msg,"error");
					r = false;
				}
			},
			error : function(e) {
				alert("err");
				r = false;
			}
		});
		return r;
	}
	function changeImg() {
        var imgSrc = $("#code");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }
    //时间戳   
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
</script>
</head>
<body>

<div style="margin:0 auto;width:800px;">

<div id="win2" class="easyui-window" minimizable="false" maximizable="false"  collapsible="false"  
	title="登陆窗口" style="width:360px;height:280px;">
    <form action="${contextPath }user/login" method="post" style="padding:10px 40px 10px 60px;" name="form-login" id="form-login">
   		<table cellpadding="5">
	    		<tr>
	    			<td>用户名&nbsp;:</td>
	    			<td><input class="easyui-textbox" name="username" id="username"></input></td>
	    		</tr>
	    		<tr>
	    			<td>口&nbsp; &nbsp;令&nbsp;:</td>
	    			<td><input class="easyui-textbox" type="password" name="password" id="password"></input></td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td><img id="code" src="${contextPath }code/load" width="100%" onclick="changeImg()"/></td>
	    		</tr>
	    		<tr>
	    			<td>验证码&nbsp;:</td>
	    			<td><input class="easyui-textbox" name="verifycode" id="verifycode"></input></td>
	    		</tr>
	    </table>
    </form>
    <div style="text-align:center;padding:20px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" id="btn-save" icon="icon-ok" onclick="login()">登 &nbsp;&nbsp;陆&nbsp;&nbsp;</a>
	    	<!-- 
	    	<a href="javascript:void(0)" class="easyui-linkbutton" id="btn-cancel" icon="icon-cancel" onclick="javascript:closeForm()">取 &nbsp;消</a>
	     -->
	    </div>
</div>
</div>

</body>
</html>
