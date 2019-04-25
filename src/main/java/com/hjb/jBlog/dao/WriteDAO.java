package com.hjb.jBlog.dao;

import com.hjb.jBlog.dto.PostDTO;

public interface WriteDAO {
	public int insertPost(PostDTO postDTO);
}
