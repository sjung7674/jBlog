package com.hjb.jBlog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hjb.jBlog.dto.PostDTO;

public interface ReadDAO {
	public PostDTO readPost(String str);
	public List postListNewest(Map<String,Object> m);
	public int selectCountPostListNewest(String category_no);
	public PostDTO selectViewByIdxAndUserId(PostDTO postDTO);
}
