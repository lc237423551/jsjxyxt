package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.User;
import com.java.service.UserService;
import com.java.util.MD5Utils;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/user")
public class UserController {	
	@Resource
	private UserService userServcie;

	 
    @RequestMapping("/get")
    public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<User> list=new ArrayList<User>();
    	User userSession=(User)request.getSession().getAttribute("user");
    	list=userServcie.findUser1(userSession.getUsers());
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("users", userSession.getUsers());
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/update")
    public String update(HttpServletResponse response,HttpServletRequest request,User user) throws IOException{
    	userServcie.updateUser(user);
    	return null;
    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String users=request.getParameter("users");
    	userServcie.deleteUser(users);
    	JSONObject result=new JSONObject();
    	List<User> list=new ArrayList<User>();
     
    	User userSession=(User)request.getSession().getAttribute("user");
    	list=userServcie.findUser1(userSession.getUsers());
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    
    @RequestMapping("/resetPassword")//重置密码
    public String resetPassword(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String users=request.getParameter("users");
    	User user=new User();	 
    	MD5Utils md5=new MD5Utils();
    	String password=md5.getMD5("123456");
    	user.setPassword(password);
    	user.setUsers(users);
    	userServcie.updateUser(user);
        return null;
    }
    
    @RequestMapping("/insert")
    public String insert(HttpServletResponse response,HttpServletRequest request,User user) throws IOException{
    	JSONObject result=new JSONObject();
    	List<User> list=new ArrayList<User>();	 
    	MD5Utils md5=new MD5Utils();
    	String password=md5.getMD5("123456");
    	user.setPassword(password);
    	try{
    		userServcie.insertUser(user);
			result.put("errmsg", "1");
		}catch (Exception e) {
			result.put("errmsg", "0");
		}	 
    	User userSession=(User)request.getSession().getAttribute("user");
    	list=userServcie.findUser1(userSession.getUsers());
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("newsInsert", user.getUsers());
		ResponseUtil.write(response, result);
        return null;
    }
    
    //修改密码
    @RequestMapping("/updatePassword")//重置密码
    public String updatePassword(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	User user=(User) request.getSession().getAttribute("user");
    	String users=user.getUsers();
    	String password=user.getPassword();
    	MD5Utils md5=new MD5Utils();
    	String p1=request.getParameter("old");
    	String p2=request.getParameter("n2");
    	JSONObject result=new JSONObject();
    	if(!md5.getMD5(p1).equals(password)){
    		result.put("errmsg", 0);
    	}else{
    		String p=md5.getMD5(p2);
    		user.setPassword(p);
    		user.setUsers(users);
        	userServcie.updateUser(user);
        	result.put("errmsg", 1);
    	}
    	ResponseUtil.write(response, result);
        return null;
    }
    //辨别教师身份
    
    
    
    
    
}