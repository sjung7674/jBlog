package com.hjb.jBlog.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hjb.jBlog.common.LoginFailHandler;
import com.hjb.jBlog.common.LoginSuccessHandler;
import com.hjb.jBlog.service.impl.MemberServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired 
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired 
	private LoginFailHandler loginFailHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/write").hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login")
				.usernameParameter("inputEmail")
				.passwordParameter("inputPassword")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailHandler)
				.and()
			.logout()
				.logoutSuccessUrl("/member/logout")
				.logoutUrl("/member/j_spring_security_logout")
				.invalidateHttpSession(true);
		http.antMatcher("**/smarteditor2/**").headers().frameOptions().sameOrigin();
	}

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	
}
