package com.java.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Subjectname;
import com.java.entity.Syargu;
import com.java.entity.TrainInfo;
import com.java.entity.User;
import com.java.service.SubjectnameService;
import com.java.service.SyarguService;
import com.java.service.TeacherService;
import com.java.service.TrainInfoService;
import com.java.util.ResponseUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/subjectname")
public class SubjectnameController {	
	@Resource
	private SubjectnameService subjectnameService;
	
	@Resource
	private TeacherService teacherService;
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource 
	private SyarguService syarguService;
	
	//得到全部
	@RequestMapping("/get")
	public String list( Subjectname subjectname,HttpServletResponse res,HttpServletRequest req) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		List<Subjectname> list= subjectnameService.findSubjectname(null);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(res, result);
		return null;
	}
	//根据ID和学生sno得到实训任务书内容
	@RequestMapping("/getById")
	public String getById(HttpServletResponse res,HttpServletRequest req) throws Exception{
		User user=(User)req.getSession().getAttribute("user");
		String role=user.getRole();
		int id = 0;
		if(role.equals("学生")){
			String sno =user.getUsers();
			//获取当前周
			Syargu syargu=new Syargu();
			syargu.setArguname("weekdate");
			String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
			WeekNumUtil w=new WeekNumUtil();
			int weeknum=w.getWeeknum(weekdate);
			if(weeknum==0) weeknum=1; 
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("weeknum", weeknum);
			map.put("sno", sno);
			List<TrainInfo> list=trainInfoService.findTrainInfo(map);
			id=list.get(0).getSubjectid();
		}else{
			id=Integer.parseInt(req.getParameter("id"));
		}
		
		Subjectname subjectname=new Subjectname();
		subjectname.setId(id);
		Subjectname sub=subjectnameService.findSubjectname(subjectname).get(0);
		JSONObject result=new JSONObject();
		result.put("errmsg", 1);
		result.put("subject", sub);
		result.put("role", role);
		ResponseUtil.write(res, result);
		return null;
	}
	//根据ID 得到实训任务书内容
	@RequestMapping("/gets")
	public String gets(HttpServletResponse res,HttpServletRequest req) throws Exception{
		String id=req.getParameter("id");
		JSONObject result=new JSONObject();
		Subjectname subjectname=subjectnameService.findById(Integer.parseInt(id));
		result.put("result", subjectname);
		ResponseUtil.write(res, result);
		return null;
	}
	//添加
	@RequestMapping("/save")
	public String save(HttpServletResponse res,Subjectname subjectname,HttpServletRequest req) throws Exception{
		User user=(User)req.getSession().getAttribute("user");
		subjectname.setTno(user.getUsers());
		subjectnameService.insertSubjectname(subjectname);
		List<Subjectname> list= subjectnameService.findSubjectname(null);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("errmsg", 1);
		ResponseUtil.write(res, result);
		return null;
	}
	
	//修改
	@RequestMapping("/update")
	public String update(HttpServletResponse res,Subjectname subjectname) throws Exception{
		JSONObject jsonObject = new JSONObject();
		try{
			subjectnameService.updateSubjectname(subjectname);
		}catch (Exception e) {
			jsonObject.put("errmsg", "0");
		}
    	List<Subjectname> list= subjectnameService.findSubjectname(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		jsonObject.put("result", jsonArray);
		ResponseUtil.write(res, jsonObject);
		return null;
	}
	//题目指导教师可以为空
		@RequestMapping("/update2")
		public String update2(HttpServletResponse res,Subjectname subjectname) throws Exception{
			if(subjectname.getTno().equals("undefined")){
				subjectname.setTno(null);
			}
			JSONObject jsonObject = new JSONObject();
			try{
				subjectnameService.updateSubjectname2(subjectname);
			}catch (Exception e) {
				jsonObject.put("errmsg", "0");
			}
	    	List<Subjectname> list= subjectnameService.findSubjectname(null);
			JSONArray jsonArray=JSONArray.fromObject(list);
			jsonObject.put("result", jsonArray);
			ResponseUtil.write(res, jsonObject);
			return null;
		}
	//删除
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="id",required=false) int id,  HttpServletResponse res,HttpServletRequest req) throws Exception{
		JSONObject result=new JSONObject();
		if(id==1){//默认题目禁止删除
			result.put("errmsg", "0");
		}else{
			try{
				subjectnameService.deleteSubjectname(id);
				result.put("errmsg", "1");
			}catch (Exception e) {
				result.put("errmsg", "0");
			}
		}
		List<Subjectname> list=subjectnameService.findSubjectname(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(res, result);
		return null;
	}
    //得到实训题目下的学生的信息
	@RequestMapping("/getBySubject")
	public String getBySubject( @RequestParam(value="id",required=false) int id,  HttpServletResponse res,HttpServletRequest req) throws Exception{
		JSONObject result=new JSONObject();
		Map<String,Object> map=new HashMap<String,Object>();
		//获取当前周
		Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum= w.getWeeknum(weekdate);
		if(weeknum==0) weeknum=1;
		map.put("weeknum", weeknum);
		map.put("subjectid", id);
		List<TrainInfo> list=trainInfoService.findTrainInfo(map);
		int num=list.size();
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("num", num);
		ResponseUtil.write(res, result);
		return null;
	}
	//得到老师下的实训题目
	@RequestMapping("/getByTea")
	public String getByTea(HttpServletResponse res,HttpServletRequest req) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		User user=(User)req.getSession().getAttribute("user");
		String tno=user.getUsers();
		Subjectname subject=new Subjectname();
		subject.setTno(tno);
		List<Subjectname> list= subjectnameService.findSubjectname(subject);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(res, result);
		return null;
	}
	//填写实训任务书
	@RequestMapping("/write")
	public String write(HttpServletResponse res,Subjectname subjectname) throws Exception{
		JSONObject jsonObject = new JSONObject();
		 
		try{
			subjectnameService.updateSubjectname(subjectname);
			jsonObject.put("errmsg", "1");
		}catch (Exception e) {
			jsonObject.put("errmsg", "0");
		}
		ResponseUtil.write(res, jsonObject);
		return null;
	}
	
 }
   