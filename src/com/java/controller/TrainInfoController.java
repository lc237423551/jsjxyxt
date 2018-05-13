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
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Practiceappli;
import com.java.entity.ShixunInfo;
import com.java.entity.Speciality;
import com.java.entity.Subjectname;
import com.java.entity.Syargu;
import com.java.entity.Teacher;
import com.java.entity.TrainInfo;
import com.java.entity.User;
import com.java.service.PracticeappliService;
import com.java.service.ShixunInfoService;
import com.java.service.SpecialityService;
import com.java.service.SubjectnameService;
import com.java.service.SyarguService;
import com.java.service.TeacherService;
import com.java.service.TrainInfoService;
import com.java.service.WeekworkService;
import com.java.util.ResponseUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/trainInfo")
public class TrainInfoController {	
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource 
	private SyarguService syarguService;
	
	@Resource 
	private SpecialityService specialityService;
	
	@Resource
	private WeekworkService weekworkService;
	
	@Resource
	private PracticeappliService practiceappliService;
	
	@Resource
	private ShixunInfoService shixunInfoService;
	
	@Resource
	private TeacherService  teacherService;
	
	@Resource
	private SubjectnameService  subjectnameService;
	
	
	
    @RequestMapping("/get")//得到当前周学生的实训情况
    public String getByCno(HttpServletResponse response,HttpServletRequest request,
    		@RequestParam(value = "cno", required = false, defaultValue = "") String cno,
    		@RequestParam(value = "specid", required = false, defaultValue = "") String specid,
    		@RequestParam(value = "state", required = false, defaultValue = "") String state,
    		@RequestParam(value = "sname", required = false, defaultValue = "") String sname) throws IOException{
    	
    	
    	//如果是专业负责人或者是专业班级负责人
    	String role=(String)request.getSession().getAttribute("role");
    	if(role.equals("专业负责人")||role.equals("专业班级负责人")){
    		//根据专业负责人的专业
    		User user=(User) request.getSession().getAttribute("user");
        	String tno=user.getUsers();
        	Speciality speciality=new Speciality();
        	speciality.setTno(tno);
        	 specid=specialityService.findSpeciality(speciality).get(0).getSpecid();
    	}
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	if(state.equals("全部")) state="";
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("cno", cno);
		map.put("specid", specid);
		map.put("state", state);
		map.put("sname", sname);
		//获取当前周
		Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0){
			weeknum=1;//如果未到实训开始 返回第一周的
			result.put("tab", 0);
		}else{
			result.put("tab", 1);
		}
		map.put("weeknum", weeknum);
    	list=trainInfoService.findTrainInfo(map);
    	for(int i=0;i<list.size();i++){
    		String sno=list.get(i).getSno();
    		int nums=weekworkService.findCount(sno);
    		list.get(i).getShixunInfo().setNums(nums);
    	}
    	int nums=list.size();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("nums", nums);
		ResponseUtil.write(response, result);
        return null;
    }  
    @RequestMapping("/getBySno")//根据学号得到该学生的当前周的实训情况（附加给前台返回可编辑的）
    public String getBySno(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	JSONObject result=new JSONObject();
    	User user=(User)request.getSession().getAttribute("user");
    	String role=user.getRole();
    	String sno=null;
    	if(role.equals("学生")){
    		sno=user.getUsers();
    		System.out.println(sno);
    	}else{
    		sno=request.getParameter("sno");
    	}
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("sno", sno);
		//获取当前周
		Syargu syargu=new Syargu();
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0){
			weeknum=1;//如果未到实训开始 返回第一周的
			result.put("tab", 0);
		}else{
			result.put("tab", 1);
		}
		map.put("weeknum", weeknum);
    	TrainInfo train=trainInfoService.findTrainInfo(map).get(0);
		result.put("result", train);
		//-------获取可编辑的周数
		String week=syarguService.findSyargu(syargu).get(1).getArguvalue();
		if(week.equals("0")){
		 week=w.getWeeknums(weekdate);
		}
		String[] arr=week.split("&");
		result.put("weeknums", arr);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/getByTno")//根据登录的教师的编号得到他带的班级的所有的学生信息
    public String getByTno(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	JSONObject result=new JSONObject();
    	User user=(User) request.getSession().getAttribute("user");
    	String tno=user.getUsers();
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("tno", tno);
		//获取当前周
		Syargu syargu=new Syargu();
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
		map.put("weeknum", weeknum);
		List<TrainInfo> list=new ArrayList<TrainInfo>();
		list=trainInfoService.findTrainInfo(map);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		//-------获取可编辑的周数
		String week=syarguService.findSyargu(syargu).get(1).getArguvalue();
		if(week.equals("0")){
			week=w.getWeeknums(weekdate);
			}
		String[] arr=week.split("&");
		result.put("weeknums", arr);
		ResponseUtil.write(response, result);
        return null;
    }
    
    @RequestMapping("/getByUid")//获得在某个单位的实训学生
    public String getByUid(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	String uid=request.getParameter("uid");
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", uid);
		//获取当前周
		Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
		map.put("weeknum", weeknum);
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
    	int line=list.size();
    	result.put("number", line);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    //分配题目
    @RequestMapping("/toStu")
    public String toStu(HttpServletResponse response,HttpServletRequest request ) {
    	String snos=request.getParameter("snos");
    	int subjectid=Integer.parseInt(request.getParameter("subjectid"));
    	String[] arr=snos.split(",");
    	Subjectname subjectname=subjectnameService.findById(subjectid) ;
    	//获取当前周
    	Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;
    	for(int j=0;j<arr.length;j++){
    		String sno=arr[j];
    		TrainInfo train=new TrainInfo();
    		train.setSno(sno);
    		train.setSubjectid(subjectid);
    		for(int i=weeknum;i<=18;i++){
			train.setWeeknum(i);
			train.setState(subjectname.getSub_state1());
			trainInfoService.updateTrainInfo(train );
		}
    }
    	return null;
    }
    
    
    //分配指导老师
    @RequestMapping("/toTeacher")
    public String toTeacher(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String snos=request.getParameter("snos");
    	String tno=request.getParameter("tno");
    	if(tno.equals("undefined")) tno=null;
    	String[] arr=snos.split(",");
    	String cno=request.getParameter("cno");
    	if(cno.equals("undefined")) cno="";
    	//获取当前周
		Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
    	for(int i=0;i<arr.length;i++){
    		Practiceappli p=new Practiceappli();
    		p.setSno(arr[i]);
    		p.setTno(tno);
    		TrainInfo trainInfo=new TrainInfo();
    		trainInfo.setTno(tno);
    		trainInfo.setSno(arr[i]);
	    	for(int j=weeknum;j<=18;j++){
	    		trainInfo.setWeeknum(j);
	    		trainInfoService.updateTrainInfo(trainInfo);
	    	}
    		practiceappliService.updatePracticeappli(p);
    	}
    	//分配完成后返回结果
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("cno", cno);
		map.put("weeknum", weeknum);
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }  
    
    //修改指导教师
    @RequestMapping("/teaToStu")
    public String teaToStu(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String sno=request.getParameter("sno");
    	String tno=request.getParameter("tno");
    	String cno=request.getParameter("cno");
     
    	if(cno.equals("undefined")) cno="";
    	//获取当前周
    	Syargu syargu=new Syargu();
    	syargu.setArguname("weekdate");
    	String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
    	WeekNumUtil w=new WeekNumUtil();
    	int weeknum=w.getWeeknum(weekdate);
    	if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
    	Teacher teacher=new Teacher();
    	teacher.setTno(tno);
    	JSONObject result=new JSONObject();
    	List<Teacher> tlist=teacherService.findTeacher(teacher);
    	if(tlist.isEmpty()){
    		result.put("errmsg", 0);
    	}else{
    		TrainInfo trainInfo=new TrainInfo();
    		trainInfo.setTno(tno);
    		trainInfo.setSno(sno);
	    	for(int j=weeknum;j<=18;j++){
	    		trainInfo.setWeeknum(j);
	    		trainInfoService.updateTrainInfo(trainInfo);
	    	}
    		Practiceappli p=new Practiceappli();
    		p.setSno(sno);
    		p.setTno(tlist.get(0).getTno());
    		practiceappliService.updatePracticeappli(p);
    	}
    	//分配完成后返回结果
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("cno", cno);
		map.put("weeknum", weeknum);
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }  
    
    @RequestMapping("/select")//获取到所有学生当前周的实训信息。。。。
    public String select(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	String cno=request.getParameter("cno");
    	String tname=request.getParameter("tname");
    	if(tname.equals("undefined")||tname.equals("全部")){
    		tname="";
    	}
    	if(cno.equals("undefined")) cno="";
    	//获取当前周
    	Syargu syargu=new Syargu();
    	syargu.setArguname("weekdate");
    	String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
    	WeekNumUtil w=new WeekNumUtil();
    	int weeknum=w.getWeeknum(weekdate);
    	if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
    	
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("cno", cno);
		map.put("tname", tname);
		map.put("weeknum", weeknum);
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    } 
    //查询某个学生的所有实训情况
    @RequestMapping("/search") 
    public String search(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	String sname=request.getParameter("sname");
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("sname",sname);
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }  
    //班主任根据实训单位查询实训学生情况
    @RequestMapping("/searchByUname") 
    public String searchByUname(HttpServletResponse response,HttpServletRequest request,@RequestParam(value="uname") String uname) throws IOException{
    	User user=(User) request.getSession().getAttribute("user");
    	String tno=user.getUsers();
    	if(uname.equals("undefined")) uname="";
    	Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("tno",tno);
		map.put("uname",uname);
		map.put("weeknum",weeknum);
    	list=trainInfoService.findTrainInfo(map);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    } 
    //根据指导教师得到所带的学生列表
    @RequestMapping("/findByTno") 
    public String findByTno(HttpServletResponse response,HttpServletRequest request ) throws IOException{
    	User user=(User) request.getSession().getAttribute("user");
    	String tno=user.getUsers();
    	Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum=w.getWeeknum(weekdate);
		if(weeknum==0)weeknum=1;//如果未到实训开始 返回第一周的
    	List<TrainInfo> list=new ArrayList<TrainInfo>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("tno",tno);
		map.put("weeknum",weeknum);
    	list=trainInfoService.findByTno(map);
		//获得每个学生完成的周总结数
		for(int i=0;i<list.size();i++){
			//前台需要 暂时将班级名称保存在备注里
			list.get(i).setRemark(list.get(i).getStudent().getCla().getCname());
			String sn=list.get(i).getSno();
			int nums=weekworkService.findCount(sn);
			list.get(i).setNums(nums);
		}
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    } 
 }