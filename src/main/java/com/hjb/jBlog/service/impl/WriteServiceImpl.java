package com.hjb.jBlog.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.hjb.jBlog.dao.impl.CategoryDAOImpl;
import com.hjb.jBlog.dao.impl.WriteDAOImpl;
import com.hjb.jBlog.dto.MemberDTO;
import com.hjb.jBlog.dto.PostDTO;
import com.hjb.jBlog.service.CategoryService;
import com.hjb.jBlog.service.WriteService;
import com.hjb.jBlog.util.FileUploader;
import com.hjb.jBlog.validator.PostValidator;

@Service
public class WriteServiceImpl implements WriteService,CategoryService{
	
	@Autowired
	WriteDAOImpl writeDao;
	@Autowired
	CategoryDAOImpl categoryDao;
	
	@Override
	public String FileUploadService(MultipartFile mpf) {
		String result="";
		String orgFileName = mpf.getOriginalFilename();
		FileUploader fu = new FileUploader();
		if(fu.is10MBOver(mpf)){
			result="over_size";
		}else{
			try{
				if(fu.isJGPBImage(mpf)){
					try{
						String fileExt = orgFileName.substring(orgFileName.lastIndexOf(".")+1, orgFileName.length());
						String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"."+fileExt;
						String dir = "D:"+File.separator+"upload";
						fu.uploadFileJGPB(mpf, fileName, dir);
						result=fileName;
					}catch(IOException e){
						e.printStackTrace();
						result="error";
					}
				}else{
					result="not_JGPBImage";
				}
			}catch(IOException e){
				e.printStackTrace();
				result="error";
			}
		}
		return result;
	}

	@Override
	public String insertPost(PostDTO postDTO,BindingResult bindingResult) {
		Gson gson = new Gson();
		HashMap<String,List<HashMap<String,String>>> result_map = new HashMap<String,List<HashMap<String,String>>>();
		List<HashMap<String,String>> obj_list= new ArrayList<HashMap<String,String>>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		MemberDTO user = (MemberDTO) authentication.getPrincipal();
		postDTO.setUserid(user.getUsername());

		PostValidator postValidator = new PostValidator();
		postValidator.validate(postDTO, bindingResult);
		if (bindingResult.hasErrors()) {
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for(FieldError e :fieldErrors){
				HashMap<String,String> error = new HashMap<String,String>();
				error.put("objectName", e.getObjectName());
				error.put("field", e.getField());
				error.put("code", e.getCode());
				error.put("message", e.getDefaultMessage());
				obj_list.add(error);
			}
			
			result_map.put("errors", obj_list);
        }else{
        	String ret="";
        	if(postDTO.getHeader_image_file()!=null){
        		ret=FileUploadService(postDTO.getHeader_image_file());
        	}
        	HashMap<String,String> error = new HashMap<String,String>();
        	if(ret.equals("over_size")){
    			error.put("message", "이미지는 10MB 까지 업로드 가능합니다.");
    			obj_list.add(error);
    			result_map.put("errors", obj_list);
        	}else if(ret.equals("not_JGPBImage")){
        		error.put("message", "이미지는 jpg,gif,png,bmp 만 업로드 가능합니다.");
    			obj_list.add(error);
    			result_map.put("errors", obj_list);
        	}else if(ret.equals("error")){
        		error.put("message", "이미지업로드 오류가 발생하였습니다.");
    			obj_list.add(error);
    			result_map.put("errors", obj_list);
        	}else{
        		postDTO.setHeader_image(ret);
        		if(writeDao.insertPost(postDTO)==1){
            		HashMap<String,String> success = new HashMap<String,String>();
            		success.put("message", "입력 되었습니다.");
            		obj_list.add(success);
        			result_map.put("success", obj_list);
            	}else{
        			error.put("message", "오류가 발생하였습니다.");
        			obj_list.add(error);
        			result_map.put("errors", obj_list);
            	}
        	}
        	
        }
		
		return gson.toJson(result_map);
	}

	@Override
	public List<Map<String, String>> getCategory() {
		return categoryDao.selectCategory();
	}

}
