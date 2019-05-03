package com.hjb.jBlog.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeaderDAOImpl {
	@Autowired
	private SqlSession sqlSession;
	
	public Map<String, String> selectMainHeader() {
		return sqlSession.selectOne("selectMainHeader");
	}
}
