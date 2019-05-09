package com.hjb.jBlog.admin.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.admin.dao.AdminCategoryDAO;
import com.hjb.jBlog.dto.CategoryDTO;

@Repository
public class AdminCategoryDAOImpl implements AdminCategoryDAO{
	@Autowired
	private SqlSession sqlSession;
	@Override
	public int insertCategory(CategoryDTO dto) {
		return sqlSession.insert("insertCategory", dto);
	}

	@Override
	public int modifyCategory(CategoryDTO dto) {
		return sqlSession.update("updateCategory", dto);
	}

	@Override
	public int deleteCategory(CategoryDTO dto) {
		return sqlSession.delete("deleteCategory",dto);
	}

	@Override
	public CategoryDTO selectCategoryByIdx(CategoryDTO dto) {
		return sqlSession.selectOne("selectCategoryByIdx", dto);
	}
	

}
