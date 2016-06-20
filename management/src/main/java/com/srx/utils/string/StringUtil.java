package com.srx.utils.string;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	private static Log log = LogFactory.getLog(StringUtil.class);
	/**
	 * 判断指定字符串是否为空(null,""," ")
	 * @param src	待判断字符串
	 * @return
	 */
	public static boolean isEmpty(String src){
		return src == null || src.trim().length() < 1;
	}
	/**
	 * 判断指定字符串是否非空(null)
	 * @param src	待判断字符串
	 * @return
	 */
	public static boolean isNotNull(String src){
		return src != null;
	}
	/**
	 * 使用指定字符或字符串替换指定区间段的原字符串,可用于部分显示场景,如:137****1234
	 * @param src	原字符串,非空
	 * @param ch	填充字符或字符串,为空时取默认值为[*]
	 * @param begin	开始填充位置(>=),数值超出范围时取默认值为[0]
	 * @param end	结束填充位置(<),数值超出范围时取默认值为原字符串[src]的长度
	 * @return
	 */
	public static String replace(String src,String ch,int begin,int end){
		if (isEmpty(src)) {
			log.error("原字符串[src]不能为空.");
			return null;
		}
		if (isEmpty(ch)) {
			log.info("填充字符串[ch]为空,取默认值为[*].");
			ch = "*";
		}
		if (begin < 0 || begin >= src.length()) {
			log.info("开始填充位置[begin]数值超出范围,取默认值为0.");
			begin = 0;
		}
		if (end > src.length() || end <= begin || end < 1) {
			log.info("结束填充位置[end]数值超出范围,取默认值为原字符串[src]长度:" + src.length());
			end = src.length();
		}
		StringBuffer res = new StringBuffer(src.substring(0,begin));
		while(res.length() < end){
			res.append(ch);
		}
		if (res.length() > end) {
			res = res.delete(end, res.length());
		}
		res.append(src.substring(end));
		return res.toString();
	}
	public static void main(String[] args) {
		String a = "123456789";
		int i = a.length()/4;
		int j = a.length() - a.length()/4;
		String b = replace(a, null, i, j);
		System.out.println(b);
	}
}
