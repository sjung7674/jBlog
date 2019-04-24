package com.hjb.jBlog.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hjb.jBlog.util.FileUploader;

@Controller
public class WriteController {
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, HttpServletResponse response){
		return "member/write";
	}
	@RequestMapping("/file_upload_handler")
	public void file_upload_handler(HttpServletResponse response, MultipartHttpServletRequest request) throws IOException{
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		String result="error";
		
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
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
						result="/getImg?imgName="+fileName;
					}catch(IOException e){
						e.printStackTrace();
					}
				}else{
					result="not_JGPBImage";
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		out.print(result);
	}
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response){
		System.out.println(request.getParameter("title"));
		return "member/write";
	}
	@RequestMapping("/getImg")
	@ResponseBody
	public void getImg(HttpServletRequest request , HttpServletResponse response) throws IOException{
		
		String imgName = request.getParameter("imgName")==null?"":request.getParameter("imgName");
		ServletOutputStream out= null;
		String ext      = imgName.substring(imgName.lastIndexOf(".")+1);
		String realFolder = "D:"+File.separator+"upload";
		BufferedImage bi = null;
		
		try
		{
			out = response.getOutputStream();
			File file = new File(realFolder +File.separator+ imgName);
			if(!file.exists() || !file.isFile()) {
				System.out.println("nofile");
			}else{
				bi = ImageIO.read(file);
				String contentType = "image/"+ext.toLowerCase();
				response.setContentType(contentType);
				ImageIO.write(bi,ext.toLowerCase(),out);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		
	}
}
