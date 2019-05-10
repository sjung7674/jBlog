package com.hjb.jBlog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hjb.jBlog.common.AdminLoginFailHandler;
import com.hjb.jBlog.common.AdminLoginSuccessHandler;
import com.hjb.jBlog.common.LoginFailHandler;
import com.hjb.jBlog.common.LoginSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired 
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired 
	private LoginFailHandler loginFailHandler;
	@Autowired 
	private AdminLoginSuccessHandler adminLoginSuccessHandler;
	@Autowired 
	private AdminLoginFailHandler adminLoginFailHandler;
	@Autowired
	@Qualifier("member")
	private UserDetailsService userDetailsService;
	@Autowired
	@Qualifier("admin")
	private UserDetailsService adminUserDetailsService;
	@Configuration
	public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Override
		  public void configure(WebSecurity web) throws Exception {
		    web
		      .ignoring()
		         .antMatchers("/**/*.js","/**/*.css","/**/*.png","/**/*.jpg","/vendor/**");
		  }
		@Override
		@Order(1)
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/smarteditor2/**").hasRole("USER")
					.antMatchers("/write").hasRole("USER")
					.antMatchers("/save").hasRole("USER")
					.antMatchers("/modify").hasRole("USER")
					.antMatchers("/file_upload_handler").hasRole("USER")
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
					.logoutSuccessUrl("/1")
					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))//기본이 post라 get으로 변경
					.invalidateHttpSession(true)
					.and()
				.headers()
					.frameOptions()
					.sameOrigin()
					.and()
				.exceptionHandling()
					.accessDeniedPage("/member/login");
		
		}
		@Override 
		protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		}
	}
		@Configuration
		@Order(2)
		public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter{
			
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http
					.antMatcher("/admin/**")
					.authorizeRequests()
						.antMatchers("/admin/login").permitAll()
						.antMatchers("/admin/**").hasRole("ADMIN")
						.and()
					.formLogin()
						.loginPage("/admin/login")
						.loginProcessingUrl("/admin/login")
						.usernameParameter("admin_id")
						.passwordParameter("admin_pw")
						.successHandler(adminLoginSuccessHandler)
						.failureHandler(adminLoginFailHandler)
						.and()
					.logout()
						.logoutSuccessUrl("/admin/login")
						.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))//기본이 post라 get으로 변경
						.invalidateHttpSession(true)
						.and()
					.headers()
						.frameOptions()
						.sameOrigin()
						.and()
					.exceptionHandling()
						.accessDeniedPage("/admin/login");
			
			}
			@Override 
			protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			    auth.userDetailsService(adminUserDetailsService).passwordEncoder(encoder);
			}
		}
}
