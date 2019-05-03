package com.hjb.jBlog.admin.service;

import org.springframework.web.multipart.MultipartFile;

public interface AdminHeaderService {
	public String updateHeader(String title, String subtitle, MultipartFile mpf);
}
