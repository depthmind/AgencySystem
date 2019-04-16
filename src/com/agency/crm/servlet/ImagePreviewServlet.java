package com.agency.crm.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * Servlet implementation class ImagePreviewServlet
 */
@WebServlet("/preview")
public class ImagePreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImagePreviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
