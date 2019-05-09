package com.hjb.jBlog.admin.dao;

import java.util.HashMap;

import com.hjb.jBlog.dto.CategoryDTO;

public interface AdminCategoryDAO {
	public int insertCategory(CategoryDTO dto);
	public int modifyCategory(CategoryDTO dto);
	public int deleteCategory(CategoryDTO dto);
	public CategoryDTO selectCategoryByIdx(CategoryDTO dto);
}
