package com.srx.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srx.controller.base.BaseController;
import com.srx.model.common.Conditional;
import com.srx.model.common.LogicOperator;
import com.srx.user.model.User;
import com.srx.user.service.UserService;
import com.srx.utils.date.DateUtil;
import com.srx.utils.security.MD5Util;
import com.srx.utils.session.SessionUtil;
import com.srx.utils.string.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User>{
	private static final String LOGIN_URL = "/login.jsp";
	private static final String MSG_LOGIN_SUCCEEDED = "登陆成功";
	private static final String MSG_LOGIN_FAILED = "用户名或密码错误";
	private static final String PREFIX = "user";
	private static Log log = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
	public String home(HttpServletRequest req,Model model){
//		req.setAttribute("res", accountService.list());
//		return PREFIX + "/home";
		return super.home(req, model);
	}
	/**
	 * 登陆
	 * @param user
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/login")
	public void login(User user,HttpServletRequest req,HttpServletResponse resp){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (user == null || StringUtil.isEmpty(user.getUsername()) || StringUtil.isEmpty(user.getPassword())) {
				return;
			}
			User loginUser = userService.login(user.getUsername(), user.getPassword());
			
			if (loginUser != null) {
				System.out.println(loginUser.getPassword());
				map.put(SUCCEEDED_NAME, true);
				map.put("url", "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + HOME);
				SessionUtil.setCurrentLoginUser(req, loginUser);
			}else{
				map.put(MSG_NAME, MSG_LOGIN_FAILED);
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			map.put(MSG_NAME, e.getMessage());
		}finally{
			writeJsonMessage(map, resp);
		}
	}
	/**
	 * 退出
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/exit")
	public void exit(HttpServletRequest req,HttpServletResponse resp){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + LOGIN_URL);
		SessionUtil.removeLoginUser(req);
		writeJsonMessage(map, resp);
	}
	/**
	 * 改密
	 * @param oldpassword
	 * @param newpassword
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/changePassword")
	public void changePassword(String oldpassword,String newpassword,HttpServletRequest req,HttpServletResponse resp){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(SUCCEEDED_NAME, false);
		
//		oldpassword = MD5Util.MD5(oldpassword);
//		newpassword = MD5Util.MD5(newpassword);
		User loginUser = SessionUtil.getCurrentLoginUser(req);
		/*
		loginUser.setUsername("18516181496");
		loginUser.setGender("1");
		loginUser.setAge(35);
		loginUser.setMail("gyy1215@163.com");
		loginUser.setName("王启方");
		loginUser.setQq("775766979");
		loginUser.setWechat("775766979");
		loginUser.setNativePlace("宜宾");
		loginUser.setAddress("上海市徐汇区华发路180弄24号601");
		loginUser.setPhone("18516181496");
		loginUser.setRoot(true);
		*/
		System.out.println(loginUser.getPassword());
		System.out.println(oldpassword);
		System.out.println(newpassword);
		try{
			if(loginUser.getPassword().equals(oldpassword)){
				User user = userService.get(loginUser.getId());
//				loginUser.setPassword(newpassword);
				user.setPassword(newpassword);
				System.out.println("loginUser.password:"+loginUser.getPassword());
				User u1 = userService.update(user);
				System.out.println("user.password:"+loginUser.getPassword());
				System.out.println("u1.password:"+u1.getPassword());
				map.put(SUCCEEDED_NAME, true);
				map.put(MSG_NAME, MSG_UPDATE_SUCCEEDED + ".");
				SessionUtil.setCurrentLoginUser(req, u1);
			}else{
				map.put(MSG_NAME, MSG_UPDATE_FAILED + "[原始密码不正确].");
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			map.put(MSG_NAME, e.getMessage());
		}finally{
			writeJsonMessage(map, resp);
		}
	}
	@RequestMapping("/saveByAjax1")
	public void saveByAjax1(User user,HttpServletRequest req,HttpServletResponse resp){
		user.setModifyTime(new Date());
		if (StringUtils.isEmpty(user.getId())) {
			user.setCreateTime(new Date());
		}
		super.saveByAjax(user, req, resp);
	}
	@RequestMapping("/saveByAjax")
	public void saveByAjax(HttpServletRequest req,HttpServletResponse resp){
		User user = new User();
		user.setModifyTime(new Date());
		if (StringUtils.isEmpty(user.getId())) {
			user.setCreateTime(new Date());
		}
//		super.saveByAjax(user, req, resp);
	}
	@RequestMapping("/updateByAjax")
	public void updateByAjax(User _user,HttpServletRequest req,HttpServletResponse resp){
		String msg = MSG_UPDATE_SUCCEEDED;
		boolean succeeded = true;
		try {
			//	查询指定记录
			User user = userService.get(_user.getId());
			if (user == null) {
				succeeded = false;
				msg = MSG_LIST_FAILED;
				return;
			}
			user.setModifyTime(new Date());
			
			
			
			
			
			userService.update(user);
		} catch (Exception e) {
			succeeded = false;
			msg = MSG_UPDATE_FAILED;
			log.error(e.getMessage(),e);
		}
		
		writeJsonMessage(succeeded, msg, resp);
		
	}
	
	@RequestMapping("/removeByAjax/{id}")
	public void removeByAjax(@PathVariable String id,HttpServletResponse resp){
		super.removeByAjax(id, resp);
	}
	
	@RequestMapping("/removeByAjax")
	public void removeByAjax(@RequestBody List<String> ids,HttpServletRequest req,HttpServletResponse resp){
		super.removeByAjax(ids, resp);
	}
	@RequestMapping("/detailByAjax/{id}")
	public void detailByAjax(@PathVariable String id,HttpServletResponse resp){
		super.detailByAjax(id, resp);
	}
	@RequestMapping("/listByAjax")
	public void listByAjax(HttpServletRequest req,HttpServletResponse resp){
		List<Conditional> cons = new ArrayList<Conditional>();
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String address = req.getParameter("address");
		String keyword = req.getParameter("keyword");
		String createTime1 = req.getParameter("createTime1");
		String createTime2 = req.getParameter("createTime2");
		String modifyTime1 = req.getParameter("modifyTime1");
		String modifyTime2 = req.getParameter("modifyTime2");
		User u = SessionUtil.getCurrentLoginUser(req);
		if(u == null){
			return;
		}
		if (!u.isRoot()) {
			cons.add(new Conditional("userid",u.getId()));
		}
		if (!StringUtil.isEmpty(name)) {
			cons.add(new Conditional("name","%" + name + "%",LogicOperator.LIKE));
		}
		if (!StringUtil.isEmpty(username)) {
			cons.add(new Conditional("username","%" + username + "%",LogicOperator.LIKE));
		}
		if (!StringUtil.isEmpty(address)) {
			cons.add(new Conditional("address","%" + address + "%",LogicOperator.LIKE));
		}
//		if (!StringUtil.isEmpty(keyword)) {
//			cons.add(new Conditional("username",keyword,LogicOperator.LIKE));
//		}
		Date c1 = DateUtil.toDateTime(createTime1);
		if (c1 != null) {
			cons.add(new Conditional("createTime",c1,LogicOperator.GREATER_OR_EQUAL));
		}
		Date c2 = DateUtil.toDateTime(createTime2);
		if (c2 != null) {
			cons.add(new Conditional("createTime",c2,LogicOperator.LESS));
		}
		Date m1 = DateUtil.toDateTime(modifyTime1);
		if (m1 != null) {
			cons.add(new Conditional("modifyTime",m1,LogicOperator.GREATER_OR_EQUAL));
		}
		Date m2 = DateUtil.toDateTime(modifyTime2);
		if (m2 != null) {
			cons.add(new Conditional("modifyTime",m2,LogicOperator.LESS));
		}
		super.listByAjax(cons,req, resp);
	}
	
}
