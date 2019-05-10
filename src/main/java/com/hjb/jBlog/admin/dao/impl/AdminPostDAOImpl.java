package com.hjb.jBlog.admin.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.admin.dao.AdminPostDAO;
import com.hjb.jBlog.dto.PostDTO;

@Repository
public class AdminPostDAOImpl implements AdminPostDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PostDTO> selectPostList() {
		return sqlSession.selectList("selectPostList");
	}

	@Override
	public int updatePost(PostDTO dto) {
		return sqlSession.update("adminUpdatePost", dto);
	}

	@Override
	public int deletePost(PostDTO postDTO) {
		return sqlSession.delete("deletePost", postDTO);
	}
	
	
}
