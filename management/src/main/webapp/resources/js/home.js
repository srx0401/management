var ctx = "/management/";
var _menus = {
	"menus": [{
		"menuid": "1",
		"icon": "icon-sys",
		"menuname": "系统功能",
		"menus": [
		{
			"menuid": "13",
			"menuname": "账户管理",
			"icon": "icon-account",
			"child": [{
				"menuid": "141",
				"menuname": "账户管理",
				"icon": "icon-account",
				"url": ctx + "account/home"
			},
			{
				"menuid": "151",
				"menuname": "dialog",
				"icon": "icon-set",
				"url": "easyui/dialog.html"
			}]
		},{
			"menuid": "14",
			"menuname": "财务管理",
			"icon": "icon-finance",
			"child": [
			{
				"menuid": "142",
				"menuname": "财务管理",
				"icon": "icon-finance",
				"url": ctx + "finance/home"
			}]
		}]
	},
	{
		"menuid": "8",
		"icon": "icon-sys",
		"menuname": "员工管理",
		"menus": [{
			"menuid": "21",
			"menuname": "datetimebox",
			"icon": "icon-nav",
			"url": "easyui/datetimebox.html"
		},
		{
			"menuid": "22",
			"menuname": "视频监控",
			"icon": "icon-nav",
			"url": "http://www.baidu.com",
			"child": [{
				"menuid": "221",
				"menuname": "tabs_href_test",
				"icon": "icon-nav",
				"url": "easyui/tabs_href_test.html"
			},
			{
				"menuid": "222",
				"menuname": "propertygrid",
				"icon": "icon-nav",
				"url": "easyui/propertygrid.html"
			}]
		}]
	},
	{
		"menuid": "56",
		"icon": "icon-sys",
		"menuname": "部门管理",
		"menus": [{
			"menuid": "31",
			"menuname": "treegrid",
			"icon": "icon-nav",
			"url": "easyui/treegrid.html"
		},
		{
			"menuid": "321",
			"menuname": "部门列表",
			"icon": "icon-nav",
			"url": "demo2.html",
			"child": [{
				"menuid": "311",
				"menuname": "validatebox",
				"icon": "icon-nav",
				"url": "easyui/validatebox.html"
			},
			{
				"menuid": "312",
				"menuname": "window",
				"icon": "icon-nav",
				"url": "easyui/window.html"
			}]
		}]
	},{
		"menuid": "57",
		"icon": "icon-sys",
		"menuname": "账户管理",
		"menus": [{
			"menuid": "31",
			"menuname": "treegrid",
			"icon": "icon-nav",
			"url": "easyui/treegrid.html"
		},
		{
			"menuid": "321",
			"menuname": "部门列表",
			"icon": "icon-nav",
			"url": "demo2.html",
			"child": [{
				"menuid": "311",
				"menuname": "validatebox",
				"icon": "icon-nav",
				"url": "easyui/validatebox.html"
			},
			{
				"menuid": "312",
				"menuname": "window",
				"icon": "icon-nav",
				"url": "easyui/window.html"
			}]
		}]
	}]
};


        function freshAD(){
        var AdHtml="<font color='red'>现在时间："+new Date().format("hh:mm:ss")+"&nbsp;&nbsp;今天："+getChineseCalendar()+"</font>";
		// var AdHtml="<font color='red'>现在时间："+new Date().format("hh:mm:ss")+"</font>";
        $("#opt_info").panel({title:AdHtml});
        setTimeout("freshAD()",500);
        }

        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 200,
                resizable:false
            });
        }
        //	关闭改密窗口
        function closeChangePass() {
        	
        	$("#form-changepass").form('clear');
            $('#w').window('close');
        }
		//	显示改密窗口
        function showChangePass(){
        	$('#w').window('open');
        }
        //	退出
		function exit(){
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                if (r) {
                   $.ajax( {
			        url : CONTEXT_PATH + "/user/exit",
			        type : "POST", 
			        dataType:"json",
			        contentType:'application/json;charset=UTF-8',
			        success : function(res) {
			        	if (res.url){
			        		location.href = res.url;
						}
			        },
			    error:function(e){
			        alert("error");   
			        }});
                }
	        });
		}
        //修改密码
        function changePass() {
        	var oldpassword = $('#oldpassword').val();
            var newpassword = $('#newpassword').val();
            var confirmpassword = $('#confirmpassword').val();

            if (oldpassword == "") {
                warningAlert("请输入原始密码.");
                return false;
            }
            if (newpassword == "") {
                warningAlert("请输入新的密码.");
                return false;
            }
			if (oldpassword == newpassword) {
				warningAlert("新密码不能和原始密码相同,请重新输入新密码.");
			    return false;
			}
            if (newpassword != confirmpassword) {
                warningAlert("修改密码和确认密码不一致,请重新输入.");
                return false;
            }
           	
            var form = $("#form-changepass");
            /*
            form.form('submit', {
				url : form.attr("action"),
				success : function(result){
            	var res = eval('('+result+')');
            	alert(res.msg);
    			if (res.success){
    				closeChangePass();
    			} else {
    			}
            },
				error : function(){
            	alert("error");
            }
			});
			*/
            $.ajax({
				url : form.attr("action"),
				type : "POST",
				async : false,
				data : {
					'oldpassword' : $.md5($("#oldpassword").val()),
					'newpassword' : $.md5($("#newpassword").val())
				},
				dataType : "json",
				success : function(res) {
					alert(res.msg);
	    			if (res.success){
	    				closeChangePass();
	    			}
				},
				error : function(e) {
					alert("err");
				}
			});
        }
        $(function() {
             freshAD();
             openPwd();
        });