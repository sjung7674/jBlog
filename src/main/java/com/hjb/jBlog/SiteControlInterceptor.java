package com.hjb.jBlog;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hjb.jBlog.service.impl.WriteServiceImpl;

public class SiteControlInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	WriteServiceImpl writeService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("in--");
		request.setAttribute("categoryList", writeService.getCategory());
		Enumeration e = request.getSession().getAttributeNames();
		while(e.hasMoreElements()){
			System.out.println(e.nextElement());
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("out--");
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
	

}
