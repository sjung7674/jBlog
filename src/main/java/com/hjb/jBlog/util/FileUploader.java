package com.hjb.jBlog.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploader {
	/** JPEG의 시작 코드*/ 
	private static int JPEG_SOI_MARKER = 0xffd8;
	/** GIF의 시작 코드*/ 
	private static int GIF_SOI_MARKER = 0x4749;
	/** PNG의 시작 코드*/ 
	private static int PNG_SOI_MARKER = 0x8950;
	/** BMP의 시작 코드*/ 
	private static int BMP_SOI_MARKER = 0x424d;
	
	public void uploadFileJGPB(MultipartFile multipartFile, String fileName, String dir) throws IOException{
		
		try {
			FileUtils.forceMkdir(new File(dir));
			
			if (multipartFile != null) {
				if (!multipartFile.isEmpty()) {
					File newFile = new File(String.format("%s/%s", dir, fileName));
					multipartFile.transferTo(newFile);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 업로드된 파일을 저장하기 전에 JPEG,GIF,PNG,BMP 이미지인지 확인한다. 
	 * @param multipartFile 
	 * @return JPEG,GIF,PNG,BMP 이미지이면 true. 아니면 false
	 * @throws IOException 
	 */
	public boolean isJGPBImage(MultipartFile multipartFile) throws IOException { 
			
			String fileName = multipartFile.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			if ( fileExt.equalsIgnoreCase("jpg")||fileExt.equalsIgnoreCase("jpeg")||fileExt.equalsIgnoreCase("png")
					||fileExt.equalsIgnoreCase("gif")||fileExt.equalsIgnoreCase("bmp") ){}else{return false;}
			
			byte[] imageHeader = new byte[2];
			//multipartFile.getInputStream().read();
			InputStream is = multipartFile.getInputStream();
			is.read(imageHeader);
			is.close();
			int head = getShortBigEndian(imageHeader, 0);
			
			if (head == JPEG_SOI_MARKER || head == GIF_SOI_MARKER || head == PNG_SOI_MARKER || head == BMP_SOI_MARKER) {
				return true;
			} else {
				return false;
			}
	} 
	public boolean is10MBOver(MultipartFile multipartFile) { 
		long maxSize = 1024 * 1024 * 10;
		long fileSize = multipartFile.getSize();
		if (fileSize>maxSize) {
			return true;
		}
		return false;
	}
	private int getShortBigEndian(byte[] a, int offs) { 
		return (a [offs]&0xff) <<8| (a [offs +1]&0xff) <<0; 
	} 

}
