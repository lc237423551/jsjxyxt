package com.java.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.java.entity.Cla;
import com.java.entity.Student;
import com.java.entity.Syargu;
import com.java.entity.TrainInfo;
import com.java.entity.Weekwork;
import com.java.service.StudentService;
import com.java.service.SyarguService;
import com.java.service.TrainInfoService;
import com.java.service.WeekworkService;
import com.java.util.PDFUtils;
import com.java.util.StringUtil;
import com.java.util.TRemarkUtil;
import com.java.util.WriteExcelUtil;
import com.java.util.ZipUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/importtremark")
public class ImportTremarkController {	

	@Resource
	private StudentService studentService;
	
	
    @RequestMapping("/down")
    public ResponseEntity<byte[]> get(HttpServletResponse response,HttpServletRequest request) throws Exception{
    	Cla cla= (Cla) request.getSession().getAttribute("cla");
    	String cno=cla.getCno();
    	Student student=new Student();
    	student.setCno(cno);
    	List<Student> list=new ArrayList<Student>();
    	list=studentService.findStudent(student);
    	String rootpath=request.getServletContext().getRealPath("/教师评语/");//根路径
    	File file = new File(rootpath);
		if (!file.exists() && !file.isDirectory()) {
		System.out.println(rootpath+"目录不存在，需要创建");
		//创建目录
		file.mkdir();
		}
		int y,m,d,h,mi,s;      
	    Calendar cal=Calendar.getInstance();      
	    y=cal.get(Calendar.YEAR);      
	    m=cal.get(Calendar.MONTH);      
	    d=cal.get(Calendar.DATE);
	    String cname="不存在学生";
	    TRemarkUtil tu=new TRemarkUtil();
//			String sname=list.get(i).getSname();
			String path=rootpath+"教师评语"+".doc";
			tu.write(path, list, y, m, d);
		
		
 //下载文件_____________________________________________________________________________________________________
		
		 File file2 = new File(path);
	     String dfileName = URLEncoder.encode(file2.getName(),"UTF-8");
	     HttpHeaders header = new HttpHeaders();
	     header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	     header.setContentDispositionFormData("attachment", dfileName);
	    return new  ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2), header, HttpStatus.CREATED);
					
 //_________________________________________end________________________________________________________________________	
		
    }   
 }