package com.hjb.jBlog.admin.dao;

import com.hjb.jBlog.admin.dto.AdminMemberDTO;

public interface AdminMemberDAO {
	public AdminMemberDTO selectUserByUserid(String id);
}
