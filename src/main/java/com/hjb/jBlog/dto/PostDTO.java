package com.hjb.jBlog.dto;

import java.util.Date;

public class PostDTO {
	private int idx = -1;
	private String userid = "";
	private String category = "";
	private String title = "";
	private String content = "";
	private Date reg_date = null;
	private String user_image="";
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	@Override
	public String toString() {
		return "PostDTO [idx=" + idx + ", userid=" + userid + ", category=" + category + ", title=" + title
				+ ", content=" + content + ", reg_date=" + reg_date + ", user_image=" + user_image + "]";
	}
	
	
	
}
