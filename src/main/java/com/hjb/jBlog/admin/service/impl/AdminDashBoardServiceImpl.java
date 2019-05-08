package com.hjb.jBlog.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjb.jBlog.admin.dao.impl.AdminDashBoardDAOImpl;

@Service
public class AdminDashBoardServiceImpl {
	@Autowired
	private AdminDashBoardDAOImpl adminDashBoard;
	
	public void selectAccessCnt(List<String> label, List<String> data){
		List<Map<String,String>> list = adminDashBoard.selectAccessCnt();
		for(Map<String,String> m : list){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			label.add(fmt.format(m.get("date")));
			data.add(String.valueOf(m.get("count")));
		}
	}
	public void selectDeviceCnt(List<String> label, List<String> data){
		List<Map<String,String>> list = adminDashBoard.selectDeviceCnt();
		for(Map<String,String> m : list){
			label.add(m.get("device"));
			data.add(String.valueOf(m.get("count")));
		}
	}
}
