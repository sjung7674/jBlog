package com.hjb.jBlog.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl {
	@Value("${upload.path}")
	private String upload_path;
	public String getImage(String imgName,String path, ServletOutputStream out) throws IOException{
		String ext      = imgName.substring(imgName.lastIndexOf(".")+1);
		String realFolder = upload_path;
		if(!path.equals("")){
			realFolder = realFolder + File.separator + path;
		}
		BufferedImage bi = null;
		File file = new File(realFolder +File.separator+ imgName);
		String contentType="";
		if(!file.exists() || !file.isFile()) {
			System.out.println("nofile");
		}else{
			bi = ImageIO.read(file);
			contentType = "image/"+ext.toLowerCase();
			ImageIO.write(bi,ext.toLowerCase(),out);
		}
		return contentType;
	}
}
