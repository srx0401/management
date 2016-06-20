package com.srx.account.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.srx.account.model.Account;
import com.srx.account.service.AccountService;
import com.srx.controller.base.BaseController;
import com.srx.model.common.Conditional;
import com.srx.model.common.LogicOperator;
import com.srx.user.model.User;
import com.srx.utils.date.DateUtil;
import com.srx.utils.session.SessionUtil;
import com.srx.utils.string.StringUtil;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController<Account>{
	private static final String PREFIX = "account";
	private static Log log = LogFactory.getLog(AccountController.class);
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/home")
	public String home(HttpServletRequest req,Model model){
		
		return super.home(req, model);
	}
	@RequestMapping("/index")
	public String index(HttpServletRequest req,Model model){
//		req.setAttribute("res", accountService.list());
//		return PREFIX + "/home";
		return super.index(req, model);
	}
	@RequestMapping("/add")
	public String add(Model model){
		return PREFIX +  "/add";
	}
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id,Model model){
//		Assert.notNull(id, "id is required");
//		Account account = accountService.get(id);
//		model.addAttribute("res", account);
//		return PREFIX + "/edit";
		log.debug("edit,id="+id);
		return super.edit(id, model);
	}
	@RequestMapping("/editByAjax/{id}")
	public void editByAjax(@PathVariable String id,HttpServletResponse resp){
//		Assert.notNull(id, "id is required");
//		Account account = accountService.get(id);
//		model.addAttribute("res", account);
//		return PREFIX + "/edit";
		log.debug("edit,id="+id);
		super.editByAjax(id, resp);
	}
	@RequestMapping("/list")
	public String list(Model model){
		return super.list(model);
//		List<Account> res = accountService.list();
//		model.addAttribute("res",res);
//		return PREFIX +  "/list";
	}
	@RequestMapping("/saveByAjax")
	public void saveByAjax(Account account,HttpServletRequest req,HttpServletResponse resp){
		account.setModifyTime(new Date());
		if (StringUtils.isEmpty(account.getId())) {
			account.setCreateTime(new Date());
		}
		super.saveByAjax(account, req, resp);
	}
	
	@RequestMapping("/updateByAjax")
	public void updateByAjax(Account _account,HttpServletRequest req,HttpServletResponse resp){
		String msg = MSG_UPDATE_SUCCEEDED;
		boolean succeeded = true;
		try {
			//	查询指定记录
			Account account = accountService.get(_account.getId());
			if (account == null) {
				succeeded = false;
				msg = MSG_LIST_FAILED;
				return;
			}
			account.setModifyTime(new Date());
			account.setName(_account.getName());
			account.setUsername(_account.getUsername());
			account.setPassword(_account.getPassword());
			account.setPrompt(_account.getPrompt());
			account.setRemark(_account.getRemark());
			
			accountService.update(account);
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
