package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	XSSFWorkbook wb = null;	
	public XSSFSheet readExcel(String filePath, String fileName, String sheetName) throws IOException {
		
		
		File file = new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
				
		String flExtn = fileName.substring(fileName.indexOf("."));
		
		if(flExtn.equals(".xlsx")){
			wb = new XSSFWorkbook(inputStream);			
		}
		else if(flExtn.equals(".xls")){
			wb = new XSSFWorkbook(inputStream);			
		}
		
		XSSFSheet wbsh = wb.getSheet(sheetName);
		
		return wbsh;
		
	}

}
