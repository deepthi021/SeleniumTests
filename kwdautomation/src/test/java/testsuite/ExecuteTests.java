package testsuite;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import operations.Action;
import utilities.ReadExcelFile;
import utilities.ReadObject;


// Deepthi's new code

public class ExecuteTests {
	WebDriver driver = null;
	
	String path = "C:\\Users\\Deepthi\\eclipse-workspace\\kwdautomation\\src\\test\\java";
	String driverPath =path+"\\drivers\\";	
	String testSuitePath=path + "\\testscenarios";
	String fileName="TestCases.xlsx";
	String propFile= path+"\\testscenarios\\objectrepos.properties";
	
	String keyword= null;
	
	@Test
	public void executeTestCases() throws Exception {
		
		ReadExcelFile readxl = new ReadExcelFile();		
		//XSSFWorkbook wb = new XSSFWorkbook(stream);		
		XSSFSheet sheet =readxl.readExcel(testSuitePath, fileName, "testcases");		
		int rc = sheet.getLastRowNum() - sheet.getFirstRowNum();		
		System.out.println("No. of rows found = "+rc);		
		for(int i=1;i<=rc;i++) {
			int cc = sheet.getRow(i).getLastCellNum();
			System.out.println("Row "+ i+ "th data is  : ");		

				String TestCaseID = sheet.getRow(i).getCell(0).toString();
				String TestCaseDesc = sheet.getRow(i).getCell(1).toString();
				String TestStepID = sheet.getRow(i).getCell(2).toString();
				String TestStepDesc = sheet.getRow(i).getCell(3).toString();
				String Keywords = sheet.getRow(i).getCell(4).toString();
				String LocatorName = sheet.getRow(i).getCell(5).toString();
				String LocatorType = sheet.getRow(i).getCell(6).toString();
				String Parm1 = sheet.getRow(i).getCell(7).toString();
				String Parm2 = sheet.getRow(i).getCell(8).toString();
				String Parm3 = sheet.getRow(i).getCell(9).toString();
				String Parm4 = sheet.getRow(i).getCell(10).toString();
				String Execute = sheet.getRow(i).getCell(11).toString();
				String Screenshots = sheet.getRow(i).getCell(12).toString();
				String Status = sheet.getRow(i).getCell(13).toString();		
				
				System.out.println(TestCaseID + " " + TestCaseDesc+ " " +  TestStepID+ " " + 	TestStepDesc+ " " + 
				Keywords+ " " +	LocatorName+ " " + 	LocatorType	+ " " + Parm1+ " " + 	Parm2	+ " " + Parm3+ " " + 
						Parm4+ " " + 	Execute+ " " + 	Screenshots+ " " + 	Status);				
				
				if(Execute.equalsIgnoreCase("yes")) {
					//Status="SKIP";
					Action act = new Action(driver);
					ReadObject obj = new ReadObject();
					Properties allObjects = obj.getObjectProperty(propFile);					
					String res=act.UIOperations(Keywords,LocatorName,LocatorType,Parm1,Parm2,Parm3,Parm4,allObjects);
					System.out.println(res);	
					}
			}
			System.out.println();
		}	

}
