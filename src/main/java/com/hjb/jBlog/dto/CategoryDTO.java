package com.hjb.jBlog.dto;

public class CategoryDTO {
	private int idx = -1;
	private String category = "";
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
	@Override
	public String toString() {
		return "CategoryDTO [idx=" + idx + ", category=" + category + "]";
	}
	
	
	
}
