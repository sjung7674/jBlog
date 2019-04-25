package com.hjb.jBlog.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.dao.CategoryDAO;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Map<String, String>> selectCategory() {
		return sqlsession.selectList("selectCategory");
	}
	

}
