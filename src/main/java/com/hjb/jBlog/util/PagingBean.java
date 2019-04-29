package com.hjb.jBlog.util;

import java.io.Serializable;

public class PagingBean implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalItems;							// 게시물수
	private int itemsPerPage = 5;					// 페이지당 갯수?
	private int pagesPerPagegroup = 1;				// 페이지갯수
	private int page;
	public PagingBean(int totalItems, int page) {
		super();
		this.totalItems = totalItems;
		this.page = page;
	}
	public PagingBean() {
	}
	public PagingBean(int totalItems, int page, int itemsPerPage, int pagesPerPagegroup){
		super();
		this.totalItems = totalItems;
		this.page = page;
		this.itemsPerPage = itemsPerPage;
		this.pagesPerPagegroup = pagesPerPagegroup;
	}
	public PagingBean(int totalItems, int page, int itemsPerPage){
		super();
		this.totalItems = totalItems;
		this.page = page;
		this.itemsPerPage = itemsPerPage;
	}
	public PagingBean(int totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPage(){
		int totalPage = 0;
		if(totalItems%itemsPerPage != 0){
			totalPage = totalItems/itemsPerPage +1;
		}else{
			totalPage = totalItems/itemsPerPage;
		}		
		return totalPage;
	}
	private int getCurrentPageGroup(){
		
		int currentPageGroup = 0;
		if(page%pagesPerPagegroup != 0){
			currentPageGroup = page / pagesPerPagegroup +1;
		}else{
			currentPageGroup = page / pagesPerPagegroup;
		}
		return currentPageGroup;
	}
	public int getTotalPageGroup(){
		
		int totalPageGroup = 0;
		int totalPage = getTotalPage();
		if(totalPage%pagesPerPagegroup != 0){
			totalPageGroup = totalPage/pagesPerPagegroup + 1;
		}else{
			totalPageGroup = totalPage/pagesPerPagegroup;
		}		
		return totalPageGroup;
	}
	public boolean isPreviousPageGroup(){		
		boolean flag = false;
		if(getCurrentPageGroup()!=1){
			flag = true;
		}		
		return flag;
	}
	public boolean isNextPageGroup(){		
		boolean flag = false;
		if(getCurrentPageGroup() < getTotalPageGroup()){
			flag = true;
		}		
		return flag;
	}
	public int getBeginPage(){
		int startPageNo = (getCurrentPageGroup()-1)*pagesPerPagegroup+1;
		return startPageNo;
	}	
	public int getEndPage(){
		int endPageNo =getCurrentPageGroup()*pagesPerPagegroup;
		if(endPageNo>getTotalPage()){
			endPageNo = getTotalPage();
		}		
		return endPageNo;
	}
	public int getPage(){
		return page;
	}
	public int getTotalItem(){
		return totalItems;
	}
	public int getItemsPerPage(){
		return itemsPerPage;
	}
}
