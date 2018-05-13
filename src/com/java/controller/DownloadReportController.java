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
import com.java.entity.ShixunInfo;
import com.java.entity.Student;
import com.java.entity.User;
import com.java.service.ShixunInfoService;
import com.java.service.StudentService;
import com.java.util.ResponseUtil;
import com.java.util.TimeUtil;
import com.java.util.ZipUtil;

import net.sf.json.JSONObject;

@Controller//上传文件
@RequestMapping(value="/download")
public class DownloadReportController {
	
	     @Resource
	    private StudentService studentService;
	    

	     @Resource
	    private ShixunInfoService shixunInfoService;
	   
	     
	    @RequestMapping("/downOne")  
	    public ResponseEntity<byte[]>  downpdf(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	String rootpath="d://msFile/upload/";
	    	User user=(User) request.getSession().getAttribute("user");
	    	String sno=null;
	    	if(user.getRole().equals("学生")){
	    		 sno=user.getUsers();
	    	}else{
	    	     sno=request.getParameter("sno");
	    	}
	    	Student student=new Student();
	    	student.setSno(sno);
	    	Student stu=studentService.findStudent(student).get(0);
	    	String s= stu.getSno()+"-"+stu.getSname()+"\\";
	    	String path1=rootpath+stu.getCla().getCname()+"\\"+s;
	    	String path=path1+"\\" +"实训报告.pdf";
	    	 File file2 = new File(path);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders header = new HttpHeaders();
		     header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     header.setContentDispositionFormData("attachment", dfileName);
		    return new  ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), header, HttpStatus.CREATED);
	    } 
	    //下载程序源代码
	    @RequestMapping("/downCode")  
	    public ResponseEntity<byte[]>  downCode(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	String rootpath="d://msFile/upload/";
	    	String zipRootPath="d://msFile/upload/zip/";
	    	String sno=null;
	        sno=request.getParameter("sno");
	    	Student student=new Student();
	    	student.setSno(sno);
	    	Student stu=studentService.findStudent(student).get(0);
	    	String s= stu.getSno()+"-"+stu.getSname()+"\\";
	    	String path=rootpath+stu.getCla().getCname()+"\\"+s;
	// //压缩文件_____________________________________________________________________________________________________
			//设置压缩包名名称
			String zipName=stu.getSname()+"实训文档.zip";
			String zipPath=zipRootPath+zipName;
			//开始压缩
			ZipUtil cpr = new ZipUtil();
			try {
				cpr.zip(zipPath, path, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  			
	 //_________________________________________end________________________________________________________________________	
	
	    	 File file2 = new File(zipPath);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders header = new HttpHeaders();
		     header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     header.setContentDispositionFormData("attachment", dfileName);
		    return new  ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), header, HttpStatus.CREATED);
	    } 
	    @RequestMapping("/downAll")// 
	    public ResponseEntity<byte[]> downAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
	    	Cla cla= (Cla) request.getSession().getAttribute("cla");
	    	String cno=cla.getCno();
	    	String cname=cla.getCname();
	    	String rootpath="d://msFile/upload/";
	    	String zipRootPath="d://msFile/upload/zip/";
	    	String path=rootpath+cname;
	    	
	// //压缩文件_____________________________________________________________________________________________________
			
			//设置压缩包名名称
			String zipName=cname+"实训文档.zip";
			String zipPath=zipRootPath+zipName;
			File localFile = new File(zipPath);  
	         if(!localFile.getParentFile().exists()) {  
	              localFile.getParentFile().mkdirs();  
	          } 
			//开始压缩
			ZipUtil cpr = new ZipUtil();
			try {
				
				cpr.zip(zipPath, path, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  			
	 //_________________________________________end________________________________________________________________________	
		
	//下载文件_____________________________________________________________________________________________________
			
			 File file2 = new File(zipPath);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders header = new HttpHeaders();
		     header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     header.setContentDispositionFormData("attachment", dfileName);
		    return new  ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), header, HttpStatus.CREATED);
						
	 //_________________________________________end________________________________________________________________________	
				
	    }
	   
}
