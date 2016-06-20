package com.srx.interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.srx.user.model.User;
import com.srx.utils.session.SessionUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static String loginUrl;
	private static List<String> ignores;
	private static List<String> resources;
	
	private static Log log = LogFactory.getLog(LoginInterceptor.class);
    /* 
     * 利用正则映射到需要拦截的路径     
      
    private String mappingURL; 
     
    public void setMappingURL(String mappingURL) {     
               this.mappingURL = mappingURL;     
    }    
  */  
    /**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */    
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
        if ("GET".equalsIgnoreCase(request.getMethod())) {  
//            RequestUtil.saveRequest();  
        }  
        log.info("==============执行顺序: 1、preHandle================");    
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        
//        log.info("requestUri:"+requestUri);    
//        log.info("contextPath:"+contextPath);    
//        log.info("url:"+url);    
        
        if(url.indexOf("home.jsp") > -1){
        	System.out.println(url);
        }
       
        if (ifIgnores(url)) {
        	System.out.println("忽略请求:"+url);
            return true;
        }
        if (ifStaticResource(url)) {
        	System.out.println("静态资源:"+url);
            return true;
        }
        User u = SessionUtil.getCurrentLoginUser(request);
        
        if(u == null){  
            log.info("Interceptor：未检测到登陆用户,跳转至登陆页面.");  
            request.getRequestDispatcher(loginUrl).forward(request, response);  
            return false;  
        }  
        return true;     
    }    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
        log.info("==============执行顺序: 2、postHandle================");    
        if(modelAndView != null){  //加入当前时间    
//            modelAndView.addObject("var", "测试postHandle");    
        }    
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
        log.info("==============执行顺序: 3、afterCompletion================");    
    }

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String _loginUrl) {
		loginUrl = _loginUrl;
	}

	public List<String> getIgnores() {
		return ignores;
	}

	public void setIgnores(List<String> _ignores) {
		ignores = _ignores;
	}

	public static List<String> getResources() {
		return resources;
	}

	public static void setResources(List<String> resources) {
		LoginInterceptor.resources = resources;
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
		   if (url.endsWith(ignore) || Pattern.compile(ignore.replaceAll("\\*", ".*")).matcher(url).matches()) {
			   return true;
		   }
	    }
		return false;
	}
}  
