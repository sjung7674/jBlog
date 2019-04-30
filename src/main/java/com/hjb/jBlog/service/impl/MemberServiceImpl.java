package com.hjb.jBlog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hjb.jBlog.dao.impl.MemberDAOImpl;
import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.service.MemberService;

@Service
@Qualifier(value="member")
public class MemberServiceImpl implements MemberService, UserDetailsService{
	
	@Autowired
	private MemberDAOImpl memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberDTO member = memberDAO.selectUserByUserid(id);
		if(member == null){
			throw new UsernameNotFoundException(id);
		}
		return member;
	}

}
