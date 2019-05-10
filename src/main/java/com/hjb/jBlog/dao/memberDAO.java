package com.hjb.jBlog.dao;

import java.util.List;
import java.util.Map;

import com.hjb.jBlog.dto.MemberDTO;

public interface memberDAO {
	public MemberDTO selectUserByUserid(String id);
	public List<MemberDTO> selectUserList();
}
