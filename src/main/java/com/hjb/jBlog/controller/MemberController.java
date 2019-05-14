package com.hjb.jBlog.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/login")
	public ModelAndView member_login(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/login");
		String clientId = "8ZMJxXbaGgxW_N0Yu9Gf";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://192.168.10.135:8085/member/oauth/oauth_proc", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    session.setAttribute("state", state);
	    view.addObject("url", apiURL);
		return view;
	}
	
	@RequestMapping(value = "/login_proc")
	public String member_login_proc(HttpServletRequest request, HttpServletResponse response) {
		String redirectURI = request.getParameter("redirect");
		String state=request.getParameter("state");
		String redirct="redirect:/error";
		if(state==null || !state.equals(request.getSession().getAttribute("state"))){
		}else{
			String access_token = (String) request.getSession().getAttribute("access_token");
			if(access_token!=null){
				String naverID = memberService.getNaverID(access_token);
				if(!naverID.equals("error")){
					MemberDTO member = memberService.login_proc(naverID);
					System.out.println(member);
					if(member!=null){
						UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(newAuth);
						redirct="redirect:/";
					}else{
						redirct="redirect:/member/join?state="+state;
					}
				}
			}
		}
		return redirct;
	}
	@RequestMapping(value = "/join")
	public ModelAndView join(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		String state=request.getParameter("state");
		if(state==null || !state.equals(request.getSession().getAttribute("state"))){
			view.setViewName("redirect:/error");
		}else{
			view.setViewName("member/join");
			String token = (String) request.getSession().getAttribute("access_token");// 네이버 로그인 접근 토큰;
			String naverId = memberService.getNaverID(token);
			if(naverId.equals("error")){
				view.setViewName("redirect:/error");
			}else{
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setUserid(naverId);
				view.addObject("memberDTO", memberDTO);
			}
		}
		return view;
	}
	@RequestMapping(value = "/oauth/oauth_proc")
	public ModelAndView oauth_proc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/oauth_proc");
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		request.getSession().setAttribute("access_token",memberService.oauth_service(view, code, state) );
	    return view;
	}
	@RequestMapping(value="/join_proc",method=RequestMethod.POST)
	public ModelAndView join_proc(MemberDTO dto,HttpServletRequest request,BindingResult result){
		ModelAndView view = new ModelAndView();
		String state=request.getParameter("state");
		if(state==null || !state.equals(request.getSession().getAttribute("state"))){
			view.setViewName("redirect:/error");
		}else{
	        memberService.join_proc(view, dto, result,state);
		}
		return view;
	}
}
