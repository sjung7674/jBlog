package com.hjb.jBlog.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl {
	public String getImage(String imgName, ServletOutputStream out) throws IOException{
		String ext      = imgName.substring(imgName.lastIndexOf(".")+1);
		String realFolder = "D:"+File.separator+"upload";
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
