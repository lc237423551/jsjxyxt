package com.java.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.java.entity.Speciality;
import com.java.entity.Student;
import com.java.entity.Subjectname;
import com.java.entity.Syargu;
import com.java.entity.Teacher;
import com.java.entity.TrainInfo;
import com.java.entity.Unit;
import com.java.entity.User;
import com.java.entity.Vacate;
import com.java.service.PracticeappliService;
import com.java.service.ShixunInfoService;
import com.java.service.SpecialityService;
import com.java.service.StudentService;
import com.java.service.SubjectnameService;
import com.java.service.SyarguService;
import com.java.service.TeacherService;
import com.java.service.TrainInfoService;
import com.java.service.UnitService;
import com.java.service.VacateService;
import com.java.util.JsonDateValueProcessor;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import com.java.util.WeekNumUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping(value="/apply")
public class ApplyController {
	@Resource 
	private  UnitService unitservice;
	@Resource
	private VacateService vacateservice;
	@Resource
	private PracticeappliService practiceappliservice;
	@Resource 
	private SyarguService syarguService;
	@Resource
	private TrainInfoService traininfoservice;
	@Resource
	private ShixunInfoService shixuninfoservice;
	@Resource
	private TeacherService teacherService;
	@Resource
	private SubjectnameService subjectnameservice;
	@Resource
	private StudentService studentservice;
	@Resource
	private SpecialityService specialityservice;
	//*******学生操作
	/***
	 * 验证学生的状态
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/stuverify")
	public void stuverify(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User) request.getSession().getAttribute("user");
		String sno=user.getUsers();
		JSONObject result=new JSONObject();
		Map map=new HashMap();
		map.put("sno", sno);
		//判断学生的邮箱和手机号是否填写
		Student stu=new Student();
		stu.setSno(sno);
		Student student=studentservice.findStudent(stu).get(0);
		StringUtil su=new StringUtil();
//		if("".equals(stu.getSphone())||stu.getSphone()==null||"".equals(stu.getSemail())||stu.getSemail()==null){
		if(su.isEmpty(student.getSphone())||su.isEmpty(student.getSemail())){	
		result.put("stuinfo", 0);
		}else{
			result.put("stuinfo", 1);
		}
		Date datenow=new Date();
		List<Vacate> vacatelist=vacateservice.findApply(map);
		//从未申请过请假
		if(vacatelist.isEmpty()){
			result.put("result", 0);
		}else{
			//申请过请假但已经结束
			if(datenow.after(vacatelist.get(0).getV_endtime())&&vacatelist.get(0).getV_status()==4){
				result.put("result", 0);

			}else{//请假尚未结束				
				result.put("result", vacatelist.get(0).getV_status());				
				JsonConfig config = new JsonConfig();
				JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();
				config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
				JSONArray statusj=JSONArray.fromObject(vacatelist,config);
				result.put("message", statusj);
			}
		}
		ResponseUtil.write(response, result);
	}
	

	/***
	 * 学生添加请假实训信息
	 * @param response
	 * @param request
	 * @param pra
	 * @param vacate
	 * @throws ParseException
	 */
	@RequestMapping("/stuaddapply")
	public void addStuApply(HttpServletResponse response,HttpServletRequest request,Practiceappli pra,Vacate vacate) throws ParseException{
		//得到日期并转换格式 保存在请假信息中
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String strattimeStr=request.getParameter("starttime");
		String endtimeStr=request.getParameter("endtime");
		String leavetimeStr=request.getParameter("leavetime");
		Date v_strattime=sdf.parse(strattimeStr);
		Date v_endtime=sdf.parse(endtimeStr); 
		Date v_leavetime=sdf.parse(leavetimeStr);
		vacate.setV_strattime(v_strattime);
		vacate.setV_endtime(v_endtime);
		vacate.setV_leavetime(v_leavetime);
		//得到公司 并保存到实训信息 （如果公司不存在就默认添加一个未审核公司）
		String pa_interncompany=request.getParameter("pa_interncompany");
		int uid=0;
		if(pa_interncompany.equals("其他")){
			Unit unit=new Unit();
			String othercompany=request.getParameter("otherCompany");
			//如果已存在此未审核的公司就得到id，不存在就添加
			unit.setUname(othercompany);
			unit.setUstatus("未审核");
			List<Unit> unitlist=unitservice.findByname(unit);
			if(unitlist.size()!=0){
				uid=unitlist.get(0).getUid();
			}else{
				unitservice.insertUnit(unit);
				uid=unit.getUid();
			}
			
		}else{
			Unit unit=new Unit();
			unit.setUname(pa_interncompany);
			unit.setUstatus("已审核");
			List<Unit> unitlist=unitservice.findByname(unit);
			uid=unitlist.get(0).getUid();
		}
//		System.out.println(pra.getTno());
		pra.setUid(uid);
		//得到学生学号并保存到实训以及请假信息
		User user=(User) request.getSession().getAttribute("user");
		String sno=user.getUsers();
		pra.setSno(sno);
		vacate.setSno(sno);
		//默认设置实训题目外键为1
		pra.setSub_id(1);
		//默认设置初始提交状态为1
		vacate.setV_status(1);
		//保存实训以及请假信息
		practiceappliservice.insertPracticeappli(pra);
		int pa_id=pra.getPa_id();
		vacate.setPa_id(pa_id);
		vacateservice.insertVacate(vacate);		
	}
	
	/***
	 * 学生撤销实训信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("/studelete")//学生撤销申请信息
	public void deleteApply(HttpServletResponse response,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		String sno=user.getUsers();
		Map map=new HashMap();
		map.put("sno", sno);
		List<Vacate> vacatelist=vacateservice.findApply(map);
		
		int pa_id = 0;
		for(Vacate vacate:vacatelist){
			if(vacate.getV_status()==1){
				pa_id=vacate.getPa_id();
				//删除请假信息
				vacateservice.deleteVacate(vacate);
				Practiceappli pra=new Practiceappli();
				pra.setPa_id(pa_id);
				List<Practiceappli> pralist=practiceappliservice.findPractice(pra);
				int uid=0;
				uid=pralist.get(0).getUid();
				//删除实训信息
				practiceappliservice.deletePracticeappli(pra);
				Unit unit=new Unit();
				unit.setUid(uid);
				List<Unit> unitlist=unitservice.findByname(unit);
				//删除未审核的公司
				if(unitlist.get(0).getUstatus().equals("未审核")&&unitlist.size()==0){
					unitservice.deleteUnit(uid);
				}
			}
		}	
	}
	//****************辅导员操作
	/***
	 * 辅导员得到已申请请假学生信息
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getapplycou")
	public void getApplyCounselor(HttpServletResponse response,HttpServletRequest request) throws IOException{
		JSONObject result=new JSONObject();
		Map map =new HashMap();		
		map.put("v_status", 1);
		List<Vacate> vacatelist=vacateservice.findApply(map);
		JsonConfig config = new JsonConfig();  
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor(); //将时间格式转换为字符串格式 
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		JSONArray statusj=JSONArray.fromObject(vacatelist,config);
		result.put("result", statusj);
		ResponseUtil.write(response, result);		
	}
	/***
	 * 辅导员修改实训信息中公司外键
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("couupdateunit")
	public void couUpdateUnit(HttpServletResponse response,HttpServletRequest request) throws IOException{
		//得到值
		String lastuid=request.getParameter("uid");
		String pa_id=request.getParameter("pa_id");
		String uname=request.getParameter("uname");
		Unit unit=new Unit();
		unit.setUname(uname);
		List<Unit> unitlist=unitservice.findByname(unit);
		//判断公司是否有变化
		if(unitlist.get(0).getUid()!=Integer.parseInt(lastuid)){
			//首先修改实训信息中的公司
			Practiceappli pra=new Practiceappli();
			pra.setUid(unitlist.get(0).getUid());
			pra.setPa_id(Integer.parseInt(pa_id));
			practiceappliservice.updatePracticeappli(pra);
			//判断实训信息中公司是否可以删除
			Practiceappli pra2=new Practiceappli();
			pra2.setUid(Integer.parseInt(lastuid));
			List<Practiceappli> pralist=practiceappliservice.findPractice(pra2);
			
			Unit lastUnit=new Unit();
			lastUnit.setUid(Integer.parseInt(lastuid));
			List<Unit> lastUnitlist=unitservice.findByname(lastUnit);
			if(pralist.size()==0&&lastUnitlist.get(0).getUstatus().equals("未审核")){
				
				unitservice.deleteUnit(Integer.parseInt(lastuid));
			}
			//返回给前台数据
			JSONObject result=new JSONObject();
			Map map =new HashMap();		
			map.put("v_status", 1);
			List<Vacate> vacatelist=vacateservice.findApply(map);
			JsonConfig config = new JsonConfig();  
	        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor(); //将时间格式转换为字符串格式 
	        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
			JSONArray statusj=JSONArray.fromObject(vacatelist,config);
			result.put("result", statusj);
			result.put("message", "1");//1为数据发生了变化
			ResponseUtil.write(response, result);			
		}else{
			JSONObject result=new JSONObject();
			result.put("message", "0");//0为数据未发生变化
			ResponseUtil.write(response, result);
		}
	}
	/***
	 * 辅导员更新学生请假状态
	 * @param response
	 * @param request
	 */
	@RequestMapping("/couupdatecou")
	public void couUpdateStatue(HttpServletResponse response,HttpServletRequest request){
		String status=request.getParameter("v_status");
		String v_id=request.getParameter("v_id");
		Vacate vacate=new Vacate();
		
		vacate.setV_id(Integer.parseInt(v_id));
		vacate.setV_status(Integer.parseInt(status));
		vacateservice.updateStaus(vacate);		
	}
	/***
	 * 辅导员得到搜索后的信息
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getsearchcou")
	public void getSearchCou(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String status=request.getParameter("v_status");
		String specname=request.getParameter("specname");
		String cname=request.getParameter("cname");
		JSONObject result=new JSONObject();
		Map map=new HashMap();
		if(status.equals("5")){
			
		}else{
			map.put("v_status", status);
		}
		
		
		if(cname.equals("全部")){
			
		}else{
			map.put("cname", cname);
		}
		if(specname.equals("全部")){
			
		}else{
			map.put("specname", specname);
		}		
		List<Vacate> vacatelist=vacateservice.searchApply(map);
		JsonConfig config = new JsonConfig();  
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		JSONArray vacatejson=JSONArray.fromObject(vacatelist,config);
		result.put("result", vacatejson);
		ResponseUtil.write(response, result);
	}
	//指导老师操作
	/***
	 * 指导老师修改学生状态
	 * @param response
	 * @param request
	 * @throws ParseException
	 */
	@RequestMapping("/teaupdatestatus")
	public void teaUpdateStatus(HttpServletResponse response,HttpServletRequest request) throws ParseException{
		String status=request.getParameter("v_status");
		String v_id=request.getParameter("v_id");
		Date canceldate=null;
		Date nowdate=new Date();
		String cancelstr="2014-09-15";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		canceldate=sdf.parse(cancelstr);
		Vacate vacate=new Vacate();
		vacate.setV_id(Integer.parseInt(v_id));
		vacate.setV_status(Integer.parseInt(status));
	    if(status.equals("1")){//失败撤销
	    	vacate.setV_confirmtime(canceldate);
	    	vacate.setV_backreason("已撤销但理由未填写");
	    	vacateservice.updateStaus(vacate);
	    	
	    }else{//成功
	    	vacate.setV_confirmtime(nowdate);
	    	vacateservice.updateStaus(vacate);
	    }
	}
	/***
	 * 指导老师获得需要通过学生的信息
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getapplybytno")
	public void getApplyByTno(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User) request.getSession().getAttribute("user");
		JSONObject result=new JSONObject();
		String tno=user.getUsers();
		Map map=new HashMap();
		map.put("tno", tno);
		map.put("v_status", 2);
		List<Vacate> vacatelist=vacateservice.findApply(map);
		JsonConfig config = new JsonConfig();  
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		JSONArray vacatejson=JSONArray.fromObject(vacatelist,config);
		result.put("result", vacatejson);
//		System.out.println(vacatelist.size()+tno);
		ResponseUtil.write(response, result);
	}
	/***
	 * 指导老师搜索学生的信息
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getsearchtno")
	public void getSearchTno(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String status=request.getParameter("v_status");
		String cname=request.getParameter("cname");
		String specname=request.getParameter("specname");
		User user=(User) request.getSession().getAttribute("user");
		JSONObject result=new JSONObject();
		String tno=user.getUsers();
		Map map=new HashMap();
		map.put("tno", tno);
		if(status.equals("5")){
			
		}else{
			map.put("v_status", status);
		}
//		System.out.println(tno);
		if(cname.equals("全部")){
			
		}else{
			map.put("cname", cname);
		}
		if(specname.equals("全部")){
			
		}else{
			map.put("specname", specname);
		}	
		List<Vacate> vacatelist=vacateservice.searchApply(map);
		JsonConfig config = new JsonConfig();  
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		JSONArray vacatejson=JSONArray.fromObject(vacatelist,config);
		result.put("result", vacatejson);
		ResponseUtil.write(response, result);
	}
	//专业负责人操作
	/***
	 * 专业负责人修改学生状态
	 * @param response
	 * @param request
	 * @throws ParseException
	 */
	@RequestMapping("/dirupdatestatus")
	public void dirUpdateStatus(HttpServletResponse response,HttpServletRequest request) throws ParseException{
		WeekNumUtil weeknum=new WeekNumUtil();
		TrainInfo trainInfo=new TrainInfo();
		ShixunInfo shixunInfo=new ShixunInfo();
		Practiceappli pra=new Practiceappli();
		
		String status=request.getParameter("v_status");
		String v_id=request.getParameter("v_id");
		String startime=request.getParameter("v_strattime");
		String endtime=request.getParameter("v_endtime");
		String companyid=request.getParameter("companyid");
		String pa_internname=request.getParameter("pa_internname");
		
		String sno=request.getParameter("sno");
		Date canceldate=null;
		Teacher teacher=teacherService.findBySno(sno);
		
		pra=practiceappliservice.selectPracticeappli(sno);
		//得到实训日期开始结束周数
		Syargu syargu=new Syargu();    
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		int startweek=weeknum.get(weekdate, startime);
		int endweek=weeknum.get(weekdate, endtime);
		//设置通过时间
		Date nowdate=new Date();
		//设置撤销时的通过时间
		String cancelstr="2014-09-15";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		canceldate=sdf.parse(cancelstr);
		//修改申请状态
		Vacate vacate=new Vacate();
		vacate.setV_id(Integer.parseInt(v_id));
		vacate.setV_status(Integer.parseInt(status));
		trainInfo.setSno(sno);
	    if(status.equals("1")){//撤销申请
	    	vacate.setV_backreason("已撤销但理由未填写");
	    	//修改实训信息
	    	shixunInfo.setSno(sno);
	    	
	    	shixunInfo.setDealtime(" ");
	    	shixuninfoservice.updateShixunInfo(shixunInfo);
	    	//修改18周状态信息
	    	trainInfo.setUid(1);
	    	trainInfo.setState("校内");
	    	for(int i=startweek;i<endweek+1;i++){	    		
	    		trainInfo.setWeeknum(i);
	    		trainInfo.setSubjectid(1);
	    		traininfoservice.updateTrainInfo(trainInfo);
	    	}
	    	vacate.setV_throughtime(canceldate);    	
	    	vacateservice.updateStaus(vacate);
	    	//撤销后修改实训请假表中sup_id外键默认为1 并删除实训题目
	    	int sub_id=pra.getPa_id();
	    	pra.setSub_id(1);
	    	practiceappliservice.updatePracticeappli(pra);
//	    	subjectnameservice.deleteSubjectname(sub_id);
	    }else{//通过申请
	    	//保存实训题目信息
			Subjectname sub=new Subjectname();
			sub.setName(pra.getPa_internname());
			sub.setTno(pra.getTno());
			sub.setSub_state("未审核");
			sub.setSub_state1("校外");
			subjectnameservice.insertSubjectname(sub);			
			pra.setSub_id(sub.getId());	    	
	    	practiceappliservice.updatePracticeappli(pra);
	    	
	    	
	    	//保存到实训信息
	    	shixunInfo.setSno(sno);
	    
	    	shixunInfo.setDealtime(sdf.format(nowdate));
	    	shixuninfoservice.updateShixunInfo(shixunInfo);
	    	//修改18周实训状态
	    	trainInfo.setUid(Integer.parseInt(companyid));	
	    	trainInfo.setState("校外");	    	
	    	for(int i=startweek;i<endweek+1;i++){
	    		trainInfo.setSubjectid(pra.getSub_id());
	    		trainInfo.setWeeknum(i);
	    		trainInfo.setTno(pra.getTno());
	    		traininfoservice.updateTrainInfo(trainInfo);
	    	}
	    	vacate.setV_throughtime(nowdate);	
	    	vacateservice.updateStaus(vacate);
	    }
	}
	
	
	/***
	 * 专业负责人得到指导老师已审核的学生
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getapply")
	public void getApply(HttpServletResponse response,HttpServletRequest request) throws IOException{
		//得到专业负责人负责的专业
		User user=(User) request.getSession().getAttribute("user");
		String tno=user.getUsers();
		Speciality spe=new Speciality();
		spe.setTno(tno);
		List<Speciality> spelist=specialityservice.findSpeciality(spe);
		//得到数据
		JSONObject result=new JSONObject();		
		Map map=new HashMap();
		map.put("v_status", 3);
		map.put("specname", spelist.get(0).getSpecname());
		List<Vacate> liststatus=vacateservice.searchApply(map);
		
		JsonConfig config = new JsonConfig();  
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		JSONArray statusj=JSONArray.fromObject(liststatus,config);
		result.put("result", statusj);
		
		ResponseUtil.write(response, result);
	}
	/***
	 * 专业负责人得到搜索后的信息
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getsearchmaster")
	public void getSearchMaster(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User) request.getSession().getAttribute("user");
		String tno=user.getUsers();
		String status=request.getParameter("v_status");
		String specname=request.getParameter("specname");
		String cname=request.getParameter("cname");
		JSONObject result=new JSONObject();
		Map map=new HashMap();
		map.put("tno2", tno);
		if(status.equals("5")){
			
		}else{
			map.put("v_status", status);
		}
		
		
		if(cname.equals("全部")){
			
		}else{
			map.put("cname", cname);
		}
		if(specname.equals("全部")){
			
		}else{
			map.put("specname", specname);
		}		
		List<Vacate> vacatelist=vacateservice.searchApply(map);
		JsonConfig config = new JsonConfig();  
        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
		JSONArray vacatejson=JSONArray.fromObject(vacatelist,config);
		result.put("result", vacatejson);
		ResponseUtil.write(response, result);
	}
	
	//填写撤销理由
	@RequestMapping("/backreason")
	public void backreason(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User) request.getSession().getAttribute("user");
		String tno=user.getUsers();
		String v_backreason=request.getParameter("v_backreason");
		String v_id=request.getParameter("v_id");
		Vacate v=new  Vacate();
		v.setV_backreason(v_backreason);
		v.setV_id(Integer.parseInt(v_id));
		vacateservice.updateStaus(v);
		
		ResponseUtil.write(response, 0);
	}
	//根据v_id得到申请信息
	@RequestMapping("/findById")
	public void findById(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String v_id=request.getParameter("v_id");
		Vacate v=vacateservice.findById(Integer.parseInt(v_id));
		JSONObject result=new JSONObject();
		result.put("result", v);
		ResponseUtil.write(response, result);
	}
}
