package com.hjb.jBlog.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjb.jBlog.dao.impl.HeaderDAOImpl;

@Service
public class HeaderServiceImpl {
	@Autowired
	private HeaderDAOImpl headerDAO;
	
	public Map<String,String> selectMainHeader() {
		return headerDAO.selectMainHeader();
	} 
}
