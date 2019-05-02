package com.hjb.jBlog.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hjb.jBlog.dao.impl.ReadDAOImpl;
import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.dto.PostDTO;
import com.hjb.jBlog.service.ViewService;

@Service
public class ViewServiceImpl implements ViewService{
	@Autowired
	private ReadDAOImpl readDAO;
	
	@Override
	public PostDTO selectViewByIdx(String idx) {
		PostDTO dto = readDAO.readPost(idx);
		readDAO.updateReadCnt(idx);
		dto.setContent(StringEscapeUtils.unescapeHtml4(dto.getContent()));
		return dto;
	}
	@Override
	public ArrayList<PostDTO> postList(Map<String,Object> m) {
		return (ArrayList<PostDTO>) readDAO.postListNewest(m);
	}
	@Override
	public int selectCountPostListNewest(String category_no) {
		return readDAO.selectCountPostListNewest(category_no);
	}
	@Override
	public PostDTO selectViewByIdxAndUserId(String idx) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		MemberDTO user = (MemberDTO) authentication.getPrincipal();
		PostDTO postDTO = new PostDTO();
		postDTO.setIdx(Integer.parseInt(idx));
		postDTO.setUserid(user.getUsername());
		return readDAO.selectViewByIdxAndUserId(postDTO);
	}
	
	
}
