package com.hjb.jBlog.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.admin.service.impl.AdminHeaderServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminHeaderController {
	@Autowired
	AdminHeaderServiceImpl adminHeaderService;
	
	@RequestMapping("/mainHeader")
	public ModelAndView mainHeaderModify(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.addObject("main_header", adminHeaderService.selectMainHeader());
		view.setViewName("/admin/main_header");
		return view;
		
	}
	@RequestMapping("/saveMainHeader")
	@ResponseBody
	public String saveMainHeader(MultipartHttpServletRequest request){
		String title=request.getParameter("title")==null?"":request.getParameter("title");
		String sub_title=request.getParameter("sub_title")==null?"":request.getParameter("sub_title");
		MultipartFile mpf = request.getFile("header_image_file");
		String result = adminHeaderService.updateHeader(title, sub_title, mpf);
		
		return result;
	}
}
