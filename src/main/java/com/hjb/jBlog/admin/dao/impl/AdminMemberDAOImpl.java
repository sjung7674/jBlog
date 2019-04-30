package com.hjb.jBlog.admin.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.admin.dao.AdminMemberDAO;
import com.hjb.jBlog.admin.dto.AdminMemberDTO;

@Repository
public class AdminMemberDAOImpl implements AdminMemberDAO{
	@Autowired
	SqlSession sqlsession;
	
	@Override
	public AdminMemberDTO selectUserByUserid(String id) {
		return sqlsession.selectOne("selectAdminByUserid", id);
	}

}
