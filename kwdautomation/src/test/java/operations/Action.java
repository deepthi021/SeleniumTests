package operations;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utilities.ReadObject;

public class Action {	
	WebDriver driver;
	String result="";
	String path = "C:\\Users\\Deepthi\\eclipse-workspace\\kwdautomation\\src\\test\\java";
	String driverPath =path+"\\drivers\\";


	public Action(WebDriver dr) {
		this.driver =dr; 
	}
	
	public String UIOperations(String kw,String locName,String LocType,String eVal1,String eVal2,String eVal3,String eVal4, Properties p) {
	
		
		switch(kw.toUpperCase()) {			

		case "APPOPEN":
			try {
			OpenApp(driverPath, eVal1, eVal2, eVal3, eVal4);
			result="Pass";
			} catch (Exception e) {				
				e.printStackTrace();
				System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
				result="Fail";
			}
		break;
		
		case "APPCLOSE":
			try {
			CloseApp();
			result="Pass";
			} catch (Exception e) {				
				e.printStackTrace();
				System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
				result="Fail";
			}
		break;
		
		case "CLICK":
			try {
				//System.out.println(this.getObject(p, locName, LocType));
			Thread.sleep(3000);
				WebElement eleClick = driver.findElement(By.linkText("Log in"));	
			//WebElement eleClick = driver.findElement(this.getObject(p, locName, LocType));
			boolean flag = eleClick.isDisplayed();
			if(flag=true) {
				eleClick.click();
				result="Pass";
			}
			else{
				result="Fail";
			}
			} catch (Exception e) {				
				e.printStackTrace();
				System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
				result="Fail";
			}
		break;
		
		case "WAIT":
			try {
				waitCustomized(Long.parseLong(eVal1));
				result="Pass";
			} catch (Exception e) {				
				e.printStackTrace();
				System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
				result="Fail";
			}
			
		break;
		
		case "TYPE":
			try {
				WebElement eleType = driver.findElement(this.getObject(p, locName, LocType));
				boolean flag = eleType.isDisplayed();
				if(flag=true) {
					eleType.clear();
					eleType.sendKeys(eVal1);
					result="Pass";
				}
				else{
					result="Fail";
				}
				} catch (Exception e) {				
					e.printStackTrace();
					System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
					result="Fail";
				}
		break;
		
		case "ASSERT":
			try {
				WebElement eleType = driver.findElement(this.getObject(p, locName, LocType));
				boolean flag = eleType.isDisplayed();
				if(flag=Boolean.parseBoolean(eVal1)) {					
					result="Pass";
				}
				else{
					result="Fail";
				}
				} catch (Exception e) {				
					e.printStackTrace();
					System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
					result="Fail";
				}
		break;
		default:
		break;
		}
		return result;
		
	}
	
	
	
	
	
	
	
	//APPOPEN
	public WebDriver OpenApp(String path, String browser, String os, String osVer, String url) {
	
		
//		 System.out.println("This is: " + browser);		 
//		 System.out.println("This is: " + os);
//		 System.out.println("This is: " + osVer);	
	try {

		System.out.println("Opening the Application");
		//String br =null;

		// Optional. If not specified, WebDriver searches the PATH for Chromedriver.
		if( browser.toLowerCase().contentEquals("chrome")) {
				System.setProperty("webdriver.chrome.driver",path+
				"chromedriver.exe");
				driver = new ChromeDriver();
		} else if(browser.toLowerCase().contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Program Files\\Internet Explorer\\iexplore.exe");
			driver = new InternetExplorerDriver();				
		} 
		else if(browser.toLowerCase().contentEquals("firefox")) {
			System.setProperty("webdriver.gecko.driver", path+"geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.toLowerCase().contentEquals("edge")) {
			System.setProperty("webdriver.edge.driver",path+
					"msedgedriver.exe");
			driver = new EdgeDriver();
		}else {
			System.out.println("This type of driver is not found");
		}
		
		
		driver.manage().window().maximize();

		driver.get(url);

		Thread.sleep(5000);
			
	} catch (Exception e) {
		System.out.println(e.getMessage());
		driver.quit();
	}
	return driver;
	}
	
	//APPCLOSE
	public void CloseApp() {
		
		try {
			if (this.driver != null) {
				System.out.println("Closing the Application");
				this.driver.quit();
			} else {
				System.out.println("Driver not available !!!");
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		//return driver;
	}
	
	
	//WAIT
	public void waitCustomized(long waittime) throws InterruptedException {
		Thread.sleep(waittime);
	}
	

	private By getObject(Properties p, String objectName, String objectType) throws Exception {
		
		//Find by XPATH
		if(objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(p.getProperty(objectName));
		}
		
		//Find by ID
		else if(objectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(objectName));
		}
		//Find by NAME
		else if(objectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(objectName));
		}
		
		//Find by CSS NAME
		else if(objectType.equalsIgnoreCase("CLASS")) {
			return By.className(p.getProperty(objectName));
		}
		
		//Find by LINK
		else if(objectType.equalsIgnoreCase("LINK")) {
			return By.linkText(p.getProperty(objectName));
		}
		//Find by PARTIAL LINK
		else if(objectType.equalsIgnoreCase("PARTIALLINK")) {
			return By.partialLinkText(p.getProperty(objectName));
		}
		else {
			throw new Exception("Element Object type not found");
		}
						
	}

	
	
}
