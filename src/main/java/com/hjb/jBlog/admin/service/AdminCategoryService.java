package com.hjb.jBlog.admin.service;

import java.util.HashMap;

import com.hjb.jBlog.dto.CategoryDTO;

public interface AdminCategoryService {
	public String saveCategory(CategoryDTO dto);
	public int modifyCategory(HashMap<String,String> map);
	public int deleteCategory(HashMap<String,String> map);
}
