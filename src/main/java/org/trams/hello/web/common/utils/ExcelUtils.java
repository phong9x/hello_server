package org.trams.hello.web.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.util.ResourceUtils;
import org.trams.hello.web.common.ApplicationDefine;

public class ExcelUtils {
	
	private static HSSFWorkbook template_workbook;
	
	public static void main(String[] args) throws IOException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new Date());
		OutputStream out;
		try {
			FileInputStream fileInput = new FileInputStream(ResourceUtils.getFile("classpath:excel-template/MemberStatistics.xls"));
			template_workbook = new HSSFWorkbook(fileInput);
			HSSFSheet sheet = template_workbook.getSheetAt(0);
			int columnIndex = 1;
			int rowIndex = 0;
			if (today != null && !today.equals("")) {
				rowIndex++;
			}
			
			for (int i = 0; i < 5; i++) {
				System.out.println("row: "+i);
				if(sheet.getRow(i)== null){
					rowIndex = i;
					System.out.println("row begin: "+ rowIndex);
					break;
				}
			}
			
			//set header
			HSSFRow rowhead = sheet.createRow(rowIndex);
			columnIndex = 0;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void createExcelFile(HttpServletRequest request, HttpServletResponse response, String title,
			List<String> header, List<List<Object>> data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + today + ".xls" + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out;
		try {
			out = response.getOutputStream();
			HSSFWorkbook template_workbook = new HSSFWorkbook();
			HSSFSheet sheet = template_workbook.createSheet("Sheet 1");
			int columnIndex = 1;
			int rowIndex = 0;
			if (title != null && !title.equals("")) {
				rowIndex++;
			}
			
			HSSFRow rowhead = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (int i = 0; i < header.size(); i++) {
				rowhead.createCell(i).setCellValue(header.get(i));
				sheet.autoSizeColumn(i);
			}
			rowIndex++;
			
			for (List<Object> listStr : data) {
				HSSFRow row = sheet.createRow(rowIndex);
				columnIndex = 0;
				for (Object rowItem : listStr) {
					row.createCell(columnIndex).setCellValue(String.valueOf(rowItem));
					columnIndex++;
				}
				rowIndex++;
			}

			try {
				template_workbook.write(out);
				out.flush();
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createExcelFileUseTemplate(HttpServletRequest request, HttpServletResponse response, ApplicationDefine.ExcelTemplate excelTemplete,
			List<String> header, List<List<Object>> data) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + today + ".xls" + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out;
		try {
			out = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(ResourceUtils.getFile("classpath:"+excelTemplete.getUrl()));
			template_workbook = new HSSFWorkbook(fileInput);
			HSSFSheet sheet = template_workbook.getSheetAt(0);
            
			int columnIndex = 1;
			int rowIndex = 0;

			for (int i = 0; i < 5; i++) {
				if(sheet.getRow(i)== null){
					rowIndex = i;
					break;
				}
			}
			
			//set header
			HSSFRow rowhead = sheet.createRow(rowIndex);
			columnIndex = 0;
			if(header != null){
				for (int i = 0; i < header.size(); i++) {
					rowhead.createCell(i).setCellValue(header.get(i));
				}
			}
			
			
			//set data
			for (List<Object> listStr : data) {
				HSSFRow row = sheet.createRow(rowIndex);
				columnIndex = 0;
				for (Object rowItem : listStr) {
					row.createCell(columnIndex).setCellValue(String.valueOf(rowItem));
					//sheet.autoSizeColumn(columnIndex);
					columnIndex++;
				}
				rowIndex++;
			}

			try {
				template_workbook.write(out);
				out.flush();
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createExcelFileUseTemplateHeaderCustom(HttpServletRequest request, HttpServletResponse response, ApplicationDefine.ExcelTemplate excelTemplete,
			List<Object> header, List<List<Object>> data) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(new Date());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + today + ".xls" + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out;
		try {
			out = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(ResourceUtils.getFile("classpath:"+excelTemplete.getUrl()));
			template_workbook = new HSSFWorkbook(fileInput);
			HSSFSheet sheet = template_workbook.getSheetAt(0);
			
			final HSSFCellStyle style = template_workbook.createCellStyle();
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);             
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);            
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);              
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
			int columnIndex = 1;
			int rowIndex = 0;
			Iterator<Object> iteratorHeader = header.iterator();
			for (int i = 0; i < 5; i++) {
				if(sheet.getRow(i)== null){
					rowIndex = i;
					break;
				}else{
					Row r = sheet.getRow(i);
					int maxCell = sheet.getRow(i).getPhysicalNumberOfCells();
					for (int j = 0; j < maxCell; j++) {
						if(r.getCell(j).getStringCellValue().equals("$data")){
							while (iteratorHeader.hasNext()) {
								r.getCell(j).setCellValue(String.valueOf(iteratorHeader.next()));
								iteratorHeader.remove();
								break;
							}
						}
					}
				}
			}
			//set data
			for (List<Object> listStr : data) {
				HSSFRow row = sheet.createRow(rowIndex);
				columnIndex = 0;
				for (Object rowItem : listStr) {
					Cell c = row.createCell(columnIndex);
					c.setCellValue(String.valueOf(rowItem));
					c.setCellStyle(style);
					columnIndex++;
				}
				rowIndex++;
			}

			try {
				template_workbook.write(out);
				out.flush();
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
