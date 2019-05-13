package com.hjb.jBlog.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.dto.MemberDTO;

public interface MemberService {
	public String oauth_service(ModelAndView view,String code,String state);
	public String getNaverID(String token);
	public void join_proc(ModelAndView view,MemberDTO memberDTO,BindingResult bindingResult,String state);
	public MemberDTO login_proc(String naverID);
}
