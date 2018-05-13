package com.java.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.History;
import com.java.entity.Syargu;
import com.java.entity.TrainInfo;
import com.java.service.HistoryService;
import com.java.service.SyarguService;
import com.java.service.TrainInfoService;
import com.java.util.FileUtil;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/syargu")
public class SyarguController {	
	@Resource
	private SyarguService syarguService;
	
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource
	private HistoryService historyService;
	
    @RequestMapping("/get")
    public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Syargu> list=new ArrayList<Syargu>();
    	JSONObject result=new JSONObject();
    	Syargu syargu=new Syargu();
    	list=syarguService.findSyargu(syargu);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    } 
    @RequestMapping("/update")
    public String update(Syargu syargu ,HttpServletResponse response,HttpServletRequest request) throws IOException{
    	syarguService.updateSyargu(syargu);
    	return null;
    }
    
    
    	//重置系统
    @RequestMapping("/reset")
    public String reset(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	JSONObject result=new JSONObject();
    	Syargu syargu=new Syargu();
    	syargu.setArguname("init");
    	String init=syarguService.findSyargu(syargu).get(0).getArguvalue();
    	System.out.println(init);
    if(init.equals("0")){
    	Calendar now = Calendar.getInstance(); 
   	    int year=now.get(Calendar.YEAR);
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("weeknum", 18);
    	List<TrainInfo> list=trainInfoService.findTrainInfo(map);
    	List<History> hlist=new ArrayList<History>();
    	if(list.size()>0){
	    	for(int i=0;i<list.size();i++){
	    		History h=new History();
	    		h.setSno(list.get(i).getStudent().getSno());
	    		h.setSname(list.get(i).getStudent().getSname());
	    		h.setCname(list.get(i).getStudent().getCla().getCname());
	    		h.setSpec(list.get(i).getStudent().getCla().getSpeciality().getSpecname());
	    		h.setSubjectname(list.get(i).getSubjectname().getName());
	    		h.setTuname(list.get(i).getUnit().getUname());
	    		h.setYear(year+"");
	    		hlist.add(h);
	    	}
	    	historyService.insertAll(hlist);
    	}
    	syarguService.deleteAll();
    	//删除上交文件
    	String rootpath="d://msFile/upload";
    	File file=new File(rootpath);
    	FileUtil fu=new FileUtil();
    	fu.deleteAllFilesOfDir(file);
    	syargu.setArguvalue("1");
    	syarguService.updateSyargu(syargu);
    	result.put("result", 0);
    	}else{
    		result.put("result", 1);
    	}
    	ResponseUtil.write(response, result);
    	return null;
    }
//    
    @RequestMapping("/reset1")
    public String reset1(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String weekdate=request.getParameter("weekdate");
    	String weekno=request.getParameter("weekno");
    	String enddate=request.getParameter("enddate");
    	Syargu sy=new Syargu();
    	sy.setArguname("weekdate");
    	sy.setArguvalue(weekdate);
    	syarguService.updateSyargu(sy);
    	sy.setArguname("startweekno");
    	sy.setArguvalue(weekno);
    	syarguService.updateSyargu(sy);
    	sy.setArguname("enddate");
    	sy.setArguvalue(enddate);
    	syarguService.updateSyargu(sy);
    	return null;
    }
    //得到教学周开始日期和请假结束日期
    @RequestMapping("/getTime")
    public String getTime(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Syargu> list=new ArrayList<Syargu>();
    	JSONObject result=new JSONObject();
    	Syargu syargu=new Syargu();
    	list=syarguService.findSyargu(syargu);
		result.put("start",list.get(0));
		result.put("end",list.get(5));
		ResponseUtil.write(response, result);
        return null;
    } 
    
    
    
    
    }
 