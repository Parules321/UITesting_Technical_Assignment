package com.sauceDemo.utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fi;

	public static XSSFWorkbook wb;

	public static XSSFSheet ws;

	public static XSSFRow row;

	public static XSSFCell cell;

	public static int getRowCount(String xFile, String sheetName) throws IOException {
		fi = new FileInputStream(xFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}

	public static int getColumnCount(String xFile, String sheetName, int lastRowNo) throws IOException {
		fi = new FileInputStream(xFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(lastRowNo);
		int columnCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return columnCount;
	}

	public static String getCellValue(String xFile, String sheetName, int rowNo, int colNo) throws IOException {
		String data = "";
		fi = new FileInputStream(xFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNo);
		cell = row.getCell(colNo);
		data = new DataFormatter().formatCellValue(cell);
		wb.close();
		fi.close();
		return data;
	}
}
