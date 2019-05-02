package com.hjb.jBlog.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hjb.jBlog.admin.service.impl.AdminDashBoardServiceImpl;

@Controller
@RequestMapping(value="/admin")
public class AdminMainController {
	
	@Autowired
	private AdminDashBoardServiceImpl adminDashBoard;
	
	@RequestMapping(value = "/login")
	public String admin_login() {
		return "admin/login";
	}
	@RequestMapping(value = "/")
	public String admin_root() {
		return "redirect:/admin/index";
	}
	@RequestMapping(value = "/index")
	public ModelAndView admin_index() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/admin/index");
		return view;
	}
	@RequestMapping(value ="/getAccessCnt",method=RequestMethod.POST)
	@ResponseBody
	public String getAccessCnt(){
		Gson gson = new Gson();
		List<String> label = new ArrayList<String>();
		List<String> data = new ArrayList<String>();
		adminDashBoard.selectAccessCnt(label, data);
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		map.put("label",label);
		map.put("data",data);
		return gson.toJson(map);
	}
	@RequestMapping(value ="/getDevicePer",method=RequestMethod.POST)
	@ResponseBody
	public String getDevicePer(){
		Gson gson = new Gson();
		List<String> label = new ArrayList<String>();
		List<String> data = new ArrayList<String>();
		adminDashBoard.selectDeviceCnt(label, data);
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		map.put("label",label);
		map.put("data",data);
		return gson.toJson(map);
	}
}
