package com.hjb.jBlog.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.dao.CategoryDAO;
import com.hjb.jBlog.dto.CategoryDTO;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public List<CategoryDTO> selectCategory() {
		return sqlsession.selectList("selectCategory");
	}
	

}
