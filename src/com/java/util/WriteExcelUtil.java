package com.java.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

public class WriteExcelUtil {
	public void exportExcel(HSSFWorkbook workbook, int sheetNum,
			String sheetTitle, String[] headers, List<List<String>> result,
			OutputStream out) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell((short) i);
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		// 遍历集合数据，产生数据行
		if (result != null) {
			int index = 1;
			for (List<String> m : result) {
				row = sheet.createRow(index);
				int cellIndex = 0;
				for (String str : m) {
					HSSFCell cell = row.createCell((short) cellIndex);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(str.toString());
					cellIndex++;
				}
				index++;
			}
		}
	}
	public void exportExcelWithTitel(HSSFWorkbook workbook, int sheetNum,
			String sheetTitle, String[] headers, List<List<String>> result,
			OutputStream out,String title) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);
		//产生title行
		HSSFRow rowTitle = sheet.createRow(0);
		CellRangeAddress cra=new CellRangeAddress(0, 1, 0, headers.length-1);
		sheet.addMergedRegion(cra);
		HSSFRow rowTitlte = sheet.createRow(0);  
        HSSFCellStyle titleStyle=workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font2 = workbook.createFont();    
        font2.setFontName("仿宋_GB2312");    
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
        font2.setFontHeightInPoints((short) 18); 
               
        titleStyle.setFont(font2);//选择需要用到的字体格式    
		HSSFCell cellTitle = rowTitlte.createCell(0);  
		cellTitle.setCellStyle(titleStyle);
          
		cellTitle.setCellValue(title);  
		// 产生表格标题行
		HSSFRow row = sheet.createRow(3);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell((short) i);
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		// 遍历集合数据，产生数据行
		if (result != null) {
			int index = 4;
			for (List<String> m : result) {
				row = sheet.createRow(index);
				int cellIndex = 0;
				for (String str : m) {
					HSSFCell cell = row.createCell((short) cellIndex);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(str.toString());
					cellIndex++;
				}
				index++;
			}
		}
 }}
	 