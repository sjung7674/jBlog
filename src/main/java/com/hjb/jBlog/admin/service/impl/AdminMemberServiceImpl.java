package com.hjb.jBlog.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hjb.jBlog.admin.dao.impl.AdminMemberDAOImpl;
import com.hjb.jBlog.admin.dto.AdminMemberDTO;

@Service
@Qualifier(value="admin")
public class AdminMemberServiceImpl implements UserDetailsService{
	
	@Autowired
	private AdminMemberDAOImpl adminMemberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		AdminMemberDTO member = adminMemberDAO.selectUserByUserid(id);
		if(member == null){
			throw new UsernameNotFoundException(id);
		}
		return member;
	}

}
