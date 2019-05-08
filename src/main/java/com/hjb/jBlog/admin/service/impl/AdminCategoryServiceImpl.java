package com.hjb.jBlog.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hjb.jBlog.admin.dao.AdminCategoryDAO;
import com.hjb.jBlog.admin.service.AdminCategoryService;
import com.hjb.jBlog.dto.CategoryDTO;
import com.hjb.jBlog.util.FileUploader;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{
	@Autowired
	private AdminCategoryDAO adminCategoryDAO;
	
	@Override
	public String saveCategory(CategoryDTO dto) {
		MultipartFile mpf = dto.getHeader_image();
		String orgFileName = mpf.getOriginalFilename();
		FileUploader fu = new FileUploader();
		String result="error";
		if(fu.is10MBOver(mpf)){
			result="over_size";
		}else{
			try{
				if(fu.isJGPBImage(mpf)){
					String fileExt = orgFileName.substring(orgFileName.lastIndexOf(".")+1, orgFileName.length());
					String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"."+fileExt;
					String dir = "D:"+File.separator+"upload"+File.separator+"category";
					fu.uploadFileJGPB(mpf, fileName, dir);
					dto.setHeader_image_name(fileName);
					int res = adminCategoryDAO.insertCategory(dto);
					if(res==1){
						result="success";
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
	public int modifyCategory(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
