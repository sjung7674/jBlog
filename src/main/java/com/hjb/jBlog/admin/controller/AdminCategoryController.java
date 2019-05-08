package com.hjb.jBlog.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.admin.service.AdminCategoryService;
import com.hjb.jBlog.dto.CategoryDTO;
import com.hjb.jBlog.service.CategoryService;
import com.hjb.jBlog.service.impl.WriteServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {
	@Autowired
	private WriteServiceImpl writeService;
	@Autowired
	private AdminCategoryService adminCategoryService; 
	
	@RequestMapping("/category")
	public ModelAndView adminCategory(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/category");
		view.addObject("category_list", writeService.getCategory());
		return view;
	}
	@RequestMapping(value="/category/save",method=RequestMethod.POST)
	@ResponseBody
	public String category_save(CategoryDTO categoryDTO){
		return adminCategoryService.saveCategory(categoryDTO);
	}
	
}
