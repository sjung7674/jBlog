package com.hjb.jBlog.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hjb.jBlog.dao.MemberDAO;
import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.service.MemberService;
import com.hjb.jBlog.validator.MemberValidator;

@Service
@Qualifier(value="member")
public class MemberServiceImpl implements MemberService, UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberDTO member = memberDAO.selectUserByUserid(id);
		if(member == null){
			throw new UsernameNotFoundException(id);
		}
		return member;
	}
	public List<MemberDTO> memberList(){
		return memberDAO.selectUserList();
	}
	@Override
	public String oauth_service(ModelAndView view,String code,String state){
		String result="";
		String clientId = "8ZMJxXbaGgxW_N0Yu9Gf";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "OPX5Lzj19g";//애플리케이션 클라이언트 시크릿값";
	    String redirectURI;
		try {
			redirectURI = URLEncoder.encode("http://localhost:8080/member/login_proc", "UTF-8");
			String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode="+responseCode);
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(responseCode==200) {
				JsonParser jsonParser = new JsonParser();
				JsonObject jsonObject = (JsonObject) jsonParser.parse(res.toString());
				result= jsonObject.get("access_token").getAsString();
				view.addObject("state",state);
			}else{
				view.addObject("error","error");
			}
		} catch ( IOException e1) {
			e1.printStackTrace();
			view.addObject("error","error");
		}
	    
	    return result;
	}
	@Override
	public String getNaverID(String token){
		String header = "Bearer " + token; // Bearer 다음에 공백 추가
		String result="error";
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
            	sb.append(inputLine);
            }
            br.close();
            JsonParser jsonParser = new JsonParser();
	    	JsonObject jsonObject = (JsonObject) jsonParser.parse(sb.toString());
	    	jsonObject = (JsonObject) jsonObject.get("response");
	    	result= jsonObject.get("id").getAsString();
        } catch (Exception e) {
        	e.printStackTrace();
        	result="error";
        }
        return result;
	}
	@Override
	public void join_proc(ModelAndView view,MemberDTO memberDTO,BindingResult bindingResult,String state){
		memberDTO.setPassword("naver");
		MemberValidator memberValidator = new MemberValidator();
		memberValidator.validate(memberDTO, bindingResult);
		if(bindingResult.hasErrors()){
			view.setViewName("member/join");
		}else{
			int i = memberDAO.insertUser(memberDTO);
			if(i==1){
				view.setViewName("redirect:/");
			}else{
				view.setViewName("redirect:/error");
			}
		}
	}
	public MemberDTO login_proc(String naverID){
		MemberDTO dto =new MemberDTO();
		dto.setUserid(naverID);
		dto.setPassword("naver");
		dto = memberDAO.selectNaverUserByUserid(dto);
		return dto;
	}
}
