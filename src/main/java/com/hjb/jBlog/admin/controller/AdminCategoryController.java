package com.hjb.jBlog.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.service.impl.WriteServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {
	@Autowired
	WriteServiceImpl writeService;
	
	@RequestMapping("/category")
	public ModelAndView adminCategory(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/category");
		view.addObject("category_list", writeService.getCategory());
		return view;
	}
	
	
}
