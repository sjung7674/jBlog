package com.hjb.jBlog.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.dao.MemberDAO;
import com.hjb.jBlog.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDTO selectUserByUserid(String id) {
		return sqlSession.selectOne("selectUserByUserid", id);
	}

	@Override
	public List<MemberDTO> selectUserList() {
		return sqlSession.selectList("selectUserList");
	}

	@Override
	public int insertUser(MemberDTO memberDTO) {
		return sqlSession.insert("insertUser",memberDTO);
	}

	@Override
	public MemberDTO selectNaverUserByUserid(MemberDTO memberDTO) {
		return sqlSession.selectOne("selectNaverUserByUserid", memberDTO);
	}

}
