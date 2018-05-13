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

import com.itextpdf.text.DocumentException;
import com.java.entity.Student;
import com.java.entity.Syargu;
import com.java.entity.TrainInfo;
import com.java.entity.Weekwork;
import com.java.service.StudentService;
import com.java.service.SyarguService;
import com.java.service.TrainInfoService;
import com.java.service.WeekworkService;
import com.java.util.PDFUtils;
import com.java.util.RenwushuUtil;
import com.java.util.StringUtil;
import com.java.util.WeekNumUtil;
import com.java.util.WriteExcelUtil;
import com.java.util.ZipUtil;


@Controller
@RequestMapping(value="/importRenwushu")
public class ImportRenwushuController {	
	@Resource
	private WeekworkService weekworkService;
	
	@Resource
	private TrainInfoService trainInfoService;
	
	@Resource
	private SyarguService syarguService;
	
	@Resource
	private StudentService studentService;
	
	
    @RequestMapping("/down")
    public ResponseEntity<byte[]> get(HttpServletResponse response,HttpServletRequest request) throws Exception{
    	StringUtil su=new StringUtil();
    	RenwushuUtil ru=new RenwushuUtil();
    	String snos=request.getParameter("snos");
    	//获取当前周
    	Syargu syargu=new Syargu();
    	syargu.setArguname("weekdate");
    	String weekdate=syarguService.findSyargu(syargu).get(0).getArguvalue();
    	WeekNumUtil w=new WeekNumUtil();
    	String[] arr=snos.split(",");
    	String rootpath=request.getServletContext().getRealPath("/任务书");//根路径
    	File file = new File(rootpath);
		if (!file.exists() && !file.isDirectory()) {
		//创建目录
		file.mkdir();
		}
		List<List<String>> data = new ArrayList<List<String>>();
    	for(int i=0;i<arr.length;i++){
    		List rowData = new ArrayList();
    		Student s=new Student();
    		String sno=arr[i];
    		s.setSno(sno);
    		Student student=studentService.findStudent(s).get(0);//获得该学生的所有信息
    		String sname=student.getSname();
    		String cname=student.getCla().getCname();
	    		String path=rootpath+"\\"+cname;
	    		File file1= new File(path);
	    		if (!file1.exists() && !file1.isDirectory()) {
	    		//创建目录
	    		file1.mkdir();
	    		}
    		String pdfpath=rootpath+"\\"+cname+"\\"+sname+"-"+sno+".pdf";
	    	Map<String,Object> map=new HashMap<String,Object>();
			map.put("sno", sno);
			map.put("weeknum", w.getWeeknum(weekdate));
	    	TrainInfo train=trainInfoService.findTrainInfo2(map).get(0);
	    	ru.write(train, pdfpath);
    	}
 //压缩文件_____________________________________________________________________________________________________
		
		//设置压缩包名名称
		String zipName="实训任务书"+".zip";
		String zipRootPath="d://msFile/upload/zip/";
		String zipPath=zipRootPath+zipName;
		File localFile = new File(zipPath);  
         if(!localFile.getParentFile().exists()) {  
              localFile.getParentFile().mkdirs();  
          } 
		
		//开始压缩
		ZipUtil cpr = new ZipUtil();
		cpr.zip(zipPath, rootpath, true);  			
					
 //_________________________________________end________________________________________________________________________	
		
 //下载文件_____________________________________________________________________________________________________
		
		 File file2 = new File(zipPath);
	     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
	     HttpHeaders header = new HttpHeaders();
	     header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	     header.setContentDispositionFormData("attachment", dfileName);
	    return new  ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), header, HttpStatus.CREATED);
					
 //_________________________________________end________________________________________________________________________	
		
    }   
 }