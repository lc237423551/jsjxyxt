package com.java.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Cla;
import com.java.entity.Speciality;
import com.java.entity.Syargu;
import com.java.entity.TrainInfo;
import com.java.entity.Unit;
import com.java.entity.User;
import com.java.service.ClaService;
import com.java.service.SpecialityService;
import com.java.service.StudentService;
import com.java.service.SyarguService;
import com.java.service.TrainInfoService;
import com.java.service.UnitService;
import com.java.service.WeekworkService;
import com.java.util.StringUtil;
import com.java.util.WeekNumUtil;
import com.java.util.WriteExcelUtil;

@Controller
@RequestMapping(value="/downExcel")
public class DownExcelController {	
	@Resource
	private WeekworkService weekworkService;
	
	@Resource
	private TrainInfoService trainInfoService;
	
	
	@Resource 
	private SpecialityService specialityService;
	@Resource
	private UnitService unitService;
	
	@Resource
	private SyarguService syarguService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private ClaService claService;
	
	
	@RequestMapping(value = "/unit")
	private ResponseEntity<byte[]> down1(HttpServletRequest request,HttpServletResponse res) throws IOException{
		List<Unit> list=new ArrayList<Unit>();
	  	Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum =w.getWeeknum(weekdate);
		if(weeknum==0) weeknum=1;
	  	list=unitService.findUnit(null);
	  	for(int i=0;i<list.size();i++){
	  		int uid=list.get(i).getUid();
	  		TrainInfo train=new TrainInfo();
	  		train.setUid(uid);train.setWeeknum(weeknum);
	  		int num=unitService.findnum(train );
	  		list.get(i).setUnums(num);
	  	}
		String filePath=request.getServletContext().getRealPath("/");
		System.out.println(filePath);
		StringUtil su=new StringUtil();
		List<List<String>> data = new ArrayList<List<String>>();
		 for(int i=0;i<list.size();i++){
			 List rowData = new ArrayList();
			 rowData.add(list.get(i).getUname()+"");
			 String upeople=list.get(i).getUpeople();
			 if(su.isEmpty(upeople)){
				upeople=" "; 
			 }
			 rowData.add(upeople);
			 String Uphone=list.get(i).getUphone();
			 if(su.isEmpty(Uphone)){
				 Uphone=" "; 
			 }
			 rowData.add(Uphone);
			 String Ucity=list.get(i).getUcity();
			 if(su.isEmpty(Ucity)){
				 Ucity=" "; 
			 }
			 rowData.add(Ucity);
			 String Uadress=list.get(i).getUadress();
			 if(su.isEmpty(Uadress)){
				 Uadress=" "; 
			 }
			 rowData.add(Uadress);
			 rowData.add(list.get(i).getUnums()+" ");
			 if(list.get(i).getUnums()>0)
			 data.add(rowData);
		 }
		 	String ExcelFile=filePath+"\\"+"实训单位统计表.xls";
		    OutputStream out = new FileOutputStream(ExcelFile);					
			String[] header = { "单位名称","单位负责人","联系电话","所在城市","地址","实习人数" };
			WriteExcelUtil write = new WriteExcelUtil();
			HSSFWorkbook workbook = new HSSFWorkbook();
			try {
				write.exportExcel(workbook, 0, "实训单位统计表", header, data, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 workbook.write(out);
			 out.close();
			 File file2 = new File(ExcelFile);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders headers = new HttpHeaders();
		     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     headers.setContentDispositionFormData("attachment", dfileName);
		     return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/student" )
	private ResponseEntity<byte[]> student(HttpServletRequest request,HttpServletResponse res,
			@RequestParam(value = "specid", required = false, defaultValue = "") String specid,
			@RequestParam(value = "cno", required = false, defaultValue = "") String cno,
			@RequestParam(value = "sname", required = false, defaultValue = "") String sname,
			@RequestParam(value = "state", required = false, defaultValue = "") String state
			) throws IOException{
		List<TrainInfo> list=new ArrayList<TrainInfo>();
	  	Syargu syargu=new Syargu();
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
    	if(state.equals("全部")) state="";
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum =w.getWeeknum(weekdate);
		if(weeknum==0) weeknum=1;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cno", cno);
		map.put("specid", specid);
		map.put("state", state);
		map.put("sname", sname);
		map.put("weeknum", weeknum);
		list=trainInfoService.findTrainInfo(map);
		String filePath=request.getServletContext().getRealPath("/");
		System.out.println(filePath);
		StringUtil su=new StringUtil();
		List<List<String>> data = new ArrayList<List<String>>();
		 for(int i=0;i<list.size();i++){
			 List rowData = new ArrayList();
			 rowData.add(list.get(i).getStudent().getCla().getSpeciality().getSpecname());
			 rowData.add(list.get(i).getStudent().getCla().getCname());
			 rowData.add(list.get(i).getStudent().getSno());
			 rowData.add(list.get(i).getStudent().getSname());
			 rowData.add(list.get(i).getTeacher().getTname());
			 rowData.add(list.get(i).getUnit().getUname());
			 rowData.add(list.get(i).getSubjectname().getName());
			 rowData.add(list.get(i).getState());
			 data.add(rowData);
		 }
		 	String ExcelFile=filePath+"\\"+"第"+weeknum+"周"+"学生实训情况统计表.xls";
		    OutputStream out = new FileOutputStream(ExcelFile);					
			String[] header = { "专业","班级","学号","姓名","指导教师","实训单位","实训题目","状态"};
			WriteExcelUtil write = new WriteExcelUtil();
			HSSFWorkbook workbook = new HSSFWorkbook();
			try {
				write.exportExcelWithTitel(workbook, 0, "第"+weeknum+"周", header, data, out,"第"+weeknum+"周实训情况");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 workbook.write(out);
			 out.close();
			 File file2 = new File(ExcelFile);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders headers = new HttpHeaders();
		     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     headers.setContentDispositionFormData("attachment", dfileName);
		     return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), headers, HttpStatus.CREATED);
	}
	//班主任导出班级明细表
	@RequestMapping(value = "/downByCTeacher")
	private ResponseEntity<byte[]> downByCTeacher(HttpServletRequest request,HttpServletResponse res) throws IOException{
		List<TrainInfo> list=new ArrayList<TrainInfo>();
		String rootpath="d://msFile/upload/";
	  	Syargu syargu=new Syargu();
		syargu.setArguname("weekdate");
		String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
		WeekNumUtil w=new WeekNumUtil();
		int weeknum =w.getWeeknum(weekdate);
		if(weeknum==0) weeknum=1;
		String cno =request.getParameter("cno");
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("cno", cno);
		Cla cla=claService.findCla(map1).get(0);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cno", cno);
		map.put("weeknum", weeknum);
		list=trainInfoService.findTrainInfo(map);
		String path=rootpath+cla.getCname()+"\\";
		StringUtil su=new StringUtil();
		List<List<String>> data = new ArrayList<List<String>>();
		 for(int i=0;i<list.size();i++){
			 List rowData = new ArrayList();
			 rowData.add(i+1+" ");
			 rowData.add(list.get(i).getStudent().getCla().getSpeciality().getSpecname()+" ");
			 rowData.add(list.get(i).getStudent().getCla().getCname()+" ");
			 rowData.add(list.get(i).getStudent().getSno()+" ");
			 rowData.add(list.get(i).getStudent().getSname()+" ");
			 rowData.add(list.get(i).getSubjectname().getName()+" ");
			 rowData.add(list.get(i).getTeacher().getTname()+" ");
			 rowData.add(list.get(i).getTeacher().getTpost()+" ");
			 rowData.add(list.get(i).getTeacher().getTdegree()+" ");
			 data.add(rowData);
		 }
		 	String ExcelFile=path+cla.getCname()+"专业实训题目明细表.xls";
		    OutputStream out = new FileOutputStream(ExcelFile);					
			String[] header = {"序号", "专业","班级","学号","姓名","题目","教师姓名","教师职称","教师学位"};
			WriteExcelUtil write = new WriteExcelUtil();
			HSSFWorkbook workbook = new HSSFWorkbook();
			try {
				write.exportExcelWithTitel(workbook, 0, "第"+weeknum+"周", header, data, out,"计算机学院2017-2018（1）软件工程专业实训明细表");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 workbook.write(out);
			 out.close();
			 File file2 = new File(ExcelFile);
		     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
		     HttpHeaders headers = new HttpHeaders();
		     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		     headers.setContentDispositionFormData("attachment", dfileName);
		     return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), headers, HttpStatus.CREATED);
	}
	
 }