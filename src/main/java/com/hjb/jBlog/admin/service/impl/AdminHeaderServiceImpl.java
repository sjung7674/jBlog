package com.hjb.jBlog.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hjb.jBlog.admin.dao.impl.AdminHeaderDAOImpl;
import com.hjb.jBlog.admin.service.AdminHeaderService;
import com.hjb.jBlog.util.FileUploader;

@Service
public class AdminHeaderServiceImpl implements AdminHeaderService{

	@Autowired
	private AdminHeaderDAOImpl adminHeaderDAO;
	@Value("${upload.path}")
	private String upload_path;
	@Override
	public String updateHeader(String title, String subtitle, MultipartFile mpf) {
		String result="error";
		if(title.equals("")){
			result="error||제목을 입력하세요.";
		}else{
			String orgFileName = mpf.getOriginalFilename();
			FileUploader fu = new FileUploader();
			if(fu.is10MBOver(mpf)){
				result="error||사진은 10MB 이하로 선택하세요.";
			}else{
				try{
					if(fu.isJGPBImage(mpf)){
						try{
							String fileExt = orgFileName.substring(orgFileName.lastIndexOf(".")+1, orgFileName.length());
							String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"."+fileExt;
							String dir = upload_path;
							fu.uploadFileJGPB(mpf, fileName, dir);
							HashMap<String,String> map = new HashMap<String,String>();
							map.put("title", title);
							map.put("sub_title", subtitle);
							map.put("header_image", fileName);
							int i  = adminHeaderDAO.insertMainHeader(map);
							if(i==1){
								result="success";
							}
						}catch(IOException e){
							e.printStackTrace();
							result="error";
						}
					}else{
						result="error||사진은 jpg,gif,png,bmp 파일만 가능합니다.";
					}
				}catch(IOException e){
					e.printStackTrace();
					result="error";
				}
			}
		}
		return result;
	}
	public Map<String,String> selectMainHeader() {
		return adminHeaderDAO.selectMainHeader();
	}
}
