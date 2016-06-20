/**
	 *	将指定日期解析为字符串,仅支持格式:yyyy-MM-dd HH:mm:ss
	 *	date	指定日期
	 *	df		异常时的默认值
	 */
function date2string(date,df) {
	var res = "";
	try{
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var hour = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		res = y + '-' + (m < 10 ? ('0' + m) : m) + "-"
				+ (d < 10 ? ('0' + d) : d) + " "
				+ (hour < 10 ? ('0' + hour) : hour) + ":"
				+ (min < 10 ? ('0' + min) : min) + ":"
				+ (sec < 10 ? ('0' + sec) : sec);
	}catch(e){
		res = df ? df : "";
	}
	console.log("date2string['" + date + "' to '" + res + "']");
	return res;
}
/**
 *	将指定字符串解析为日期.仅支持格式:yyyy-MM-dd HH:mm:ss
 *	date	指定字符串.
 *	df	异常时的默认值
 */
function string2date(date,df) {
	var res = new Date();
	try {
		var t = date.split(" ");
		var t1 = t[0].split("-");
		var t2 = t[1].split(":");
		var y = parseInt(t1[0], 10);
		var M = parseInt(t1[1], 10);
		var d = parseInt(t1[2], 10);

		var H = parseInt(t2[0], 10);
		var m = parseInt(t2[1], 10);
		var s = parseInt(t2[2], 10);
		if (!isNaN(y) && !isNaN(M) && !isNaN(d) && !isNaN(H) && !isNaN(m)
				&& !isNaN(s)) {
			res = new Date(y, M - 1, d, H, m, s);
		}
	} catch (e) {
		res = df ? df : "";
	}
	/*
	var date = new Date(y, M - 1, d);
	date.setHours(H, m, s);
	console.log(date);
	
	return date;
	 */
	console.log("string2date['" + date + "' to '" + res + "']");
	return res;
}