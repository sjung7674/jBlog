package com.hjb.jBlog.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${upload.path}")
	private String upload_path;
	@Override
	public String saveCategory(CategoryDTO dto) {
		MultipartFile mpf = dto.getHeader_image();
		String orgFileName = mpf.getOriginalFilename();
		FileUploader fu = new FileUploader();
		String result="error";
		if(dto.getCategory().equals("")){
			return result;
		}
		if(dto.getHeader_image()==null){
			return result;
		}
		if(fu.is10MBOver(mpf)){
			result="over_size";
		}else{
			try{
				if(fu.isJGPBImage(mpf)){
					String fileExt = orgFileName.substring(orgFileName.lastIndexOf(".")+1, orgFileName.length());
					String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"."+fileExt;
					String dir = upload_path+File.separator+"category";
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
	public String modifyCategory(CategoryDTO dto) {
		MultipartFile mpf = dto.getHeader_image();
		String result="error";
		if(dto.getCategory().equals("")){
			return result;
		}
		if(dto.getIdx()==-1){
			return result;
		}
		if(mpf!=null){
			String orgFileName = mpf.getOriginalFilename();
			FileUploader fu = new FileUploader();
			if(fu.is10MBOver(mpf)){
				result="over_size";
			}else{
				try{
					if(fu.isJGPBImage(mpf)){
						String fileExt = orgFileName.substring(orgFileName.lastIndexOf(".")+1, orgFileName.length());
						String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"."+fileExt;
						String dir = upload_path+File.separator+"category";
						fu.uploadFileJGPB(mpf, fileName, dir);
						dto.setHeader_image_name(fileName);
					}else{
						result="not_JGPBImage";
					}
				}catch(IOException e){
					e.printStackTrace();
					result="error";
				}
			}
		}
		int res = adminCategoryDAO.modifyCategory(dto);
		if(res==1){
			result="success";
		}
		return result;
	}

	@Override
	public String deleteCategory(CategoryDTO dto) {
		String result="error";
		if(dto.getIdx()==-1){
			return result;
		}
		try{
			CategoryDTO tmpDto = adminCategoryDAO.selectCategoryByIdx(dto);
			String dir =upload_path+File.separator+"category"+File.separator+tmpDto.getHeader_image_name();
			System.out.println(dir);
			File f = new File (dir);
			if(f.exists()){
				f.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		int res = adminCategoryDAO.deleteCategory(dto);
		if(res==1){
			result="success";
		}
		return result;
	}

}
