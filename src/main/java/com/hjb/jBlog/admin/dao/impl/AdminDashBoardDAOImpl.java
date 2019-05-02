package com.hjb.jBlog.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjb.jBlog.admin.dao.AdminDashBoardDAO;

@Repository
public class AdminDashBoardDAOImpl implements AdminDashBoardDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Map<String,String>> selectAccessCnt() {
		return sqlSession.selectList("selectAccessCnt");
	}

	@Override
	public List<Map<String, String>> selectDeviceCnt() {
		return sqlSession.selectList("selectDeviceCnt");
	}
	
	
	
}
