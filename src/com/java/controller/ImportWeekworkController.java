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
import com.java.util.StringUtil;
import com.java.util.WriteExcelUtil;
import com.java.util.ZipUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/importWeekwork")
public class ImportWeekworkController {	
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
    	PDFUtils pdf=new PDFUtils();
    	StringUtil su=new StringUtil();
    	String snos=request.getParameter("snos");
    	String[] arr=snos.split(",");
    	Syargu syargu=new Syargu();
    	syargu.setArguname("unneed");
    	String unneeds=syarguService.findSyargu(syargu).get(0).getArguvalue();
    	String[] unneed=unneeds.split("&");
    	String rootpath=request.getServletContext().getRealPath("/周总结");//根路径
    	String serverPath="d://msFile/upload/";//在服务器上赋值一份
    	
    	File file = new File(rootpath);
		if (!file.exists() && !file.isDirectory()) {
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
    		String pdfpath=rootpath+"\\"+cname+"\\"+sno+"-"+sname+".pdf";
	    	Weekwork week=new Weekwork();
	    	week.setSno(sno);
	    	Map<String,Object> map=new HashMap<String,Object>();
			map.put("sno", sno);
	    	List<Weekwork> wlist=new ArrayList<Weekwork>();
	    	List<TrainInfo> tlist=new ArrayList<TrainInfo>();
	    	wlist=weekworkService.findWeekwork(week);
	    	tlist=trainInfoService.findTrainInfo(map);
	    	pdf.write(sno, sname, tlist, wlist, pdfpath, unneed);
	    	//复制一份
	    	String  serverPath2= serverPath+student.getCla().getCname()+"\\"+student.getSno()+"-"+student.getSname()+"\\"+"周总结.pdf";
    		File  serverFile=new File(pdfpath);
    		File  localFile=new File(serverPath2);
    		 if(!serverFile.getParentFile().exists()) {  
    			 serverFile.getParentFile().mkdirs();  
               } 
    		 FileUtils.copyFile(serverFile,localFile);
	    	//统计未完成的个数和未完成的周
	    	int unfinishnums=0;
			//求出完成的周，
			String unfinish="";
			for(int j=0;j<wlist.size();j++){
				String tab=wlist.get(j).getTab();
				String wnum=String.valueOf(j+1);
				if((!su.isExist(unneed,wnum))&&tab.equals("0")){
					unfinish=unfinish+(j+1)+",";
					unfinishnums++;
				}
			}
			if(unfinishnums!=0&&unfinishnums>0){
				rowData.add(sno+" ");
				rowData.add(sname+" ");
				rowData.add(unfinishnums+" ");
				rowData.add(unfinish+" ");
				data.add(rowData);
			}
    	}//end_for
 //导出未完成的统计表Excel_____________________________________________________________________________________________________
		OutputStream out = new FileOutputStream(rootpath+"\\"+"未完成.xls");					
		String[] headers = { "学号", "姓名","缺少周总结数","缺少周" };
		WriteExcelUtil we = new WriteExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		we.exportExcel(workbook, 0, "统计表", headers, data, out);
		workbook.write(out);
		out.close();					
 //_______________________________________end__________________________________________________________________________
    	
 //压缩文件_____________________________________________________________________________________________________
		
		//设置压缩包名名称
		String zipName="周总结"+".zip";
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