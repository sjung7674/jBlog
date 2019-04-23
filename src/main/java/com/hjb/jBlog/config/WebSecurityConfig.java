package com.hjb.jBlog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired 
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired 
	private LoginFailHandler loginFailHandler;
	@Autowired 
	private MemberServiceImpl memberServiceImpl;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/write").hasRole("ROLE_USER")
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
				
	}

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.authenticationProvider(authenticationProvider)
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder()); 
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	} 
	
	@Bean
	public AuthenticationProvider springAuthenticationProvider() {
		
		return new AuthenticationProvider();
	}

	
}
