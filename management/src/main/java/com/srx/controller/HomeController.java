package com.srx.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srx.controller.base.BaseController;
import com.srx.user.model.User;
import com.srx.user.service.UserService;

@Controller
public class HomeController extends BaseController<User>{
	private static final String PREFIX = "";
	private static Log log = LogFactory.getLog(HomeController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
	public String home(HttpServletRequest req,Model model){
//		req.setAttribute("res", accountService.list());
//		return PREFIX + "/home";
		return "/home";
//		return super.home(req, model);
	}
	@RequestMapping("/login")
	public String login(String username,String password,String code,Model model){
		
		return "redirect:/home";
	}
	@RequestMapping("/saveByAjax")
	public void saveByAjax(User user,HttpServletRequest req,HttpServletResponse resp){
		user.setModifyTime(new Date());
		if (StringUtils.isEmpty(user.getId())) {
			user.setCreateTime(new Date());
		}
		super.saveByAjax(user, req, resp);
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
	
	@RequestMapping("/detailByAjax/{id}")
	public void detailByAjax(@PathVariable String id,HttpServletResponse resp){
		super.detailByAjax(id, resp);
	}
	
}
