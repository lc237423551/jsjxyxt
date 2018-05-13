package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Teacher;
import com.java.entity.User;
import com.java.service.TeacherService;
import com.java.service.UserService;
import com.java.util.MD5Utils;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {	
	@Resource
	private TeacherService teacherService;

	@Resource
	private UserService userService;
	@RequestMapping("/getTeacherInfo")
	public String getTeacherInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User)request.getSession().getAttribute("user");
		String tno=user.getUsers();
		Teacher t=new Teacher();
		t.setTno(tno);
		List<Teacher> list=teacherService.findTeacher(t);
		t=list.get(0);
		JSONObject result=new JSONObject();
		result.put("result", t);
		ResponseUtil.write(response, result);
		return null;
	}
	
	 @RequestMapping("/updateByTeacher")
	    public String updateByTeacher(HttpServletResponse response,HttpServletRequest request) throws IOException{
	    	String tno=request.getParameter("tno");
	    	String tsex=request.getParameter("tsex");
	    	String tpost=request.getParameter("tpost");
	    	String tdegree=request.getParameter("tdegree");
	    	String tstudy=request.getParameter("tstudy");
	    	String temail=request.getParameter("temail");
	    	String tdept=request.getParameter("tdept");
	    	String tphone=request.getParameter("tphone");
	    	String tremark=request.getParameter("tremark");
	    	Teacher teacher =new Teacher();
	    	teacher.setTno(tno);teacher.setTsex(tsex);teacher.setTpost(tpost);
	    	teacher.setTdept(tdept);teacher.setTdegree(tdegree);teacher.setTstudy(tstudy);
	    	teacher.setTphone(tphone);teacher.setTremark(tremark);teacher.setTemail(temail);
	    	teacherService.updateTeacher(teacher);
	        return null;
	    }
	

    @RequestMapping("/get")
    public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Teacher> list=new ArrayList<Teacher>();
    	Teacher teacher=new Teacher();
    	list=teacherService.findTeacher(teacher);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    
    @RequestMapping("/getwithall")
    public String getwithall(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Teacher> list=new ArrayList<Teacher>();
    	list=teacherService.findTeacher(null);
    	Teacher teacher=new Teacher();
    	teacher.setTname("全部");
    	list.add(0, teacher);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
//    @RequestMapping("/getwithnull")
//    public String getwithnull(HttpServletResponse response,HttpServletRequest request) throws IOException{
//    	List<Teacher> list=new ArrayList<Teacher>();
//    	list=teacherService.findTeacher(null);
//    	JSONObject result=new JSONObject();
//    	JSONArray jsonArray=JSONArray.fromObject(list);
//		result.put("result", jsonArray);
//		ResponseUtil.write(response, result);
//        return null;
//    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String tno=request.getParameter("tno");
    	JSONObject result=new JSONObject();
    	try{
    		teacherService.deleteTeacher(tno);
    		userService.deleteUser(tno);
			result.put("errmsg", "1");
		}catch (Exception e) {
			result.put("errmsg", "0");
		}
    	
    	List<Teacher> list=new ArrayList<Teacher>();
    	list=teacherService.findTeacher(null);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
//    
    @RequestMapping("/update")
    public String update(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String tno=request.getParameter("tno");
    	String tname=request.getParameter("tname");
    	String tdept=request.getParameter("tdept");
    	String tphone=request.getParameter("tphone");
    	Teacher teacher =new Teacher();
    	teacher.setTno(tno);
    	teacher.setTname(tname);
    	teacher.setTdept(tdept);
    	teacher.setTphone(tphone);
    	teacherService.updateTeacher(teacher);
        return null;
    }
    
    @RequestMapping("/search")
    public String search(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String search =request.getParameter("search");
    	JSONObject result=new JSONObject();

    	Pattern pattern=Pattern.compile("[0-9]*");
    	Matcher isNum = pattern.matcher(search);
    	List<Teacher> list=new ArrayList<Teacher>();
    	if(isNum.matches()){//按照教职工号查
    		Teacher teacher=new Teacher();
    		teacher.setTno(search);
    		list=teacherService.findTeacher(teacher);
    	}else{//按照教师姓名查
    		Teacher teacher=new Teacher();
    		teacher.setTname(search);
    		list=teacherService.findTeacher(teacher); 
    	}
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("line", list.size());
		ResponseUtil.write(response, result);
    	return null;
    }
    
	    @RequestMapping("/insert")
	    public String insert(HttpServletResponse response,HttpServletRequest request) throws IOException{
	    	JSONObject result=new JSONObject();
	    	String tno =request.getParameter("tno");
	    	String tname =request.getParameter("tname");
	    	String tsex =request.getParameter("tsex");
	    	String tdept =request.getParameter("tdept");
	    	String tpost =request.getParameter("tpost");
	    	String tdegree =request.getParameter("tdegree");
	    	Teacher teacher =new Teacher();
	    	teacher.setTno(tno);
	    	teacher.setTname(tname);
	    	teacher.setTsex(tsex);
	    	teacher.setTdept(tdept);
	    	teacher.setTpost(tpost);
	    	teacher.setTdegree(tdegree);
	    	try{
	    		teacherService.insertTeacher(teacher);
				result.put("errmsg", "1");
			}catch (Exception e) {
				result.put("errmsg", "0");
			}
	    	//添加用户
	    	result.put("newsInsert", tno);
	    	//插入成功后 获取全部的教师
	    	List<Teacher> list=new ArrayList<Teacher>();
	    	list=teacherService.findTeacher(null);
	    	JSONArray jsonArray=JSONArray.fromObject(list);
			result.put("result", jsonArray);
			ResponseUtil.write(response, result);
	        return null;
	}
    
 }