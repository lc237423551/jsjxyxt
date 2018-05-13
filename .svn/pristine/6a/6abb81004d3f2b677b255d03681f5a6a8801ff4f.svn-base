package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Cla;
import com.java.entity.Speciality;
import com.java.entity.Student;
import com.java.entity.Teacher;
import com.java.entity.User;
import com.java.service.ClaService;
import com.java.service.SpecialityService;
import com.java.service.StudentService;
import com.java.service.TeacherService;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value="/cla")
public class ClaController {	
	@Resource
	private ClaService claService;
	
	@Resource
	private SpecialityService specialityService;
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
    @RequestMapping("/getClaByTno")//根据登录的老师如果是专业负责人 得到该专业的所有班级
    public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Cla> list=new ArrayList<Cla>();
    	JSONObject result=new JSONObject();
    	User user=(User)request.getSession().getAttribute("user");
    	String tno=user.getUsers();
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("tno", tno);
    	list=claService.findCla(map);
    	Cla cla =new Cla();
    	cla.setCno("");
    	cla.setCname("全部");
    	list.add(0, cla);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }   
    
    @RequestMapping("/get")
    public String getCla(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String specid=request.getParameter("specid");
    	List<Cla> list=new ArrayList<Cla>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("specid", specid);
    	list=claService.findCla(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/getwithAll")
    public String getwithAll(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String specid=request.getParameter("specid");
    	List<Cla> list=new ArrayList<Cla>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("specid", specid);
    	list=claService.findCla(map);
    	Cla cla =new Cla();
    	cla.setCno("");
    	cla.setCname("全部");
    	list.add(0, cla);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/getBySpecname")
    public String getBySpecname(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String specname=request.getParameter("specname");
    	Speciality speciality=new Speciality();
    	speciality.setSpecname(specname);
    	String specid=specialityService.findSpeciality(speciality).get(0).getSpecid();
    	List<Cla> list=new ArrayList<Cla>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("specid", specid);
    	list=claService.findCla(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/addCla")
    public void addCla(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String specid=request.getParameter("specid");
    	String tno=request.getParameter("tno");
    	String cname=request.getParameter("cname");	
    	Cla cla=new Cla();
    	cla.setCname(cname);
    	cla.setCno(cname);
    	cla.setSpecid(specid);
    	cla.setTno(tno);
    	JSONObject result=new JSONObject();
    	try{
    		claService.addCla(cla);
			result.put("errmsg", "1");
		}catch (Exception e) {
			result.put("errmsg", "0");
		}
    	List<Cla> list=new ArrayList<Cla>();
    	Map<String,Object> map=new HashMap<String,Object>();
    	list=claService.findCla(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
    	result.put("result", jsonArray);
		ResponseUtil.write(response, result);
    	
    }
    @RequestMapping("/deleteCla")
    public void deleteCla(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String cno=request.getParameter("cno");
    	Cla cla=new Cla();
    	JSONObject result=new JSONObject();
    	
    	List<Cla> list=new ArrayList();
    	Map<String,Object> map=new HashMap<String,Object>();
    	Student stu=new Student();
		stu.setCno(cno);
		List<Student> stulist=new ArrayList();
		stulist=studentService.findStudent(stu);
    	if(stulist.isEmpty()){
    		cla.setCno(cno);
    		claService.deleteCla(cla);
    		list=claService.findCla(map);
    		JSONArray jsonArray=JSONArray.fromObject(list);
    		result.put("result", jsonArray);
    		
    	}else{
    		result.put("result", "0");
    	}
    	ResponseUtil.write(response, result);
    	
    }
    @RequestMapping("/updateCla")
    public void updateCla(HttpServletResponse response,HttpServletRequest request, Cla cla) throws IOException{
 		JSONObject result=new JSONObject();
 	     try{
 	    	claService.update(cla);
 	     }catch(Exception e){
 	    	 
 	    	 result.put("errmsg", "0");
 	     }
    	List<Cla> list=new ArrayList();
    	Map<String,Object> map=new HashMap<String,Object>();
    	list=claService.findCla(map);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
    }
    //根据指导教师所带的学生得到班级列表
    @RequestMapping("/get1")
    public String getCla1(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	User user=(User)request.getSession().getAttribute("user");
    	List<Cla> list=new ArrayList<Cla>();
    	JSONObject result=new JSONObject();
    	list=claService.findCla1(user.getUsers());
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    
 }