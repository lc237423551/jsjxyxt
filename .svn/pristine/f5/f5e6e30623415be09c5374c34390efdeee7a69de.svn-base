package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.History;
import com.java.entity.Teacher;
import com.java.entity.User;
import com.java.service.HistoryService;
import com.java.service.TeacherService;
import com.java.util.MD5Utils;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value="/history")
public class HistoryController {	
	@Resource
	private HistoryService historyService;
    @RequestMapping("/get")
    public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<History> list=new ArrayList<History>();
    	History history=new History();
    	list=historyService.findHistory(history);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/search")
    public String search(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String sname=request.getParameter("sname");
    	if(sname.equals("undefined")) sname="";
    	String year=request.getParameter("year");
    	if(year.equals("全部")||year.equals("undefined")) year="";
    	List<History> list=new ArrayList<History>();
    	History history=new History();
    	history.setSname(sname);
    	history.setYear(year);
    	list=historyService.findHistory(history);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/reset")
    public String reset(HttpServletResponse response) throws IOException{
    	historyService.updateHistory();
    	JSONObject result=new JSONObject();
		result.put("result", null);
		ResponseUtil.write(response, result);
		return null;
    }
   
    
 }