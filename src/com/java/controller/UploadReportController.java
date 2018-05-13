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
import com.java.entity.Student;
import com.java.entity.User;
import com.java.service.ShixunInfoService;
import com.java.service.StudentService;
import com.java.util.ResponseUtil;
import com.java.util.TimeUtil;

import net.sf.json.JSONObject;

@Controller//上传文件
@RequestMapping(value="/upload")
public class UploadReportController {
	
	    @Resource
	    private StudentService studentService;
	    
	    @Resource
	    private ShixunInfoService shixunInfoService;
	
	    @RequestMapping(value="/reportpdf", method=RequestMethod.POST    )  
	    public String ngUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	//一级目录
	    	String rootpath="d://msFile/upload/";
	    	String seePath="d://msFile/upload/report/";
	    	User user=(User) request.getSession().getAttribute("user");
	    	String sno=user.getUsers(); 
	    	Student student=new Student();
	    	student.setSno(sno);
	    	Student stu=studentService.findStudent(student).get(0);
	    	//二级目录
	    	String s= stu.getSno()+"-"+stu.getSname()+"\\";
	    	String path1=rootpath+stu.getCla().getCname()+"\\"+s;
	     
	    	JSONObject result=new JSONObject();
	        CommonsMultipartResolver multipartResolver =  
	            new CommonsMultipartResolver(request.getSession().getServletContext());  
	        if(multipartResolver.isMultipart(request)){  
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
	            Iterator iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){
	                String name=(String)iter.next();  
	                MultipartFile file = multiRequest.getFile(name);
	             
	                if(file != null){
	                	//获取上传文件名称
	                	String 	sourceName=file.getOriginalFilename();
	                	//判断是实训报告还是程序源代码
	                	String fileName="";
	                	if(sourceName.endsWith(".pdf")){
	                		 fileName ="实训报告.pdf";
	                		 //三级目录
	 	                    String path2 = path1+fileName;  
	 	                    File localFile = new File(path2);  
	 	                    if(!localFile.getParentFile().exists()) {  
	 	                         localFile.getParentFile().mkdirs();  
	 	                     }  
	 	                    //复制一个可以预览的路径
	 	                    String seePath2 = seePath+stu.getSno()+".pdf";  
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
	                	}else{
	                		fileName ="程序源代码.rar";
	                		 //三级目录
	 	                    String path2 = path1+fileName;  
	 	                    File localFile = new File(path2);  
	 	                    if(!localFile.getParentFile().exists()) {  
	 	                         localFile.getParentFile().mkdirs();  
	 	                     } 
	 	                   try {  
		                        file.transferTo(localFile); 
		                    } catch (IOException e) {  
		                        e.printStackTrace();  
		                    }
	                	}
	                }  
	            }  
	          } 
	        TimeUtil tu=new TimeUtil();
            String time=tu.getNow();
            ShixunInfo shi=new ShixunInfo();
            shi.setSxpdfState("1");
            shi.setSxpdfTime(time);
            shi.setSno(sno);
            shi.setCodeState("1");
            shixunInfoService.updateShixunInfo(shi);
            result.put("result", "1");
	        result.put("time", time);
			ResponseUtil.write(response, result);
	        return null;  
	    }  
}