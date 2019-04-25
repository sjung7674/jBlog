package com.hjb.jBlog.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.dao.WriteDAO;
import com.hjb.jBlog.dto.PostDTO;

@Repository
public class WriteDAOImpl implements WriteDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertPost(PostDTO postDTO) {
		return sqlSession.insert("insertPost",postDTO);
	}

}
