package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelUtil {
	
	public static final String TESTDATA_SHEET_PATH="C:\\Users\\sandy\\eclipse\\March2023POMSeries\\src\\test\\resources\\testData\\OpenCart_TestData.xlsx";
	
	public static Workbook book;
	public static Sheet sheet;

public static Object[][] getTestData(String sheetName) {
		
		System.out.println("reading the data from sheet: " + sheetName);
		
		Object data[][] = null;
		
		try {
			System.out.println(TESTDATA_SHEET_PATH);
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i < sheet.getLastRowNum(); i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data;
		
	}
	
	
	
	
	

}
