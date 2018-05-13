package com.java.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.java.entity.Student;


public class TRemarkUtil {
	public void write(String path, List<Student> list,int year,int month,int day) throws IOException{
		 XWPFDocument doc = new XWPFDocument();  
		  //页面开始
		      for(int i=0;i<list.size();i++){
			        XWPFParagraph p1 = doc.createParagraph();  
			        p1.setAlignment(ParagraphAlignment.CENTER);
				        XWPFRun r1 = p1.createRun();  
				        r1.setBold(true);  
				        r1.setText("山东建筑大学计算机科学与技术学院");  
				        r1.setFontFamily("宋体");  
				        r1.setFontSize(14);  //字体大小
				        r1.setTextPosition(20);  //间距
				        r1.addBreak();  
				        XWPFRun r2 = p1.createRun();  
				        r2.setBold(true);  
				        r2.setText("实训指导教师评语");  
				        r2.setBold(true);  
				        r2.setFontFamily("宋体");  
				        r2.setFontSize(16);  //字体大小
				        r2.setTextPosition(30);  //间距
				        r2.addBreak(); 
				        XWPFRun r3 = p1.createRun();  
				        r3.setBold(true);  
				        r3.setText("班级:"+list.get(i).getCla().getCname()+"    学号:"+list.get(i).getSname()+"   姓名:"+list.get(i).getSno());  
				        r3.setFontFamily("宋体");  
				        r3.setFontSize(12);  //字体大小
				        r3.setTextPosition(16);  //间距
				        r3.addBreak();  
				        
				        XWPFRun r4 = p1.createRun();  
				        r4.setBold(true);  
				        r4.setText("--------------------------------------------------------------------");  
				        r4.setFontFamily("宋体");  
				        r4.setFontSize(12);  //字体大小
				        r4.setTextPosition(7);  //间距
				       
				        XWPFParagraph p2 = doc.createParagraph();  
				        p2.setAlignment(ParagraphAlignment.LEFT);
				        XWPFRun r5 = p2.createRun();  
				        r5.setBold(true);  
				        r5.setText("指导教师评语");  
				        r5.setFontFamily("宋体");  
				        r5.setFontSize(12);  //字体大小
				        r5.setTextPosition(7);  //间距
				        XWPFRun r6 = p2.createRun();  
				        r6.setText("（包括工作态度，遵守纪律；基本理论、知识、技能；独立工作能力和分析解决问题的能力；完成任务情况及水平）：");  
				        r6.setFontFamily("宋体");  
				        r6.setFontSize(12);  //字体大小
				        r6.setTextPosition(7);  //间距
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();
				        r6.addBreak();//学生成绩
				        XWPFRun r7 = p2.createRun();  
				        r7.setBold(true);  
				        r7.setText("学生成绩");  
				        r7.setFontFamily("宋体");  
				        r7.setFontSize(12);  //字体大小
				        r7.setTextPosition(7);  //间距
				        XWPFRun r8 = p2.createRun();  
				        r8.setText("（百分制）：");  
				        r8.setFontFamily("宋体");  
				        r8.setFontSize(12);  //字体大小
				        r8.setTextPosition(7);  //间距
				        r8.addBreak();
				        r8.addBreak();
				        r8.addBreak();
				        r8.addBreak();
				        r8.addBreak();
				        r8.addBreak();
				        r8.addBreak();
				        //指导教师签名：            ${year}年${month} 月${day} 日
				        
				        XWPFRun r9 = p2.createRun();  
				        r9.setText("             指导教师签名:       "+"            "+year+"年"+month+"月"+day+"日");  
				        r9.setFontFamily("宋体");  
				        r9.setFontSize(12);  //字体大小
				        r9.setTextPosition(7);  //间距
				        XWPFParagraph p3 = doc.createParagraph();
				        p3.setPageBreak(true);
				                   
				        
		      }
		        FileOutputStream out = new FileOutputStream(path);    
		        doc.write(out);  
		        out.close();  
	}
}
