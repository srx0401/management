package com.srx.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.srx.utils.string.StringUtil;

public class DateUtil {
	public static String DEFAULT_FORMATTER = "yyyy-MM-dd";
	public static String DATE_FORMATTER = "yyyy-MM-dd";
	public static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat();
	public static String parse(Date d) throws Exception{
		throw new Exception();
	}
	/**
	 * 	以指定格式解析指定字符串为日期对象
	 * @param date		指定字符串
	 * @param pattern	指定格式,为""或null时取默认值[yyyy-MM-dd]
	 * @return			返回日期对象,有任何异常返回null
	 */
	public static Date parse(final String date,final String pattern){
		if (StringUtil.isEmpty(date)) {
			return null;
		}
		
		sdf.applyPattern(StringUtil.isEmpty(pattern) ? DEFAULT_FORMATTER : pattern);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 以[yyyy-MM-dd]解析指定字符串为日期对象
	 * @param date		[yyyy-MM-dd]格式的字符串
	 * @return			返回日期对象,有任何异常返回null
	 */
	public static Date toDate(final String date){
		return parse(date, DATE_FORMATTER);
	}
	/**
	 * 以[yyyy-MM-dd HH:mm:ss]解析指定字符串为日期对象
	 * @param date		[yyyy-MM-dd HH:mm:ss]格式的字符串
	 * @return			返回日期对象,有任何异常返回null
	 */
	public static Date toDateTime(final String dateTime){
		return parse(dateTime, DATE_TIME_FORMATTER);
	}
	/**
	 * 以指定格式解析指定日期对象为字符串
	 * @param date		指定日期
	 * @param pattern	指定格式,,为""或null时取默认值[yyyy-MM-dd]
	 * @return			返回字符串对象,有任何异常返回null
	 */
	public static String format(final Date date,final String pattern){
		if (date == null) {
			return null;
		}
		
		sdf.applyPattern(StringUtil.isEmpty(pattern) ? DEFAULT_FORMATTER : pattern);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 以[yyyy-MM-dd]解析指定日期对象为字符串
	 * @param date		待处理转换的日期对象
	 * @return			返回字符串对象,有任何异常返回null
	 */
	public static String dateToString(final Date date){
		return format(date,DATE_FORMATTER);
	}
	/**
	 * 以[yyyy-MM-dd HH:mm:ss]解析指定日期对象为字符串
	 * @param dateTime	待处理转换的日期对象
	 * @return			返回字符串对象,有任何异常返回null
	 */
	public static String dateTimeToString(final Date dateTime){
		return format(dateTime,DATE_TIME_FORMATTER);
	}
	public static void main(String[] args) {
		Date d = DateUtil.parse("2015-01-02 15:12:34", DateUtil.DATE_TIME_FORMATTER);
		System.out.println(d);
		System.out.println(DateUtil.dateToString(new Date()));
		System.out.println(DateUtil.dateTimeToString(new Date()));
	}
}
