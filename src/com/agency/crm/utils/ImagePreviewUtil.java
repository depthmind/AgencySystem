package com.agency.crm.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class ImagePreviewUtil {

	public static void preview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String photoUrl=request.getParameter("photoUrl");
	    // photoUrl为接收到的路径
	    if(StringUtils.isNotBlank(photoUrl)){
	        File file = new File(photoUrl);
	        // File file=new File(photoUrl);
	        if (file.exists()) {
	            try (FileInputStream fis = new FileInputStream(file);
	                    BufferedInputStream bis = new BufferedInputStream(fis, 1024);
	                    ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);) {
	                byte[] cache = new byte[1024];
	                int length = 0;
	                while ((length = bis.read(cache)) != -1) {
	                    bos.write(cache, 0, length);
	                }
	                response.getOutputStream().write(bos.toByteArray());
	            }
	        }
	    }
	}
}
