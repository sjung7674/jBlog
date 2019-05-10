package com.hjb.jBlog.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.admin.service.AdminPostService;
import com.hjb.jBlog.dto.PostDTO;
import com.hjb.jBlog.service.impl.WriteServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminPostController {
	@Autowired
	private AdminPostService adminPostService;
	@Autowired
	private WriteServiceImpl writeService;
	
	@RequestMapping("/postList")
	public ModelAndView admin_postList(){
		ModelAndView view = new ModelAndView();
		view.addObject("postList", adminPostService.postList());
		view.setViewName("admin/postList");
		return view;
	}
	@RequestMapping("/post/view/{idx}")
	public ModelAndView admin_postView(@PathVariable String idx ){
		ModelAndView view = new ModelAndView();
		view.addObject("post_dto",adminPostService.selectViewByIdx(idx));
		view.addObject("categoryList",writeService.getCategory());
		view.setViewName("admin/postView");
		return view;
	}
	@RequestMapping(value = "/post/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute PostDTO postDTO,HttpServletRequest request, HttpServletResponse response,BindingResult bindingResult){
		String result="";
		result = adminPostService.updatePost(postDTO,bindingResult);
		return result;
	}
	@RequestMapping(value = "/post/delete",method=RequestMethod.POST)
	@ResponseBody
	public int delete(@ModelAttribute PostDTO postDTO,HttpServletRequest request, HttpServletResponse response){
		int result=-1;
		result = adminPostService.removePost(postDTO);
		return result;
	}
}
