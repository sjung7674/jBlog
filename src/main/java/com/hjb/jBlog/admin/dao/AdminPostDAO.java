package com.hjb.jBlog.admin.dao;

import java.util.List;

import com.hjb.jBlog.dto.PostDTO;

public interface AdminPostDAO {
	public List<PostDTO> selectPostList();
	public int updatePost(PostDTO postDTO);
	public int deletePost(PostDTO postDTO);
}
