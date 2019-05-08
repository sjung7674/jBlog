package com.hjb.jBlog.dao;

import java.util.List;
import java.util.Map;

import com.hjb.jBlog.dto.CategoryDTO;

public interface CategoryDAO {
	public List<CategoryDTO> selectCategory();
}
