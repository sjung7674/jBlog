package com.hjb.jBlog.admin.service;

import java.util.HashMap;

import com.hjb.jBlog.dto.CategoryDTO;

public interface AdminCategoryService {
	public String saveCategory(CategoryDTO dto);
	public String modifyCategory(CategoryDTO dto);
	public String deleteCategory(CategoryDTO dto);
}
