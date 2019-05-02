package com.hjb.jBlog;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hjb.jBlog.admin.dto.AdminMemberDTO;
import com.hjb.jBlog.dao.impl.LogDAOImpl;
import com.hjb.jBlog.dto.LogDTO;
import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.service.impl.WriteServiceImpl;

public class SiteControlInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	WriteServiceImpl writeService;
	@Autowired
	LogDAOImpl logDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("in--");
		String not_access = "ico|js|jpg|png|gif|bmp|css|image|img|font|vender|smarteditor2|getImg|admin";
		String domain = request.getRequestURL().toString();
		Pattern not_access_pattern = Pattern.compile(not_access);
		Matcher na = not_access_pattern.matcher(domain);
		if(na.find()){
			return true;
		}
		System.out.println("+++"+domain);
		request.setAttribute("categoryList", writeService.getCategory());
		Enumeration e = request.getSession().getAttributeNames();
		while(e.hasMoreElements()){
			System.out.println(e.nextElement());
		}
		String param_sub="";
		request.setCharacterEncoding("UTF-8");
		Enumeration params = request.getParameterNames();
		
		while (params.hasMoreElements()){
		    String name = (String)params.nextElement();
		    param_sub+= name + " : " +request.getParameter(name)+"|";
		}
		LogDTO logDto = new LogDTO();		
		Calendar now = Calendar.getInstance();
		logDto.setYear_(now.get(Calendar.YEAR));
		logDto.setMonth_(now.get(Calendar.MONTH) + 1);
		logDto.setDate_(now.get(Calendar.DATE));
		logDto.setWeek_(now.get(Calendar.DAY_OF_WEEK));
		logDto.setHour_(now.get(Calendar.HOUR_OF_DAY));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		if(authentication!=null && authentication.getPrincipal() instanceof MemberDTO){
			MemberDTO user = (MemberDTO) authentication.getPrincipal();
			logDto.setUserid(user.getUsername());
			logDto.setUsertype("1");
		}else{
			logDto.setUserid("guest");			
			logDto.setUsertype("0");
		}
		 String userAgent = request.getHeader("User-Agent").toUpperCase();
		 if(userAgent.indexOf("MOBILE") > -1) {
		        if(userAgent.indexOf("PHONE") == -1){
		        	logDto.setDevice("PHONE");
		        }else{
		        	logDto.setDevice("TABLET");
		        }
	    } else{
	    	logDto.setDevice("PC");
	    }
		 
		 
		String prePage = request.getHeader("referer");
		if( (prePage=="undefined")||( prePage == "" )) prePage = "bookmark" ;
		
		String fname = "";
		String lname = "";
		String strUrl = "";
		String title = "jBlog";
		String strParam = param_sub;
		String[] tmp =  domain.split("://");
		tmp =  tmp[1].split("/");
		if(tmp.length >=2 ){
			
			if(tmp.length >= 3){
				fname = tmp[1];
				lname = tmp[2];
			}else{
				fname = "";
				lname = tmp[1];
			}
						
			strUrl = fname +"/"+ lname ;
			if(tmp.length>=4){
				strUrl=tmp[3];
				
			}
		}else{
			strUrl = "/";
		}

		if(strUrl != null ){
			if(strUrl.length() > 255){
				strUrl = strUrl.substring(0, 250);
			}
		}

		if(strParam != null ){
			if(strParam.length() > 999){
				strParam = strParam.substring(0, 999);
			}
		}
		if(prePage != null ){
			if(prePage.length() > 255){
				prePage = prePage.substring(0, 250);
			}
		}
		
		logDto.setUserip(request.getRemoteAddr());
		logDto.setFname(fname);
		logDto.setLname(lname);
		logDto.setName(strUrl);
		logDto.setParam(strParam);
		logDto.setTitle(title);
		logDto.setPrepage(prePage+"->"+domain);
		if(!logDto.getName().equals("/")){
			logDAO.insertLog(logDto);
		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("out--");
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
	

}
