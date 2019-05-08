package com.hjb.jBlog.dto;

public class CategoryDTO {
	private int idx = -1;
	private String category = "";
	private String header_image="";
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
	
	public String getHeader_image() {
		return header_image;
	}
	public void setHeader_image(String header_image) {
		this.header_image = header_image;
	}
	@Override
	public String toString() {
		return "CategoryDTO [idx=" + idx + ", category=" + category + ", header_image=" + header_image + "]";
	}
}
