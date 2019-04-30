package com.hjb.jBlog.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.dto.LogDTO;

@Repository
public class LogDAOImpl {
	@Autowired
	SqlSession sqlSession;
	
	public int insertLog(LogDTO logDTO){
		return sqlSession.insert("insertLog", logDTO);
	}

}
