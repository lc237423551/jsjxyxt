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

import com.java.entity.Practiceappli;
import com.java.entity.ShixunInfo;
import com.java.entity.Syargu;
import com.java.entity.Teacher;
import com.java.entity.TrainInfo;
import com.java.entity.User;
import com.java.service.PracticeappliService;
import com.java.service.ShixunInfoService;
import com.java.service.SyarguService;
import com.java.service.TeacherService;
import com.java.service.TrainInfoService;
import com.java.service.WeekworkService;
import com.java.util.ResponseUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/shixunInfo")
public class ShixunInfoController {	
	@Resource
	private ShixunInfoService shixunInfoService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private WeekworkService weekworkService;
	
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource 
	private SyarguService syarguService;
	
	@Resource
	private PracticeappliService practiceappliService;
	
    @RequestMapping("/get")
    public String getByCno(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<ShixunInfo> list=new ArrayList<ShixunInfo>();
    	JSONObject result=new JSONObject();
    	list=shixunInfoService.findShixunInfo(null);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }  
 
   
    @RequestMapping("/getBySession")//1.根据教师的姓名得到所指导的学生2.根据学生的学号得到实训信息
    public String getBySno(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	User user=(User) request.getSession().getAttribute("user");
    	String role=user.getRole();
    	if(role.equals("学生")){
    		String sno=user.getUsers();
    		map.put("sno", sno);
        	ShixunInfo shi=shixunInfoService.findShixunInfo(map).get(0);
    		result.put("result", shi);
    	}
//    	else{
//    		String tno=user.getUsers();
//    		Teacher t=new Teacher();
//    		t.setTno(tno);
//    		String tname=teacherService.findTeacher(t).get(0).getTname();
//    		List<ShixunInfo> list=new ArrayList<ShixunInfo>();
//    		map.put("tname", tname);
//    		list=shixunInfoService.findShixunInfo(map);
//    		//获得每个学生完成的周总结数
//    		for(int i=0;i<list.size();i++){
////    			list.get(i).setDealTime(list.get(i).getStudent().getCla().getCname());
//    			String sn=list.get(i).getSno();
//    			int nums=weekworkService.findCount(sn);
//    			list.get(i).setNums(nums);
//    			JSONArray jsonArray=JSONArray.fromObject(list);
//    			result.put("result", jsonArray);
//    		}
//    	}
    	
		ResponseUtil.write(response, result);
        return null;
    } 
    @RequestMapping("/updatesxpdfState")//修改实训报告的状态
    public String updatesxpdfState(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String state=request.getParameter("state");
    	String sno=request.getParameter("sno");
    	String time=request.getParameter("time");
    	ShixunInfo shi=new ShixunInfo();
    	shi.setSno(sno);
    	shi.setSxpdfState(state);
    	shi.setSxpdfTime(time);
    	shixunInfoService.updateShixunInfo(shi);    	
    	//操作完成后返回结果
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	
		//获取当前周
		Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum= w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;
		map.put("weeknum",weeknum);
		User user=(User) request.getSession().getAttribute("user");
    	String role=user.getRole();
    	if(role.equals("教师")){
    		map.put("tno",user.getUsers());
    	}
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
 
 }
   