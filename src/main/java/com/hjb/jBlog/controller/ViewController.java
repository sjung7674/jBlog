package com.hjb.jBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.dao.impl.ReadDAOImpl;

@Controller
public class ViewController {
	@Autowired
	ReadDAOImpl readDAO;
	
	@RequestMapping("/view/{idx}")
	public ModelAndView view(@PathVariable String idx,HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.addObject("post_dto", readDAO.readPost(idx));
		view.setViewName("view");
		return view;
	}
}
