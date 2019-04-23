package com.hjb.jBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, HttpServletResponse response){
		return "member/write";
	}
	
	
	
}
