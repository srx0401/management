package com.srx.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.srx.model.common.Conditional;
import com.srx.model.common.Page;
import com.srx.service.base.BaseService;
import com.srx.utils.json.JacksonUtil;
import com.srx.utils.string.StringUtil;

public class BaseController<T> {
	public static final String RESULT_NAME = "res";
	public static final String SUCCEEDED_NAME = "success";
	public static final String SUCCEEDED_VALUE = "true";
	public static final String MSG_NAME = "msg";
	public static final String MSG_SAVE_SUCCEEDED = "保存成功";
	public static final String MSG_SAVE_FAILED = "保存失败";
	public static final String MSG_UPDATE_SUCCEEDED = "修改成功";
	public static final String MSG_UPDATE_FAILED = "修改失败";
	public static final String MSG_REMOVE_SUCCEEDED = "删除成功";
	public static final String MSG_REMOVE_FAILED = "删除失败";
	public static final String MSG_LIST_SUCCEEDED = "加载成功";
	public static final String MSG_LIST_FAILED = "加载失败";
	protected static final String INDEX = "/index";
	protected static final String HOME = "/home";
	protected static final String LIST = "/list";
	protected static final String ADD = "/add";
	protected static final String EDIT = "/edit";
	protected static final String DETAIL = "/detail";
	
	private static Log log = LogFactory.getLog(BaseController.class);
	
	@Autowired
	private BaseService<T, Serializable> baseService;
	
	private Class<T> entityClass;
	private String prefix;
	
	public BaseController() {
		this.entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            
            this.entityClass = (Class<T>) parameterizedType[0];
            prefix = entityClass.getSimpleName().substring(0, 1).toLowerCase() + entityClass.getSimpleName().substring(1);
//            log.info("entityClass=" + entityClass.toString());
        }  
	}
	/**
	 * 时间编辑器
	 * 
	 * @param bin
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder bin) {
		bin.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	protected String add(Model model){
		return prefix +  ADD;
	}
	protected String edit(String id,Model model){
		Assert.notNull(id, "id is required");
		T res=null;
		try {
			res = baseService.get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(RESULT_NAME, res);
		return prefix + EDIT;
	}
	protected String detail(String id,Model model){
		Assert.notNull(id, "id is required");
		T res=null;
		try {
			res = baseService.get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(RESULT_NAME, res);
		return prefix + DETAIL;
	}
	
	protected String list(Model model){
		List res = baseService.list(null);
		model.addAttribute(RESULT_NAME,res);
		return prefix + LIST;
	}
	protected String home(HttpServletRequest req,Model model){
		return prefix + HOME;
	}
	protected String index(HttpServletRequest req,Model model){
		return prefix + INDEX;
	}
	protected void listByAjax(List<Conditional> conditionals,HttpServletRequest req,HttpServletResponse resp){
		
		String _page = req.getParameter("page");
		String _rows = req.getParameter("rows");
		if (!StringUtil.isEmpty(_page) && !StringUtil.isEmpty(_rows)) {
			listByAjax(conditionals,req, resp, Integer.parseInt(_page), Integer.parseInt(_rows));
		}else{
			List list = baseService.list(conditionals);
			String res="";
			try {
				res = JacksonUtil.list2Json(list);
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}
			log.info(res);
			writeJson(res, resp);
		}
	}
	protected void listByAjax(List<Conditional> conditionals,HttpServletRequest req,HttpServletResponse resp,int page,int rows){
		Page<T> p = baseService.list(page,rows,conditionals);
		String res="";
		try {
//			res = JacksonUtil.list2Json(p.getItems());
			res = JacksonUtil.bean2Json(p);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		log.info(res);
		writeJson(res, resp);
		//writeJson("{\"total\":\""+p.getTotal()+"\"}", resp);
	}
	protected void editByAjax(String id,HttpServletResponse resp){
//		JsonObject
		T bean = baseService.get(id);
		String res="";
		try {
			res = JacksonUtil.bean2Json(bean);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		log.info(res);
		writeJson(res, resp);
	}
	protected void detailByAjax(String id,HttpServletResponse resp){
		T bean = baseService.get(id);
		String res="";
		try {
			res = JacksonUtil.bean2Json(bean);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		log.info(res);
		writeJson(res, resp);
	}
	protected void saveByAjax(T t,HttpServletRequest req,HttpServletResponse resp){
		Object id=null;
		String msg = MSG_SAVE_SUCCEEDED;
		try {
			id = baseService.save(t);
		} catch (Exception e) {
			msg = MSG_SAVE_FAILED;
			log.error(e.getMessage(),e);
		}
		writeJsonMessage(id != null, msg, resp);
	}
	protected void removeByAjax(String id,HttpServletResponse resp){
		boolean succeeded = true;
		String msg = MSG_REMOVE_SUCCEEDED;
		try {
			succeeded = baseService.remove(id);
		} catch (Exception e) {
			succeeded = false;
			msg = MSG_REMOVE_FAILED;
			log.error(e.getMessage(),e);
		}
		writeJsonMessage(succeeded, msg, resp);
	}
	protected void removeByAjax(List ids,HttpServletResponse resp){
		boolean succeeded = true;
		String msg = MSG_REMOVE_SUCCEEDED;
		try {
//			flag = baseService.remove(id);
			succeeded = baseService.remove(ids);
			msg += "[" + ids.size() + "]";
		} catch (Exception e) {
			succeeded = false;
			msg = MSG_REMOVE_FAILED;
			log.error(e.getMessage(),e);
		}
		writeJsonMessage(succeeded, msg, resp);
	}
	protected void writeJson(String content,HttpServletResponse resp) {
		log.info(" ===> begin");
		log.info("content=" + content);
		resp.setContentType("application/json");
		try {
			PrintWriter out = resp.getWriter();
			out.write(content);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		log.info(" ===> end");
	}
	protected void writeJsonMessage(boolean succeeded,final String msg,final HttpServletResponse resp) {
		String res="{\""+MSG_NAME+"\":\""+msg+"\""+(succeeded ? ",\""+SUCCEEDED_NAME+"\":\""+SUCCEEDED_VALUE+"\"" : "")+"}";
		log.info(res);
		resp.setContentType("application/json");
		try {
			PrintWriter out = resp.getWriter();
			out.write(res);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}
	protected void writeJsonMessage(Map<String,Object> map,final HttpServletResponse resp) {
//		String res="{\""+MSG_NAME+"\":\""+msg+"\""+(succeeded ? ",\""+SUCCEEDED_NAME+"\":\""+SUCCEEDED_VALUE+"\"" : "")+"}";
		
		String res="";
		try {
			if(map != null && !map.isEmpty()){
				res = JacksonUtil.bean2Json(map);
			}
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		log.info(res);
		resp.setContentType("application/json");
		try {
			PrintWriter out = resp.getWriter();
			out.write(res);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}
}
