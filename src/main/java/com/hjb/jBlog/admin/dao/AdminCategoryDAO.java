package com.hjb.jBlog.admin.dao;

import java.util.HashMap;

import com.hjb.jBlog.dto.CategoryDTO;

public interface AdminCategoryDAO {
	public int insertCategory(CategoryDTO dto);
	public int modifyCategory(HashMap<String,String> map);
	public int deleteCategory(HashMap<String,String> map);
}
