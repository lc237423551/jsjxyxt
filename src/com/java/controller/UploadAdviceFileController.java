package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.java.entity.ShixunInfo;
import com.java.entity.Advice;
import com.java.entity.User;
import com.java.service.ShixunInfoService;
import com.java.service.AdviceService;
import com.java.util.ResponseUtil;
import com.java.util.TimeUtil;

import net.sf.json.JSONObject;

@Controller//上传文件
@RequestMapping(value="/uploadAdvice")
public class UploadAdviceFileController {
	
	    @Resource
	    private AdviceService adviceService;
	
	    @RequestMapping(value="/advicefile", method=RequestMethod.POST    )  
	    public String ngUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	//一级目录
	    	String fileName1=request.getParameter("filename");
	    	String rootpath="d://msFile/upload/";
	    	String seePath="d://msFile/upload/advice/";
	    	//二级目录
	    	String path1=seePath+"\\";
	     
	    	JSONObject result=new JSONObject();
	        CommonsMultipartResolver multipartResolver =  
	            new CommonsMultipartResolver(request.getSession().getServletContext());
	        String sourceName="";
	        if(multipartResolver.isMultipart(request)){  
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
	            Iterator iter = multiRequest.getFileNames();
	                String name=(String)iter.next();  
	                MultipartFile file = multiRequest.getFile(name);
	             
	                if(file != null){
	                	//获取上传文件名称
	                	sourceName=file.getOriginalFilename();
	                	String fileName="";
	                		 fileName = sourceName;
	                		 //三级目录
	 	                    String path2 = path1+fileName;  
	 	                    File localFile = new File(path2);  
	 	                    if(!localFile.getParentFile().exists()) {  
	 	                         localFile.getParentFile().mkdirs();  
	 	                     }  
	 	                    //复制一个可以预览的路径
	 	                    String seePath2 = seePath+sourceName;  
	 	                    File localFile1 = new File(seePath2);  
	 	                    if(!localFile1.getParentFile().exists()) {  
	 	                    	localFile1.getParentFile().mkdirs();  
	 	                     } 
	 	                   try {  
		                        file.transferTo(localFile); 
		                    	FileUtils.copyFile(localFile, localFile1);
		                        
		                    } catch (IOException e) {  
		                        e.printStackTrace();  
		                    }
	            }  
	          } 
	        TimeUtil tu=new TimeUtil();
            String time=tu.getNow();
            result.put("filename", sourceName);
            result.put("result", "1");
	        result.put("time", time);
			ResponseUtil.write(response, result);
	        return null;  
	    }  
}
