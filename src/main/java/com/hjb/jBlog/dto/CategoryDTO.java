package com.hjb.jBlog.dto;

import org.springframework.web.multipart.MultipartFile;

public class CategoryDTO {
	private int idx = -1;
	private String category = "";
	private MultipartFile header_image=null;
	private String header_image_name="";
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public MultipartFile getHeader_image() {
		return header_image;
	}
	public void setHeader_image(MultipartFile header_image) {
		this.header_image = header_image;
	}
	public String getHeader_image_name() {
		return header_image_name;
	}
	public void setHeader_image_name(String header_image_name) {
		this.header_image_name = header_image_name;
	}
	@Override
	public String toString() {
		return "CategoryDTO [idx=" + idx + ", category=" + category + ", header_image=" + header_image
				+ ", header_image_name=" + header_image_name + "]";
	}
	
}
