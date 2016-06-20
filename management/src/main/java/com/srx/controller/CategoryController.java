package com.srx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srx.controller.base.BaseController;
import com.srx.model.Category;
import com.srx.service.CategoryService;
import com.srx.utils.date.DateUtil;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController<Category>{
	private static final String PREFIX = "category";
	private static Log log = LogFactory.getLog(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/home")
	public String home(HttpServletRequest req){
		req.setAttribute("res", categoryService.list(null));
		return PREFIX + "/home";
	}
	@RequestMapping("/add")
	public String add(Model model){
		return PREFIX +  "/add";
	}
	@RequestMapping("/edit/{id}")
	public String edit(@RequestParam String id,Model model){
		Assert.notNull(id, "id is required");
		Category category = categoryService.get(id);
		model.addAttribute("res", category);
		return PREFIX + "/edit";
	}
	@RequestMapping("/detail/{id}")
	public String detail(@RequestParam String id,Model model){
		Assert.notNull(id, "id is required");
		Category category = categoryService.get(id);
		model.addAttribute("res", category);
		return PREFIX + "/edit";
	}
	@RequestMapping("/list")
	public String list(Category category,Model model){
		List<Category> res = categoryService.list(null);
		model.addAttribute("res",res);
		return PREFIX +  "/list";
	}
	@RequestMapping("/save")
	public void save(Category category,Model model){
		Assert.notNull(category, "category is required");
		category.setModifyTime(new Date());
		if (StringUtils.isEmpty(category.getId())) {
			category.setCreateTime(new Date());
			String id=null;
			try {
				id = categoryService.save(category).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("id:" + id);
			category.setId(id);
		}else{
			
			try {
				categoryService.update(category);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		model.addAttribute("res", category);
//		return PREFIX + "/detail";
//		return "redirect:/"+PREFIX+"/list";
	}
	@RequestMapping("/remove/{id}")
	public void remove(String id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		
		try {
			if(categoryService.remove(id)){
				result = "{\"result\":\"success\"}";
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
