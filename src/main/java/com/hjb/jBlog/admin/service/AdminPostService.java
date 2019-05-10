package com.hjb.jBlog.admin.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.hjb.jBlog.dto.PostDTO;

public interface AdminPostService {
	public List<PostDTO> postList();
	public PostDTO selectViewByIdx(String idx);
	public String updatePost(PostDTO postDTO, BindingResult bindingResult);
	public int removePost(PostDTO postDTO);
}
