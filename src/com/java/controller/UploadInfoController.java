package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.java.entity.Cla;
import com.java.entity.ShixunInfo;
import com.java.entity.Student;
import com.java.entity.Teacher;
import com.java.entity.TrainInfo;
import com.java.entity.Unit;
import com.java.entity.User;
import com.java.entity.Weekwork;
import com.java.service.ClaService;
import com.java.service.ShixunInfoService;
import com.java.service.StudentService;
import com.java.service.TeacherService;
import com.java.service.TrainInfoService;
import com.java.service.UnitService;
import com.java.service.UserService;
import com.java.service.WeekworkService;
import com.java.util.ReadExcelUtil;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller//上传文件
@RequestMapping(value="/uploadInfo")
public class UploadInfoController {
	@Resource
	private StudentService studentService;
	
	@Resource 
	private ClaService claService;
	
	@Resource
	private ShixunInfoService shixunInfoService;
	
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource
	private WeekworkService weekworkservice;
	
	@Resource
	private UserService userService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private UnitService unitService;
	    @RequestMapping(value="/student", method=RequestMethod.POST    )  
	    public String ngUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	String rootpath=request.getServletContext().getRealPath("/upload/");
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
	                    String fileName = "studentInfo"+".xlsx"; 
	                    String path = rootpath+"studnet"+"\\"+fileName;  
	                    File localFile = new File(path);  
	                    if(!localFile.getParentFile().exists()) {  
	                         localFile.getParentFile().mkdirs();  
	                     }  
	                    try {  
	                        file.transferTo(localFile);  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                        result.put("errmsg", "0");
	                    }finally{
	                    	System.out.println("上传成功！");
	                    	ReadExcelUtil ru=new ReadExcelUtil();
	                    	List<Student> slist=new ArrayList<Student>();
	                    	List list1 = ru.read2007Excel(localFile);
	                    	try{
//	                		List<Student> studentList=new ArrayList<Student>();
	                		for (int i = 1; i < list1.size(); i++) {
	                			Student student=new Student();
	                			List list2 = (List) list1.get(i);
	                			String sno=(String) list2.get(0);
	        					 student.setSno(sno);
	        					String sname=(String) list2.get(1);
	        					student.setSname(sname);
	        					String cname=(String) list2.get(2);
	        					Map<String,Object> map=new HashMap<String,Object>();
	        			    	map.put("cname", cname);
	        			    	Cla cla=claService.findCla(map).get(0);
	        					String cno=cla.getCno();
	        					student.setCno(cno);
	        					student.setCla(cla);
	        					slist.add(student);
	                		}
	                		
	                			studentService.insertAll(slist);
	                			result.put("errmsg", "1");
	                		}catch (Exception e) {
	                			List<Student> list=studentService.findStudent(null);
	                    		JSONArray jsonArray=JSONArray.fromObject(list);
	                    		result.put("result", jsonArray);
	                    		result.put("errmsg", "0");
	                    		ResponseUtil.write(response, result);
	                			
	                			return null;
	                		}
	                    	for(int n=0;n<slist.size();n++){
	                    		String sno=slist.get(n).getSno();
	                    		String sname=slist.get(n).getSname();
	                    		String tno=slist.get(n).getCla().getTeacher().getTno();
	                    		//2添加ShixunInfo
	                        	ShixunInfo shi=new ShixunInfo();
	                        	shi.setSno(sno);
	                        	shi.setSxpdfState("0");
	                        	shixunInfoService.insertShixunInfo(shi);
	                    		//3添加TrainInfo
	                        	List<TrainInfo> tlist=new ArrayList<TrainInfo>();
	                        	for(int j=1;j<=18;j++){
	                        		TrainInfo train=new TrainInfo();
	                            	train.setSno(sno);
	                            	train.setState("校内");
	                            	train.setUid(1);
	                            	train.setTno(tno);
	                            	train.setWeeknum(j);
	                            	train.setSubjectid(1);
	                            	tlist.add(train);
	                        	}
	                        	trainInfoService.insertTrainInfo(tlist);	
	                    		//4添加Weekwork
	                        	List<Weekwork> wlist=new ArrayList<Weekwork>();
	                        	for(int j=1;j<=18;j++){
	                        		Weekwork week=new Weekwork();
	                            	week.setSno(sno);
	                            	week.setTab("0");
	                        		week.setWeeknum(j);
	                        		wlist.add(week);
	                        	}
	                    		weekworkservice.insertWeekwork(wlist);
	                    		//5添加用户
	                    		User user=new User();
	                    		user.setUsers(sno);
	                    		user.setRole("学生");
	                    		user.setUsername(sname);
	                    		user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
	                    		userService.insertUser(user);
	                        	
	                    	}
	                    }
	                }  
	            }  
	          } 
	      //添加成功后
        	List<Student> list=studentService.findStudent(null);
    		JSONArray jsonArray=JSONArray.fromObject(list);
    		result.put("result", jsonArray);
    		ResponseUtil.write(response, result);
    		return null;
	    }  
	    
	    @RequestMapping(value="/teacher", method=RequestMethod.POST    )  
	    public String teacher(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	String rootpath=request.getServletContext().getRealPath("/upload/");
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
	                    String fileName = "teacherInfo"+".xlsx"; 
	                    String path = rootpath+"teacher"+"\\"+fileName;  
	                    File localFile = new File(path);  
	                    if(!localFile.getParentFile().exists()) {  
	                         localFile.getParentFile().mkdirs();  
	                     }  
	                    try {  
	                        file.transferTo(localFile);  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }finally{
	                    	System.out.println("上传成功！");
	                    	ReadExcelUtil ru=new ReadExcelUtil();
	                    	List<Teacher> slist=new ArrayList<Teacher>();
	                    	List list1 = ru.read2007Excel(localFile);
//	                		List<Student> studentList=new ArrayList<Student>();
	                		for (int i = 1; i < list1.size(); i++) {
	                			Teacher teacher=new Teacher();
	                			List list2 = (List) list1.get(i);
	                			String tno=(String) list2.get(0);
	        					 teacher.setTno(tno);
	        					String tname=(String) list2.get(1);
	        					 teacher.setTname(tname);
	        					slist.add(teacher);
	                		}
	                		try{
	                			teacherService.insertAll(slist);
	                			result.put("errmsg", "1");
	                		}catch (Exception e) {
	                			List<Teacher> list=teacherService.findTeacher(null);
	                    		JSONArray jsonArray=JSONArray.fromObject(list);
	                    		result.put("result", jsonArray);
	                    		result.put("errmsg", "0");
	                    		ResponseUtil.write(response, result);
	                			return null;
	                		}
	                    	for(int n=0;n<slist.size();n++){
	                    		String tno=slist.get(n).getTno();
	                    		String tname=slist.get(n).getTname();
	                    		//添加用户
	                    		User user=new User();
	                    		user.setUsers(tno);
	                    		user.setRole("教师");
	                    		user.setUsername(tname);
	                    		user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
	                    		userService.insertUser(user);
	                    	}
	                    }
	                }  
	            }  
	          } 
	      //添加成功后
	        List<Teacher> list=teacherService.findTeacher(null);
    		JSONArray jsonArray=JSONArray.fromObject(list);
    		result.put("result", jsonArray);
    		ResponseUtil.write(response, result);
    		return null;
	    } 
	    
	    @RequestMapping(value="/unit", method=RequestMethod.POST    )  
	    public String unit(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
	    	String rootpath=request.getServletContext().getRealPath("/upload/");
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
	                    String fileName = "unitInfo"+".xlsx"; 
	                    String path = rootpath+"unit"+"\\"+fileName;  
	                    File localFile = new File(path);  
	                    if(!localFile.getParentFile().exists()) {  
	                         localFile.getParentFile().mkdirs();  
	                     }  
	                    try {  
	                        file.transferTo(localFile);  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }finally{
	                    	System.out.println("上传成功！");
	                    	ReadExcelUtil ru=new ReadExcelUtil();
	                    	List<Unit> slist=new ArrayList<Unit>();
	                    	List list1 = ru.read2007Excel(localFile);
//	                		List<Student> studentList=new ArrayList<Student>();
	                		for (int i = 1; i < list1.size(); i++) {
	                			Unit unit=new Unit();
	                			List list2 = (List) list1.get(i);
	                			String uname=(String) list2.get(0);
	        					 unit.setUname(uname);
	        					String ucity=(String) list2.get(1);
	        					 unit.setUcity(ucity);
	        					String uadress=(String) list2.get(2);
	        					 unit.setUadress(uadress);
	        					 unit.setUstatus("未审核");
	        					 slist.add(unit);
	                		}
	                		try{
	                			unitService.insertAll(slist);
	                			result.put("errmsg", "1");
	                		}catch (Exception e) {
	                			List<Unit> list=unitService.findUnit(null);
	                    		JSONArray jsonArray=JSONArray.fromObject(list);
	                    		result.put("result", jsonArray);
	                    		result.put("errmsg", "0");
	                    		ResponseUtil.write(response, result);
	                			return null;
	                		}
	                    }
	                }  
	            }  
	          } 
	      //添加成功后
	        List<Unit> list=unitService.findUnit(null);
    		JSONArray jsonArray=JSONArray.fromObject(list);
    		result.put("result", jsonArray);
    		ResponseUtil.write(response, result);
    		return null;
	    }
}
