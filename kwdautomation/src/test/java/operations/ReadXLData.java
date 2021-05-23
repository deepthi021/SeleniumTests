package operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
public class ReadXLData {
	
	@Test
	public void Test() throws Exception {
		
		File file = new File("C:\\Users\\Deepthi\\eclipse-workspace\\kwdautomation\\src\\test\\java\\testscenarios\\TestCases.xlsx");
		
		FileInputStream stream = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(stream);
		
		XSSFSheet sheet = wb.getSheet("testcases");
		
		int rc = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		System.out.println("No. of rows found = "+rc);
		
		for(int i=0;i<rc;i++) {
			int cc = sheet.getRow(i).getLastCellNum();
			System.out.println("Row "+ i+ "th data is  : ");
			
			for(int j=0;j<cc;j++) {
				System.out.println(sheet.getRow(i).getCell(j));
			}
			System.out.println();
		}
		
		
	}

}
