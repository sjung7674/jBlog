package com.hjb.jBlog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.service.impl.MemberServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	@Autowired
	MemberServiceImpl memberService;
	
	@RequestMapping("/member")
	public ModelAndView admin_member(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/member");
		view.addObject("memberList", memberService.memberList());
		return view;
	}
}
