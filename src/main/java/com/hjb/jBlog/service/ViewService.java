package com.hjb.jBlog.service;

import java.util.ArrayList;
import java.util.Map;

import com.hjb.jBlog.dto.PostDTO;

public interface ViewService {
	public PostDTO selectViewByIdx(String idx);
	public ArrayList<PostDTO> postList(Map<String,Object> m);
	public int selectCountPostListNewest(String category_no);
	public PostDTO selectViewByIdxAndUserId(String m);
}
