package com.java.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.entity.TrainInfo;
import com.java.entity.Weekwork;
public class RenwushuUtil {
	public void write(TrainInfo trainInfo,String path) throws DocumentException, IOException{
		 BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	        Font font20 = new Font(bfChinese, 20, Font.BOLD);//字体设置
	        Font font16 = new Font(bfChinese, 16, Font.NORMAL);
	        Font font12 = new Font(bfChinese, 12, Font.NORMAL);
	        Font font10 = new Font(bfChinese, 10, Font.NORMAL);
	        Rectangle rectPageSize = new Rectangle(PageSize.A4);
	        Document document = new Document(rectPageSize,50,50,50,50);
	        try
	        {
	            PdfWriter.getInstance(document,new FileOutputStream(path));
	            document.open();
	        
	        		    document.add(new Paragraph());

		   	            Paragraph par1 = new Paragraph("山东建筑大学专业实训任务书",font20);
		   	            par1.setAlignment(Element.ALIGN_CENTER);
		   	            par1.setLeading(40f);//设置行间距//设置上面空白宽度
		   	            document.add(par1);
		   	            
		   	            Paragraph par2 = new Paragraph("              实训题目:     "+trainInfo.getSubjectname().getName()+"               指导教师:"+trainInfo.getTeacher().getTname(),font16);
		   	            par2.setSpacingBefore(25f);//设置上面空白宽度  
		   	            //par2.
		   	            document.add(par2);
		   	            Paragraph par3 = new Paragraph("              学号:     "+trainInfo.getSno()+"      "+"姓名:     "+trainInfo.getStudent().getSname()+"     班级:  "+trainInfo.getStudent().getCla().getCname(),font16);
		   	            par3.add("");
		   	            par3.setSpacingBefore(10f);//设置上面空白宽度  
		   	            document.add(par3);
//		   	            Paragraph par4 = new Paragraph("              实习单位:     "+tlist.get(j).getUnit().getUname(),font16);
//		   	            par4.setSpacingBefore(10f);//设置上面空白宽度  
//		   	            document.add(par4);
		   	            
	
		   	            PdfPTable table = new PdfPTable(2); //2列
		   	            table.setSpacingBefore(30f);
		   	          
		   	            table.setLockedWidth(true);
		   	            table.setTotalWidth(new float[]{ 50,450});
		   	            StringUtil su=new StringUtil();
		   	            String skill=trainInfo.getSubjectname().getSkill();
		   	            if(su.isEmpty(skill)){skill=" ";}
		   	            String content=trainInfo.getSubjectname().getContent();
		   	            if(su.isEmpty(content)) content="";
		   	            String plan=trainInfo.getSubjectname().getPlan();
		   	            if(su.isEmpty(plan)) plan="";
		   	            String requirement=trainInfo.getSubjectname().getRequirement();
		   	            if(su.isEmpty(requirement)) requirement="";
			   	            PdfPCell cell11 = new PdfPCell(new Paragraph("已知技术参数和实训要求", font12));cell11.setMinimumHeight(200);
			   	            PdfPCell cell12 = new PdfPCell(new Paragraph(skill, font12));cell12.setMinimumHeight(200); 
			   	            PdfPCell cell21 = new PdfPCell(new Paragraph("实训内容与步骤", font12));cell21.setMinimumHeight(200); 
			   	            PdfPCell cell22 = new PdfPCell(new Paragraph(content, font12));cell22.setMinimumHeight(200); 
			   	            PdfPCell cell31 = new PdfPCell(new Paragraph("实训工作计划与进度安排(按2周填写)", font12));cell31.setMinimumHeight(200); 
			   	            PdfPCell cell32 = new PdfPCell(new Paragraph(plan, font12));cell32.setMinimumHeight(200); 
			   	            PdfPCell cell41 = new PdfPCell(new Paragraph("实训考核要求", font12));cell41.setMinimumHeight(200); 
			   	            PdfPCell cell42 = new PdfPCell(new Paragraph(requirement, font12));cell42.setMinimumHeight(200); 
			   	            table.addCell(cell11);
			   	            cell12.setPadding(10);
			   	            cell22.setPadding(10);
			   	            cell32.setPadding(10);
			   	            cell42.setPadding(10);
			   	            table.addCell(cell12);
			   	            table.addCell(cell21);
			   	            table.addCell(cell22);
			   	            table.addCell(cell31);
			   	            table.addCell(cell32);
			   	            table.addCell(cell41);
			   	            table.addCell(cell42);
			   	           
			   	            document.add(table);
	        }

	   
	        catch (DocumentException de)
	        {
	            System.err.println(de.getMessage());
	        }
	        catch (IOException ioe)
	        {
	            System.err.println(ioe.getMessage());
	        }
	        //关闭document
	        document.close();
	        System.out.println("生成Pdf成功！");
		
	}
}