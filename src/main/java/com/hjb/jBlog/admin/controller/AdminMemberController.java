package com.hjb.jBlog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	
	@RequestMapping("/member")
	public ModelAndView admin_member(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/member");
		return view;
	}
}
