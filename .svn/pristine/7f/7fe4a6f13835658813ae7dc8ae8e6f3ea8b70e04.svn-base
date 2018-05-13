package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.java.entity.Cla;
import com.java.service.AdviceService;
import com.java.util.ResponseUtil;
import com.java.util.TimeUtil;
import com.java.util.ZipUtil;

import net.sf.json.JSONObject;

@Controller//下载文件
@RequestMapping(value="/downloadAdvice")
public class DownloadAdviceController {
	
	     @Resource
	    private AdviceService adivceService;
	   
	     
	    @RequestMapping("/down")  
	    public ResponseEntity<byte[]>  downFile(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	String rootpath="d://msFile/upload/advice/";
	    	String filename=request.getParameter("filename");
	    	String path=rootpath+"\\" +filename;
	    	 File file2 = new File(path);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders header = new HttpHeaders();
		     header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     header.setContentDispositionFormData("attachment", dfileName);
		    return new  ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), header, HttpStatus.CREATED);
	    } 
	    
}
