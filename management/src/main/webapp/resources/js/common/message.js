var INFO = "info";
var ERROR = "error";
var QUESTION = "question";
var WARNING = "warning";
/**
 * 	基于Jquery的message简单封装
 */
/**
 * Alert弹出提示窗口
 * @param {} msg		内容		
 * @param {} title		标题,为空取默认值[温馨提示]
 * @param {} type		类型[error,info,question,warning]
 */
function alert(msg,title,type) {
	
	title = title ? title : DEFAULT_TIP_TITLE;
	type = type ? type : INFO;
	
	$.messager.alert(title, msg, type);
}
 
/**
 * 警告弹框
 * @param {} msg		内容
 * @param {} title		标题,为空取默认值[温馨提示]
 */
function warningAlert(msg,title){
	alert(msg,title,WARNING);
}

/**
 * 错误弹框
 * @param {} msg		内容
 * @param {} title		标题,为空取默认值[温馨提示]
 */
function errorAlert(msg,title){
	alert(msg,title,ERROR);
}
