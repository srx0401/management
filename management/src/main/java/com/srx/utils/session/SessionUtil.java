package com.srx.utils.session;

import javax.servlet.http.HttpServletRequest;

import com.srx.user.model.User;

public class SessionUtil {
	private static String CURRENT_USER = "currentLoginUser";
	/**
	 * 获取当前登陆用户
	 * @param req	HttpServlet请求
	 * @return		存在登陆用户则返回用户对象,不存在则返回null
	 */
	public static User getCurrentLoginUser(HttpServletRequest req){
		Object res = req.getSession(true).getAttribute(CURRENT_USER);
		return res == null ? null : (User)res;
	}
	/**
	 * 设置当前登陆用户到会话中
	 * @param req	HttpServlet请求
	 * @param user	登陆用户
	 */
	public static void setCurrentLoginUser(HttpServletRequest req,User user){
		 req.getSession(true).setAttribute(CURRENT_USER,user);
	}
	/**
	 * 删除会话中的信息
	 * @param req	HttpServlet请求
	 * @param key	会话信息KEY
	 * @param value	会话信息VALUE
	 */
	public static void add(HttpServletRequest req,final String key,final Object value){
		 req.getSession(true).setAttribute(key, value);
	}
	/**
	 * 删除会话中的信息
	 * @param req	HttpServlet请求
	 * @param key	会话信息KEY
	 */
	public static void remove(HttpServletRequest req,final String key){
		 req.getSession(true).removeAttribute(key);
	}
	/**
	 * 退出当前用户
	 * @param req	HttpServlet请求
	 */
	public static void removeLoginUser(HttpServletRequest req){
		 req.getSession(true).removeAttribute(CURRENT_USER);
	}
}
