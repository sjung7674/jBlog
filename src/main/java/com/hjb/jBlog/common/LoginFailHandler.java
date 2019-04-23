package com.hjb.jBlog.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler{
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String errormsg = null;
        if(exception instanceof BadCredentialsException) {
            errormsg = "아이디나 비밀번호가 맞지 않습니다. <br>다시 확인해주세요.";
        } 
        request.setAttribute("error", errormsg);
        request.getRequestDispatcher("/member/login").forward(request, response);
	}
	

}
