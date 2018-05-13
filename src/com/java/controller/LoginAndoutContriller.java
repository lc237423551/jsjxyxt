package com.java.controller;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.entity.Cla;
import com.java.entity.Speciality;
import com.java.entity.User;
import com.java.service.ClaService;
import com.java.service.SpecialityService;
import com.java.service.SyarguService;
import com.java.service.TeacherService;
import com.java.service.UserService;
import com.java.util.MD5Utils;
import com.java.util.ResponseUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/log")
public class LoginAndoutContriller {
	//登录验证
	
	@Resource
	private UserService userServcie;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private ClaService claService;
	
	@Resource
	private SpecialityService specialityService;
	
	@Resource 
	private SyarguService syarguService;
	
    @RequestMapping("/login")
    public String login(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	
    	JSONObject result=new JSONObject();
    	String users = request.getParameter("name");
	    String pass= request.getParameter("password");
	    String check = request.getParameter("code");
	    String role = request.getParameter("role");
	    String checked = (String)request.getSession().getAttribute("checked");
	    MD5Utils md5=new MD5Utils();    	    
	    String password=md5.getMD5(pass);
	   
	    User user=new User();
	    if ((check == null) || (!checked.equals(check))){//判断验证码
	    	result.put("result", "1");//验证码不正确
	    }else{
	    	user.setUsers(users);
	    	if(userServcie.findUser(user).isEmpty()){
	    		result.put("result", "6");//用户不存在
	    	}else{
	    		user.setPassword(password);
	    		user.setRole(role);
	    		if(userServcie.findUser2(user)==null){
	    			result.put("result", "5");//账号或者密码错误
	    		}else{
	    			result.put("result", "2");
	    				//更新登陆次数 以及最后一次登录时间
	    				Date lastDate=new Date();
	    				User newuser= userServcie.findUser2(user);
	    				newuser.setLoginnum(newuser.getLoginnum()+1);
	    				newuser.setLastlogintime(lastDate);
	    				userServcie.updateUser(newuser);
	    				user.setLastlogintime(lastDate);
	    				user.setLoginnum(newuser.getLoginnum());
//	    			    request.getSession().invalidate();
	    			    WeekNumUtil wu=new WeekNumUtil();
	    				String weekdate=syarguService.findSyargu(null).get(0).getArguvalue();
	    			    int weeknum=wu.getWeeknum(weekdate);
	    			    request.getSession().setAttribute("weeknum", weeknum);
	    			    request.getSession().setAttribute("user", newuser);
	    			if (role.equals("学生")) {
	    	        	 request.getSession().setAttribute("role","学生");
	    	        	 
	    	        }
	    	        else if (role.equals("教师")) { 	
//	    	        	System.out.println("教师");
	    	            int n=0;
	    	            Map<String,Object> map=new HashMap<String,Object>();
	    	            map.put("tno", users);
	    	        	if(!claService.findByTno(users).isEmpty()){//班级负责人
	    	        		n+=1;
	    	        	}
	    	        	Speciality speciality=new Speciality();
	    	        	speciality.setTno(users);
	    	        	if(!specialityService.findSpeciality(speciality).isEmpty()){//专业负责人
	    	        		n+=2;
	    	        	}
	    	           if(n==1){
	    	        		request.getSession().setAttribute("role","班主任");
	    	        		Cla cla=claService.findByTno(users).get(0);
	    	        		request.getSession().setAttribute("cla", cla);
	    	        	}else if(n==2){
	    	        		request.getSession().setAttribute("role","专业负责人");
	    	        	}else if(n==3){
	    	        		request.getSession().setAttribute("role","专业班级负责人");
	    	        		Cla cla=claService.findByTno(users).get(0);
	    	        		request.getSession().setAttribute("cla", cla);
	    	        	}else{
	  	    	        			request.getSession().setAttribute("role","教师");
	    	        			
	    	        	}
	    	        	     
	    	        }  else if (role.equals("管理员")) {
	    	        	 
	    	        	request.getSession().setAttribute("role","管理员");
	    	        }else if (role.equals("辅导员")) {
	    	        	 
	    	        	request.getSession().setAttribute("role","辅导员");
	    	        }
	    		}
	    	}
	    	
	    }
	    ResponseUtil.write(response, result);
   	
       return null;
    }
   //退出
    @RequestMapping("/logout")
    public void logout(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	request.getSession().invalidate();
    	response.sendRedirect(request.getContextPath() + "/");        
    }
}