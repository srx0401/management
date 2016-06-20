	//	默认的FORM ID
	var FORM_DIV = "form-div";
	var FORM_ID = "form-data";
	var FORM_QUERY = "form-query";
	var TABLE_LIST = "table-list";
	
	var TIP_TITLE = "温馨提示";
	var TIP_ADD_TITLE = "添加一个新的用户";
	var TIP_EIDT_TITLE = "修改用户信息";
	var SAVE_URL = CONTEXT_PATH + MODULE + "/saveByAjax";
	var UPDATE_URL = CONTEXT_PATH + MODULE + "/updateByAjax";
	var REMOVE_URL =  CONTEXT_PATH + MODULE + "/removeByAjax";
	var LIST_URL =  CONTEXT_PATH + MODULE + "/listByAjax";
	
	function add(){
		openForm(TIP_ADD_TITLE,"icon-add");
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
		openForm(TIP_EIDT_TITLE,"icon-edit");
		$("#" + FORM_ID).form('load',rows[0]);
		$("#" + FORM_ID).attr("action",UPDATE_URL);
	}
	//	删除	
    function rm(){
    	var rows = getSelections();
    	if(!rows || rows.length < 1){
			$.messager.alert(TIP_TITLE,"请选择一条数据记录.","warning");
			return;
		}
    	$.messager.confirm(TIP_TITLE, "您确定要删除所选记录[<font color=red>" + rows.length + "</font>]吗?", function(r){
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
			        		/*
			        		$.messager.show({
		    					title:TIP_TITLE,
		    					msg:res.msg,
		    					timeout:5000,
		    					showType:'slide'
		    				});
		    				*/
		    				showUpdateMsg(TIP_TITLE,res.msg);
							$("#" + TABLE_LIST).datagrid('reload');
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
    	alert(form.attr("action"));
    	/**/
    	form.form('submit',{
    		url: form.attr("action"),
    		onSubmit: function(){
    			//return $(this).form('validate');
    			if($(this).form('enableValidation').form('validate')){
    				alert("y");
    			}
    			return $(this).form('enableValidation').form('validate');
    		},
    		success: function(result){
    			alert("1");
    		},error:function(){
    			alert("error");
    		}
    	});
    	
    }
    /**
     * 增删改提示信息
     * @param {} _title
     * @param {} _msg
     */
    function showUpdateMsg(_title,_msg){
		$.messager.show({
			title:_title,
			msg:_msg,
			showType:'slide',
			style:{
				left:0,
				right:'',
				top:'',
				bottom:-document.body.scrollTop-document.documentElement.scrollTop
			}
		});
	}
	    /**
     * 查询提示信息
     * @param {} _title
     * @param {} _msg
     */
    function showQueryMsg(_title,_msg){
		$.messager.show({
			title:_title,
			msg:_msg,
			showType:'slide'
		});
	}
	    /**
	     * 
	     **/
	    function getSelections(tableid){
	    	tableid = tableid ? tableid : "table-list";
			var res = [];
			var rows = $('#' + tableid).datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				res.push(rows[i]);
			}
			return rows;
		}
	   
		function openForm(_title,_iconCls){
			_title = _title ? _title : $('#' + FORM_DIV).attr("title");
			_iconCls = _iconCls ? _iconCls : $('#' + FORM_DIV).attr("iconCls");
			
			$('#' + FORM_DIV).dialog({iconCls:_iconCls,title:_title}).dialog('open');
		}
		function closeForm(){
			$('#' + FORM_DIV).dialog('close');
		}
		function formatter1(d){
		console.log("formatter1:"+d);
		var t = d ? d : new Date();
		return date2string(t);
	}
	function parser1(d){
		console.log("parser1:"+d);
		var t = d ? d : formatter1();
		return string2date(t);
	}
	
	/*
	function f1(_a){
	
		if (!_a) {
			return "";
		}
		return $.fn.datebox.defaults.formatter.call(this, _a) + " "
				+ $.fn.timespinner.defaults.formatter.call(this, _a);
	}
	function p1(s) {
		s = $.trim(s);
		if (!s) {
			return null;
		}
		var dt = s.split(" ");
		var _b = $.fn.datebox.defaults.parser.call(this, dt[0]);
		if (dt.length < 2) {
			return _b;
		}
		var _c = $.fn.timespinner.defaults.parser.call(this, dt[1]);
		return new Date(_b.getFullYear(), _b.getMonth(), _b.getDate(), _c
				.getHours(), _c.getMinutes(), _c.getSeconds());
	}
	*/
	function _search(queryid){
		/*
		$("#" + TABLE_LIST).datagrid({ queryParams: query });
		*/
		search1(TABLE_LIST,FORM_QUERY);
	}
	function search1(tableid,queryid){  
		var tableid = tableid ? tableid : TABLE_LIST;
		var queryid = queryid ? queryid : FORM_QUERY;
		//先取得 datagrid 的查询参数  
        var params = $("#" + tableid).datagrid('options').queryParams;
        params={};
        //自动序列化表单元素为JSON对象  
        var fields =$("#" + queryid).serializeArray();
        $.each( fields, function(i, field){
        	if(field.value){
        		var val = field.value;
        		if(field.name == "createTime1" || field.name == "createTime2"){
        			var t = val.split(" ");
        			val = t[0].split("/")[2] + "-" + t[0].split("/")[0] + "-" + t[0].split("/")[1] + " " + t[1];
        		}
        		params[field.name] = val;
        	}
        });
        //设置好查询参数 reload 一下就可以了  
        //$("#" + tableid).datagrid("reload");
        $("#" + tableid).datagrid({queryParams:params});
    } 
	function _clear(formid){
		var formid = formid ? formid : "form-query";
		alert("clear:" + formid);
		$("#" + formid).form('clear');
	}
	function onLoadSuccess(res){
		var msg = res.total ? "请求到[<font color=red>" + res.total + "</font>]条符合条件的数据" : "没有请求到符合条件的数据";
		
		showQueryMsg(TIP_TITLE,msg);
		// 初始化 tooltip
        $("#" + TABLE_LIST).datagrid('doCellTip', {
            onlyShowInterrupt: true,
            position: 'bottom',
            maxWidth: '450px',
            // specialShowFields: [{field:'status',showField:'statusDesc'}],
            tipStyler:{
                backgroundColor:'#FFFFCC',
                color: '#333',
                // borderColor:'#ff0000',
                boxShadow:'1px 1px 3px #eee',
                wordBreak: 'break-all'
            }
        });
	}
