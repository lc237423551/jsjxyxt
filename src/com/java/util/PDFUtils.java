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
public class PDFUtils {
	public static boolean useLoop(String[] arr, String targetValue) {//判断数组是否存在某字符串
		  for(String s: arr){
		    if(s.equals(targetValue))
		      return true;
		  }
		  return false;
		}
	public void write(String sno,String sname,List<TrainInfo> tlist,List<Weekwork> weeklist,String path ,String unneed[]) throws DocumentException, IOException{
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
	         for(int j=0;j<18;j++){
	        	  int jj=j+1;
	        	  String s=String.valueOf(jj);
	        	  boolean isContains = useLoop(unneed,s);
	        	   if(isContains==true){
	        	   }else{
	        		    document.add(new Paragraph());

		   	            Paragraph par1 = new Paragraph("专业实训周工作日志",font20);
		   	            par1.setAlignment(Element.ALIGN_CENTER);
		   	            par1.setLeading(40f);//设置行间距//设置上面空白宽度
		   	            document.add(par1);
		   	            
		   	            Paragraph par2 = new Paragraph("              实训题目:     "+tlist.get(j).getSubjectname().getName(),font16);
		   	            par2.setSpacingBefore(25f);//设置上面空白宽度  
		   	            document.add(par2);
		   	            Paragraph par3 = new Paragraph("              学号:     "+sno+"      "+"姓名:     "+sname+"     实训第  "+(j+1)+" 周",font16);
		   	            par3.add("");
		   	            par3.setSpacingBefore(10f);//设置上面空白宽度  
		   	            document.add(par3);
		   	            Paragraph par4 = new Paragraph("              实习单位:     "+tlist.get(j).getUnit().getUname(),font16);
		   	            par4.setSpacingBefore(10f);//设置上面空白宽度  
		   	            document.add(par4);
		   	            
	
		   	            PdfPTable table = new PdfPTable(2); //2列
		   	            table.setSpacingBefore(30f);
		   	            table.setTotalWidth(400);
		   	            table.setLockedWidth(true);
		   	            table.setTotalWidth(new float[]{ 50,350});
		   	            String thisweek=weeklist.get(j).getSummary();
		                   if(thisweek == null || thisweek.length() <= 0){
		                  	 thisweek=" ";
		                   }
		                   String deal=weeklist.get(j).getDeal();
		                   if(deal == null || deal.length() <= 0){
		                  	 deal=" ";
		                   }
		                   String advice=weeklist.get(j).getAdvise();
		                   if(advice == null || advice.length() <= 0){
		                  	 advice=" ";
		                   }
		                   String next=weeklist.get(j).getArrange();
		                   if(next == null || next.length() <= 0){
		                  	 next=" ";
		                   }
		                   String teacher=weeklist.get(j).getEvaluation();
		                   if(teacher == null || teacher.length() <= 0){
		                  	 teacher=" ";
		                   }
			   	            PdfPCell cell11 = new PdfPCell(new Paragraph("本周工作总结", font12));cell11.setFixedHeight(130); 
			   	            PdfPCell cell12 = new PdfPCell(new Paragraph(thisweek, font12));cell12.setFixedHeight(130); 
			   	            PdfPCell cell21 = new PdfPCell(new Paragraph("下周工作安排", font12));cell21.setFixedHeight(110); 
			   	            PdfPCell cell22 = new PdfPCell(new Paragraph(next, font12));cell22.setFixedHeight(110); 
			   	            PdfPCell cell31 = new PdfPCell(new Paragraph("老师对问题的处理", font12));cell31.setFixedHeight(90); 
			   	            PdfPCell cell32 = new PdfPCell(new Paragraph(deal, font12));cell32.setFixedHeight(90); 
			   	            PdfPCell cell41 = new PdfPCell(new Paragraph("工作建议", font12));cell41.setFixedHeight(88); 
			   	            PdfPCell cell42 = new PdfPCell(new Paragraph(advice, font12));cell42.setFixedHeight(88); 
			   	            PdfPCell cell51 = new PdfPCell(new Paragraph("本周考核评价", font12));cell51.setFixedHeight(80); 
			   	            PdfPCell cell52 = new PdfPCell(new Paragraph(teacher, font12));cell52.setFixedHeight(80); 
			   	            table.addCell(cell11);
			   	            table.addCell(cell12);
			   	            table.addCell(cell21);
			   	            table.addCell(cell22);
			   	            table.addCell(cell31);
			   	            table.addCell(cell32);
			   	            table.addCell(cell41);
			   	            table.addCell(cell42);
			   	            table.addCell(cell51);
			   	            table.addCell(cell52);
			   	            document.add(table);
		        	   }

	        }
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
