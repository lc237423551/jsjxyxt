package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Syargu;
import com.java.entity.User;
import com.java.entity.Weekwork;
import com.java.service.SyarguService;
import com.java.service.WeekworkService;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import com.java.util.TimeUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/weekwork")
public class WeekworkController {	
	@Resource
	private WeekworkService weekworkService;
	
	@Resource 
	private SyarguService syarguService;
	
    @RequestMapping("/getWeekwork")
    public String getWeek(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String weeknum=request.getParameter("week");
    	String sno=null;
    	User user=(User) request.getSession().getAttribute("user"); 
    	String role=user.getRole();
    	if(role.equals("学生")){
    		sno=user.getUsers();
    	}else{
    		sno=request.getParameter("sno");
    	}
    	Weekwork weekwork=new Weekwork();
    	weekwork.setSno(sno);weekwork.setWeeknum(Integer.parseInt(weeknum) );
    	List<Weekwork> list=new ArrayList<Weekwork>();
    	JSONObject result=new JSONObject();
    	list=weekworkService.findWeekwork(weekwork);
    	weekwork=list.get(0);
		result.put("result", weekwork);
		//-------获取可编辑的周数
		Syargu syargu=new Syargu();
		WeekNumUtil wu=new WeekNumUtil();
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		String week=syarguService.findSyargu(syargu).get(1).getArguvalue();
		if(week.equals("0")){
			week=wu.getWeeknums(weekdate);
		}
		String[] arr=week.split("&");
		result.put("weeknums", arr);
		ResponseUtil.write(response, result);
        return null;
    }  
   //获得可修改的周数
    @RequestMapping("/getweeknum")
    public String getweeknum(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	JSONObject result=new JSONObject();
		//-------获取可编辑的周数
		Syargu syargu=new Syargu();
		WeekNumUtil wu=new WeekNumUtil();
		List<Syargu> list=syarguService.findSyargu(syargu);
		String weekdate=list.get(0).getArguvalue();
		String week=list.get(1).getArguvalue();
		if(week.equals("0")){
			week=wu.getWeeknums(weekdate);
		}
		int tab=0;
		int startweekno=Integer.parseInt(list.get(4).getArguvalue()) ;
		int weeknum=wu.getWeeknum(weekdate);//当前周次
		String[] arr=week.split("&");
		if(startweekno<=weeknum){
			 tab=1;
		}else{
			 tab=0;
		}
		result.put("weeknums", arr);
		result.put("tab", tab);
		ResponseUtil.write(response, result);
        return null;
    }
    //保存周总结
    @RequestMapping("/update")
    public String update(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	  String weeknum=request.getParameter("week");
		  String summary=request.getParameter("summary");
		  String advise=request.getParameter("advise");
		  String arrange=request.getParameter("arrange");
		  String deal=request.getParameter("deal");
		  String evaluation=request.getParameter("evaluation");
		  Weekwork w=new Weekwork (Integer.parseInt(weeknum),   summary,   advise,   arrange,   deal, evaluation);
		  //获取当前时间
		  TimeUtil tu=new TimeUtil();
		  String time=tu.getNow();
		    String sno=null;
	    	User user=(User) request.getSession().getAttribute("user"); 
	    	String role=user.getRole();
	    	if(role.equals("学生")){
	    		sno=user.getUsers();
	    		w.setWtime(time);
	    		w.setTab("1");
	    	}else{
	    		sno=request.getParameter("sno");
	    	}
	    	w.setSno(sno);
    	   JSONObject result=new JSONObject();
    	//-------获取可编辑的周数
    			Syargu syargu=new Syargu();
    			WeekNumUtil wu=new WeekNumUtil();
    			String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
    			String week=syarguService.findSyargu(syargu).get(1).getArguvalue();
    			if(week.equals("0")){
    				week=wu.getWeeknums(weekdate);
    			}
    			String[] arr=week.split("&");
    	
    			StringUtil su=new StringUtil();
    			if(su.isExist(arr, weeknum)){
    				weekworkService.updateWeekwork(w);
    				result.put("result", "1");
    				result.put("time", time);
    			}else{
    				result.put("result", "0");
    			}
    			
    			ResponseUtil.write(response, result);
    			return null;
    }
 }