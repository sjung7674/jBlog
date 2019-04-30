package com.hjb.jBlog.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.hjb.jBlog.dto.PostDTO;

public interface WriteService {

	public String FileUploadService(MultipartFile mpf);
	public String insertPost(PostDTO postDTO,BindingResult bindingResult);
	public String updatePost(PostDTO postDTO,BindingResult bindingResult);
	public void removePost(PostDTO postDTO);
}
