package testsuite;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import operations.Action;
import utilities.ReadExcelFile;
import utilities.ReadObject;

public class ExecuteTests {
	WebDriver driver;
	
	//String path = "C:\\Users\\Deepthi\\eclipse-workspace\\kwdautomation\\src\\test\\java";	
	String path =System.getProperty("user.dir") + "\\src\\test\\java\\";
	String driverPath =path+"drivers\\";	
	String testSuitePath=path + "testscenarios";
	String fileName="TestCases.xlsx";
	String propFile= path+"testscenarios\\objectrepos.properties";	
	String keyword= null;
	String browser="chrome";
	String os="windows";
	String osVer="10.0";
	String url = "http://demowebshop.tricentis.com/";
	Action act = new Action(driver);
	PrintStream consoleOut;
	
	@BeforeClass
	public void setUp()
	{		
		act.OpenApp(driverPath, browser, os, osVer, url);
	}
	@Test
	public void executeTestCases() throws Exception {
		System.out.println();
		consoleOut = new PrintStream(new File(
				System.getProperty("user.dir")+"\\Reports\\" + os + "_" + osVer + "_"
						+ browser + "_TestResults" + "_ConsoleLog.txt"));
		System.setOut(consoleOut);
		System.out.println(
				"**************************** Printing Console Logs Starts Here !!! **************************");
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
				String Status = sheet.getRow(i).getCell(12).toString();		
				
				System.out.println(TestCaseID + " " + TestCaseDesc+ " " +  TestStepID+ " " + 	TestStepDesc+ " " + 
				Keywords+ " " +	LocatorName+ " " + 	LocatorType	+ " " + Parm1+ " " + 	Parm2	+ " " + Parm3+ " " + 
						Parm4+ " " + 	Execute+ " " + 	Status);				
				
				if(Execute.equalsIgnoreCase("yes")) {
					ReadObject obj = new ReadObject();
					Properties allObjects = obj.getObjectProperty(propFile);					
					String res=act.UIOperations(Keywords,LocatorName,LocatorType,Parm1,Parm2,Parm3,Parm4,allObjects);
					System.out.println(res);						
					}
				System.out.println();
			}			
			System.out
			.println("**************************** Printing Console Logs Ends Here !!! **************************");
		}	

	@AfterClass
	public void tearDown() throws Exception {
		act.CloseApp();
	}
}
