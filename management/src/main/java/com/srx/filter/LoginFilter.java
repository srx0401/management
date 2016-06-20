package com.srx.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srx.user.model.User;
import com.srx.utils.session.SessionUtil;

public class LoginFilter implements Filter {
	private static final String STATIC_RESOURCES_URL = "/resources/*";
	private FilterConfig config;
	private String loginUrl = "/login.jsp";
	/**
	 * 排外请求URI
	 */
	private static List<String> ignores = new ArrayList<String>();
	/**
	 * 静态资源
	 */
	private static List<String> resources = new ArrayList<String>();
	static{
		resources.add(STATIC_RESOURCES_URL);
		
		ignores.add(".js");
		ignores.add(".gif");
		ignores.add(".png");
		ignores.add(".jpg");
		ignores.add("/code");
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String _ignores = filterConfig.getInitParameter("ignores");
		if (_ignores != null && !"".equals(_ignores)) {
			for(String s : _ignores.split(",")){
				ignores.add(s);
			}
		}
		String _resources = filterConfig.getInitParameter("resources");
		if (_resources != null && !"".equals(_resources)) {
			for(String s : _resources.split(",")){
				resources.add(s);
			}
		}
		
		String _loginUrl = filterConfig.getInitParameter("loginUrl");
		if (_loginUrl != null && !"".equals(_loginUrl)) {
			this.loginUrl = _loginUrl;
		}
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url =  req.getRequestURI().substring(req.getContextPath().length());  
		System.out.println(url);
		User u = SessionUtil.getCurrentLoginUser(req);
		if(ifIgnores(url) || ifStaticResource(url) || ifAjaxRequest(req) || u != null){
			chain.doFilter(request, response);
			return;
		}
		resp.sendRedirect(req.getContextPath() + loginUrl);
		return ;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	private boolean ifAjaxRequest(final HttpServletRequest req){
		return "XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"));
	}
	private boolean ifStaticResource(final String url){
	    for(String regex : resources){
		    // 邮箱验证规则
	//	    String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		    // 忽略大小写的写法
		    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		    // 字符串是否与正则表达式相匹配
		    if(Pattern.compile(regex.replaceAll("\\*", ".*")).matcher(url).matches()){
		    	return true;
		    }
	    }
		return false;
	}
	private boolean ifIgnores(final String url){
	    for(String ignore : ignores){
		   if (url.endsWith(ignore)) {
			   return true;
		   }
	    }
		return false;
	}
	public static void main(String[] args) {
		LoginFilter lf = new LoginFilter();
		boolean flag = lf.ifStaticResource("/resources/css/default.css");
		System.out.println(flag);
		boolean f = lf.ifStaticResource("/resources/js/abdsbsdge.js");
		System.out.println(f);
	}
}
