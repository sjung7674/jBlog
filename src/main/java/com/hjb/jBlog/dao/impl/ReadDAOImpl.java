package com.hjb.jBlog.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.dao.ReadDAO;
import com.hjb.jBlog.dto.PostDTO;

@Repository
public class ReadDAOImpl implements ReadDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public PostDTO readPost(String idx) {
		return sqlSession.selectOne("selectPostByIdx", idx);
	}

	@Override
	public List postListNewest(Map<String,Object> m) {		
		return  sqlSession.selectList("selectPostListNewest",m);
	}

	@Override
	public int selectCountPostListNewest(String category_no) {
		return sqlSession.selectOne("selectCountPostListNewest",category_no);
	}

}
