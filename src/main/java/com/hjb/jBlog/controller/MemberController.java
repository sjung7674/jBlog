package com.hjb.jBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@RequestMapping(value = "/login")
	public String member_login() {
		return "member/login";
	}
	
	@RequestMapping(value = "/login_proc")
	public String member_login_proc(HttpServletRequest request, HttpServletResponse response) {
		String redirectURI = request.getParameter("redirect");
		
		return "redirect:"+redirectURI;
	}
}
