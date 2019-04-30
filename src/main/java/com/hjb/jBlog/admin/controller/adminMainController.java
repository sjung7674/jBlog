package com.hjb.jBlog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class adminMainController {
	
	@RequestMapping(value = "/login")
	public String admin_login() {
		return "admin/login";
	}
	@RequestMapping(value = "/")
	public String admin_root() {
		return "redirect:/admin/index";
	}
	@RequestMapping(value = "/index")
	public String admin_index() {
		return "/admin/index";
	}
}
