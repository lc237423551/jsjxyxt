package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Syargu;
import com.java.entity.Teacher;
import com.java.entity.TrainInfo;
import com.java.entity.Unit;
import com.java.service.SyarguService;
import com.java.service.TeacherService;
import com.java.service.UnitService;
import com.java.util.ResponseUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/unit")
public class UnitController {	
	@Resource
	private UnitService unitService;
	
	@Resource
	private SyarguService syarguService;
	
	@Resource
	private TeacherService teacherService;
	
  @RequestMapping("/getCity")
  public String getCity(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
	  	JSONObject result=new JSONObject();
	  	Unit unit=new Unit();
	  	unit.setUcity("全部");
	  	list=unitService.findAllcity();
	  	list.add(0, unit);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  @RequestMapping("/getName")
  public String getName(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
	  	JSONObject result=new JSONObject();
		Unit unit=new Unit();
		unit.setUname("全部");
	  	list=unitService.findAllname();
	  	list.add(0, unit);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  @RequestMapping("/get")
  public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
	  	String uname=request.getParameter("uname");
	  	JSONObject result=new JSONObject();
	  	Unit u=new Unit();
	  	u.setUname(uname);
	  	
	  	list=unitService.findUnit(u);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  @RequestMapping("/getshenHe")
  public String getUnitShenHe(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
	  	String uname=request.getParameter("uname");
	  	JSONObject result=new JSONObject();
	  	Unit u=new Unit();
	  	u.setUname(uname);
	  	u.setUstatus("已审核");
	  	list=unitService.findUnit(u);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  @RequestMapping("/get1")
  public String get1(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>(); 
	  	String uname=request.getParameter("uname");
	  	if(uname.equals("undefined")||uname.equals("全部")) uname="";
	  	String ucity=request.getParameter("ucity");
	  	if(ucity.equals("undefined")||ucity.equals("全部")) ucity="";
	  	Unit u=new Unit();
	  	u.setUcity(ucity);u.setUname(uname);
	  	JSONObject result=new JSONObject();
	  	list=unitService.findUnit(u);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  //添加
  @RequestMapping("/insert")
  public String insert(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	Unit u=new Unit();
	  	String uname=request.getParameter("uname");u.setUname(uname);
	  	String ucity=request.getParameter("ucity");u.setUcity(ucity);
	  	String uadress=request.getParameter("uadress");u.setUadress(uadress);
	  	String uphone=request.getParameter("uphone");u.setUphone(uphone);
	  	String upeople=request.getParameter("upeople");u.setUpeople(upeople);
	  	u.setUstatus("已审核");
	  	unitService.insertUnit(u);
	  	List<Unit> list=new ArrayList<Unit>();
	  	JSONObject result=new JSONObject();
	  	list=unitService.findUnit(null);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
	  	result.put("errmsg", 1);
	  	result.put("newsInsert", uname);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  //删除
  @RequestMapping("/delete")
  public String delete(HttpServletResponse response,HttpServletRequest request) throws IOException{
		JSONObject result=new JSONObject();
	  	String uid=request.getParameter("uid");
	  	if(uid.equals("1")){//默认单位禁止删除
			result.put("errmsg", "0");
		}else{
			try{
	    		unitService.deleteUnit(Integer.valueOf(uid));
				result.put("errmsg", "1");
			}catch (Exception e) {
				result.put("errmsg", "0");
			}
		}
	  	
	  	List<Unit> list=new ArrayList<Unit>();
	  	list=unitService.findUnit(null);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
//修改单位信息
  @RequestMapping("/update")
  public String update(HttpServletResponse response,HttpServletRequest request,Unit unit) throws IOException{
	  JSONObject result=new JSONObject();
	  String tno=unit.getTno();
	  if(tno.equals("undefined")) {
		  tno=null;
		  unit.setTno(tno);
	  }
	  try{
			  unitService.updateUnit2(unit);
			  result.put("errmsg", "1");
		   
	  }catch (Exception e) {
		  result.put("errmsg", "0"); 
	  }
	  	List<Unit> list=unitService.findUnit(null);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  
  @RequestMapping("/getwithnum")
  public String getwithnum(HttpServletResponse response,HttpServletRequest request) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
		List<Unit> list1=new ArrayList<Unit>();
	  	String uname=request.getParameter("uname");
	  	if(uname.equals("undefined")||uname.equals("全部")) uname="";
	  	String ucity=request.getParameter("ucity");
	  	if(ucity.equals("undefined")||ucity.equals("全部")) ucity="";
	  	Unit u=new Unit();
	  	u.setUcity(ucity);
	  	u.setUname(uname);
	  	JSONObject result=new JSONObject();
	  	Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum =w.getWeeknum(weekdate);
		if(weeknum==0) weeknum=1;
	  	list=unitService.findUnit(u);
	  	for(int i=0;i<list.size();i++){
	  		Unit unit=list.get(i);
	  		int uid=list.get(i).getUid();
	  		TrainInfo train=new TrainInfo();
	  		train.setUid(uid);
	  		train.setWeeknum(weeknum);
	  		int num=unitService.findnum(train);
	  		unit.setUnums(num);
	  		if(num>0)
	  			list1.add(unit);
	  	}
	  	JSONArray jsonArray=JSONArray.fromObject(list1);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  //获取全部未审核的单位
  
  @RequestMapping("/getByStatus")
  public String getByStatus(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uname") String uname) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
	  	JSONObject result=new JSONObject();
	  	Unit u=new Unit();
	  	u.setUstatus("未审核");
	  	if(uname.equals("undefined"))uname=null;
	  	u.setUname(uname);
	  	System.out.println(uname);
	  	list=unitService.findUnit(u);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  
  //批量审核单位
  @RequestMapping("/updateStatus")
  public String updateStatus(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uids") String uids) throws IOException{
	  	List<Unit> list=new ArrayList<Unit>();
	  	JSONObject result=new JSONObject();
	  	String[] arr=uids.split(",");
	  	for(int i=0;i<arr.length;i++){
	  		Unit u=new Unit();
		  	u.setUstatus("已审核");
		  	u.setUid(Integer.parseInt(arr[i]) );
		  	unitService.updateUnit(u);
	  	}
	  	Unit unit=new Unit();
	  	unit.setUstatus("未审核");
	  	list=unitService.findUnit(unit);
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
      return null;
  }
  
  
  
  
  
 }