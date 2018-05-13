package com.java.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Cla;
import com.java.entity.Practiceappli;
import com.java.entity.ShixunInfo;
import com.java.entity.Student;
import com.java.entity.TrainInfo;
import com.java.entity.User;
import com.java.entity.Vacate;
import com.java.entity.Weekwork;
import com.java.service.ClaService;
import com.java.service.PracticeappliService;
import com.java.service.ShixunInfoService;
import com.java.service.StudentService;
import com.java.service.TrainInfoService;
import com.java.service.UnitService;
import com.java.service.UserService;
import com.java.service.VacateService;
import com.java.service.WeekworkService;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import com.java.util.WordGenerator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/student")
public class StudentController {	
	@Resource
	private StudentService studentService;
	
	@Resource 
	private ClaService claService;
	
	@Resource
	private ShixunInfoService shixunInfoService;
	
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource
	private WeekworkService weekworkservice;
	
	@Resource
	private UserService userService;
	
	@Autowired
	private PracticeappliService practiceappliService;
	
	@Autowired
	private VacateService vacateService;
	
	@Autowired
	private UnitService unitService;
	
	//添加一个学生
	@RequestMapping("/insert")
	public String insert(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String sno=request.getParameter("sno");
		String ssex=request.getParameter("ssex");
		String cno=request.getParameter("cno");
		String sname=request.getParameter("sname");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cno", cno);
		Cla cla=claService.findCla(map).get(0);
		String tno=cla.getTeacher().getTno();
		//1添加基本信息
		Student student=new Student();
		student.setSno(sno);
		student.setSname(sname);
		student.setSsex(ssex);
		student.setCno(cno);
		JSONObject result=new JSONObject();
    	try{
    		studentService.insertStudent(student);
			result.put("errmsg", "1");
			
		}catch (Exception e) {
			result.put("errmsg", "0");
			List<Student> list=studentService.findStudent(null);
			JSONArray jsonArray=JSONArray.fromObject(list);
			result.put("newsInsert", sno);
			result.put("result", jsonArray);
			ResponseUtil.write(response, result);
			return null;
		}
		//2添加ShixunInfo
    	ShixunInfo shi=new ShixunInfo();
    	shi.setSno(sno);
    	shi.setSxpdfState("0");
    	shixunInfoService.insertShixunInfo(shi);
		//3添加TrainInfo
    	List<TrainInfo> tlist=new ArrayList<TrainInfo>();
    	for(int j=1;j<=18;j++){
    		TrainInfo train=new TrainInfo();
        	train.setSno(sno);
        	train.setState("校内");
        	train.setUid(1);
        	train.setTno(tno);
        	train.setSubjectid(1);
        	train.setWeeknum(j);
        	tlist.add(train);
    	}
    	trainInfoService.insertTrainInfo(tlist);	
		//4添加Weekwork
    	List<Weekwork> wlist=new ArrayList<Weekwork>();
    	for(int j=1;j<=18;j++){
    		Weekwork week=new Weekwork();
        	week.setSno(sno);
        	week.setTab("0");
    		week.setWeeknum(j);
    		wlist.add(week);
    	}
		weekworkservice.insertWeekwork(wlist);
		//5添加用户
		User user=new User();
		user.setUsers(sno);
		user.setRole("学生");
		user.setUsername(sname);
		user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
		userService.insertUser(user);
    	//添加成功后
    	List<Student> list=studentService.findStudent(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("newsInsert", sno);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/getStudentInfo")
	public String getStudentInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User)request.getSession().getAttribute("user");
		String sno=user.getUsers();
		Student student=new Student();
		student.setSno(sno);
		List<Student> list=studentService.findStudent(student);
		student=list.get(0);
		JSONObject result=new JSONObject();
		result.put("result", student);
		ResponseUtil.write(response, result);
		return null;
	}
    
	@RequestMapping("/update")
	public String update(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String sno=request.getParameter("sno");
		String sname=request.getParameter("sname");
		String sid=request.getParameter("sid");
		String ssex=request.getParameter("ssex");
		String semail=request.getParameter("semail");
		String sphone=request.getParameter("sphone");
		String sadress=request.getParameter("sadress");
		String sremark=request.getParameter("sremark");
		String cno=request.getParameter("cno");
		
		String cname=request.getParameter("cname");
		StringUtil su=new StringUtil();
		if(!su.isEmpty(cname)){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("cname", cname);
			cno=claService.findCla(map).get(0).getCno();
		}
		
		Student student=new Student(sno,sname,sid,ssex,semail,sphone,sadress,sremark,cno);
		studentService.updateStudent(student);
		return null;
	}
	
	@RequestMapping("/get")//得到所有学生的信息
	public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Student student=new Student();
		String cno=request.getParameter("cno");
		student.setCno(cno);
		List<Student> list=studentService.findStudent(student);
		JSONArray jsonArray=JSONArray.fromObject(list);
		JSONObject result=new JSONObject();
		result.put("result", jsonArray);
		result.put("nums", list.size());
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/delete")//删除学生
	public String delete(HttpServletResponse response,HttpServletRequest request) throws IOException {
		JSONObject result=new JSONObject();
		String sno=request.getParameter("sno");
		try{
			studentService.deleteStudent(sno);
			result.put("errmsg", "1");
		}catch (Exception e) {
			result.put("errmsg", "0");
		}
		List<Student> list=studentService.findStudent(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/search")
    public String search(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	String search =request.getParameter("search");
    	JSONObject result=new JSONObject();

    	Pattern pattern=Pattern.compile("[0-9]*");
    	Matcher isNum = pattern.matcher(search);
    	List<Student> list=new ArrayList<Student>();
    	if(isNum.matches()){//按照教职工号查
    		Student student=new Student();
    		student.setSno(search);
    		list=studentService.findStudent(student);
    	}else{//按照教师姓名查
    		Student student=new Student();
    		student.setSname(search);
    		list=studentService.findStudent(student);
    	}
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("line", list.size());
		ResponseUtil.write(response, result);
    	return null;
    }
	
	@RequestMapping("/exportWord")
	public void exportWord(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		User user=(User)req.getSession().getAttribute("user");
		String studentId=user.getUsers();
		Practiceappli practiceappli=practiceappliService.selectPracticeappli(studentId);
		Vacate vacate=vacateService.getVacateByStudentId(studentId);
		Student student=studentService.getStudentbyId(studentId);
		map.put("sname", student.getSname());
		map.put("cname", student.getCla().getCname());
		map.put("phone", student.getSphone()==null?" ":student.getSphone());
		map.put("telphone", vacate.getTelphone()==null?" ":vacate.getTelphone() );
		//map.put("startTime", (vacate.getV_strattime().getYear()+1900)+"年"+vacate.getV_strattime().getMonth()+"月"+vacate.getV_strattime().getDay()+"日");
		Date date = vacate.getV_strattime();
	    long times = date.getTime();//时间戳
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
	    String startTime = formatter.format(date);
	    map.put("startTime", startTime);
		//map.put("endTime", (vacate.getV_endtime().getYear()+1900)+"年"+vacate.getV_endtime().getMonth()+"月"+vacate.getV_endtime().getDay()+"日");
	    date = vacate.getV_endtime();
	    times = date.getTime();//时间戳
	    String endTime = formatter.format(date);
	    map.put("endTime", endTime);
		//map.put("leaveTime", (vacate.getV_leavetime().getYear()+1900)+"年"+vacate.getV_leavetime().getMonth()+"月"+vacate.getV_leavetime().getDay()+"日");
		//System.out.println(" "+vacate.getV_leavetime().getMonth()+" "+vacate.getV_leavetime().getDay());
		date = vacate.getV_leavetime();
	    times = date.getTime();//时间戳
	    String leaveTime = formatter.format(date);
	    map.put("leaveTime", leaveTime);
		map.put("reason", vacate.getV_reason());
		map.put("where", vacate.getV_stayplace());
		map.put("subjectname", practiceappli.getPa_internname());
		map.put("pa_internreason", practiceappli.getPa_internreason());
		//根据公司id(uid)获取公司名称
		map.put("pa_interncompany", unitService.getCompanyNameById(practiceappli.getUid()));
		map.put("pa_internplace", practiceappli.getPa_internplace());
		map.put("pa_collteaname", practiceappli.getPa_collteaname());
		map.put("pa_collteaposition", practiceappli.getPa_collteaposition());
		map.put("pa_collteaphone", practiceappli.getPa_collteaphone());	
		// 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整
		// 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordGenerator.createDoc(map, "resume");
			fin = new FileInputStream(file);
			
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/msword");
			resp.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("打印.doc", "UTF-8"));
			
			out = resp.getOutputStream();
			byte[] buffer = new byte[512];	// 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if(fin != null) fin.close();
			if(out != null) out.close();
			if(file != null) file.delete();	// 删除临时文件
		}
	}
	
 }