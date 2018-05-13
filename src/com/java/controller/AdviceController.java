package com.java.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.java.entity.Advice;
import com.java.entity.User;
import com.java.service.AdviceService;
import com.java.service.UnitService;
import com.java.service.UserService;
import com.java.service.VacateService;
import com.java.service.WeekworkService;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import com.java.util.TimeUtil;
import com.java.util.WordGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/advice")
public class AdviceController {	
	@Resource
	private AdviceService adviceService;
	
	@Resource
	private UserService userService;
	
	//添加一个公告
	@RequestMapping("/insert")
	public String insert(HttpServletResponse response,HttpServletRequest request) throws IOException{
		//获取当前时间
		TimeUtil tu=new TimeUtil();
		String time=tu.getNow1(); 
		String content=request.getParameter("content");
		User user=(User)request.getSession().getAttribute("user");
		String title=request.getParameter("title");
		String fileName1=time;
		String users=user.getUsers();
		int nums=0;//插入记录数
		//1添加基本信息
		Advice advice=new Advice();
		advice.setTitle(title);
		advice.setContent(content);
		advice.setUsers(users);
		advice.setFilename(fileName1);
		JSONObject result=new JSONObject();
    	try{
    		nums=adviceService.insertAdvice(advice);
    		if(nums>0){//添加成功插入文件
    			result.put("errmsg", "1");
    			//一级目录
    	    	String rootpath="d://msFile/upload/";
    	    	String seePath="d://msFile/upload/announcement/";
    	    	//二级目录
    	    	String path1=seePath+"\\";

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
    	                		String fileName = fileName1+".rar";
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
    		}	
		}catch (Exception e) {
			e.printStackTrace();
			result.put("errmsg", "0");
			List<Advice> list=adviceService.findAdvice(null);
			JSONArray jsonArray=JSONArray.fromObject(list);
			result.put("result", jsonArray);
			ResponseUtil.write(response, result);
			return null;
		}
    	List<Advice> list=adviceService.findAdvice(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/getAdviceInfo")
	public String getAdviceInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User)request.getSession().getAttribute("user");
		String users=user.getUsers();
		Advice advice=new Advice();
		advice.setUsers(users);
		List<Advice> list=adviceService.findAdvice(advice);
		advice=list.get(0);
		JSONObject result=new JSONObject();
		result.put("result", advice);
		ResponseUtil.write(response, result);
		return null;
	}
    //更新公告
	@RequestMapping("/update")
	public String update(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String advice_id=request.getParameter("advice_id");
		String content=request.getParameter("content");
		
		StringUtil su=new StringUtil();
		if(!su.isEmpty(content)){
			Advice advice=new Advice(Integer.parseInt(advice_id),content);
			adviceService.updateAdvice(advice);
		}
		return null;
	}
	
	@RequestMapping("/get")//得到所有公告的信息
	public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Advice advice=new Advice();
		List<Advice> list=adviceService.findAdvice(advice);
		JSONArray jsonArray=JSONArray.fromObject(list);
		JSONObject result=new JSONObject();
		result.put("result", jsonArray);
		result.put("nums", list.size());
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/delete")//删除公告
	public String delete(HttpServletResponse response,HttpServletRequest request) throws IOException {
		JSONObject result=new JSONObject();
		String advice_id=request.getParameter("advice_id");
		try{
			adviceService.deleteAdvice(advice_id);
			result.put("errmsg", "1");
		}catch (Exception e) {
			result.put("errmsg", "0");
		}
		List<Advice> list=adviceService.findAdvice(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
		return null;
	}
	
 }