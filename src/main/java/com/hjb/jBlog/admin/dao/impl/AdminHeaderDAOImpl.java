package com.hjb.jBlog.admin.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.admin.dao.AdminHeaderDAO;

@Repository
public class AdminHeaderDAOImpl implements AdminHeaderDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMainHeader(HashMap<String,String> map){
		return sqlSession.insert("insertMainHeader", map);
	}

	@Override
	public Map<String, String> selectMainHeader() {
		return sqlSession.selectOne("selectMainHeader");
	}
}
