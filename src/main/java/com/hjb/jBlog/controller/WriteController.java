package com.hjb.jBlog.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hjb.jBlog.dto.PostDTO;
import com.hjb.jBlog.service.impl.ImageServiceImpl;
import com.hjb.jBlog.service.impl.ViewServiceImpl;
import com.hjb.jBlog.service.impl.WriteServiceImpl;

@Controller
public class WriteController {
	
	@Autowired
	private WriteServiceImpl writeService; 
	@Autowired
	private ImageServiceImpl imageService;
	@Autowired
	private ViewServiceImpl viewService;
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("member/write");
		return view;
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		String idx = request.getParameter("idx")==null?"":request.getParameter("idx");
		view.addObject("post_dto",viewService.selectViewByIdxAndUserId(idx));
		view.setViewName("member/write");
		return view;
	}
	@RequestMapping(value="/file_upload_handler",method=RequestMethod.POST)
	public void file_upload_handler(HttpServletResponse response, MultipartHttpServletRequest request) throws IOException{
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		String result="error";
		
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		result = writeService.FileUploadService(mpf);
		out.print(result);
	}
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute PostDTO postDTO,HttpServletRequest request, HttpServletResponse response,BindingResult bindingResult){
		String result="";
		result = writeService.updatePost(postDTO,bindingResult);
		return result;
	}
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute PostDTO postDTO,HttpServletRequest request, HttpServletResponse response,BindingResult bindingResult){
		String result="";
		result = writeService.insertPost(postDTO,bindingResult);
		return result;
	}
	@RequestMapping(value = "/remove",method=RequestMethod.POST)
	public String remove(@ModelAttribute PostDTO postDTO,HttpServletRequest request, HttpServletResponse response){
		writeService.removePost(postDTO);
		return "redirect:/";
	}
	@RequestMapping(value="/getImg",method=RequestMethod.GET)
	@ResponseBody
	public void getImg(HttpServletRequest request , HttpServletResponse response) throws IOException{
		String imgName = request.getParameter("imgName")==null?"":request.getParameter("imgName");
		ServletOutputStream out= null;
		String contentType="";
		try
		{
			out = response.getOutputStream();
			contentType = imageService.getImage(imgName, out);
			response.setContentType(contentType);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
	}
}
