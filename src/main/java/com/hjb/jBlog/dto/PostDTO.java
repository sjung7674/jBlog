package com.hjb.jBlog.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PostDTO {
	private int idx = -1;
	private String userid = "";
	private String category = "";
	private String sub_title = "";
	private String title = "";
	private String content = "";
	private Date reg_date = null;
	private String user_image="";
	private String header_image="";
	private MultipartFile header_image_file=null;
	private String read_cnt="";
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
	
	public String getHeader_image() {
		return header_image;
	}
	public void setHeader_image(String header_image) {
		this.header_image = header_image;
	}
	public MultipartFile getHeader_image_file() {
		return header_image_file;
	}
	public void setHeader_image_file(MultipartFile header_image_file) {
		this.header_image_file = header_image_file;
	}
	
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	
	public String getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(String read_cnt) {
		this.read_cnt = read_cnt;
	}
	@Override
	public String toString() {
		return "PostDTO [idx=" + idx + ", userid=" + userid + ", category=" + category + ", sub_title=" + sub_title
				+ ", title=" + title + ", content=" + content + ", reg_date=" + reg_date + ", user_image=" + user_image
				+ ", header_image=" + header_image + ", header_image_file=" + header_image_file + "]";
	}
	
	
	
	
}
