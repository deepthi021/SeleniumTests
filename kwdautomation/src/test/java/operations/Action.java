package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utilities.GetScreenShot;
import utilities.Utility;

public class Action {	
	WebDriver driver;
	String result="";
	
	
	public Action(WebDriver dr) {
		this.driver =dr; 
	}
	
	public String UIOperations(String kw,String locName,String LocType,String eVal1,String eVal2,String eVal3,String eVal4, Properties p) throws SQLException {
	
		Utility util= new Utility(driver);
		GetScreenShot getScreen = new GetScreenShot(driver);
		String passtimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String path1 = System.getProperty("user.dir")+ "\\Reports\\Screenshots\\";
		String path2 = passtimeStamp + "_" + kw + ".jpg";
		
		
		switch(kw.toUpperCase()) {					
		case "CLICK":
			try {
				//System.out.println(this.getObject(p, locName, LocType));
			//WebElement eleClick = driver.findElement(By.linkText("Log in"));	
			WebElement eleClick = driver.findElement(this.getObject(p, locName, LocType));
			boolean flag = eleClick.isDisplayed();
			if(flag=true) {
				util.highlightElement(eleClick);
				eleClick.click();
				result="Pass";					
				//System.out.println("Image Location path : >>>" + path1 + path2);
				String imgLoc1 = getScreen.screencapture(path1, path2);
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
				Thread.sleep(3000);
				result="Pass";
			} catch (Exception e) {				
				e.printStackTrace();
				System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
				result="Fail";
			}
			
		break;
		
		case "WAIT-MED":
			try {
				Thread.sleep(10000);
				result="Pass";
			} catch (Exception e) {				
				e.printStackTrace();
				System.out.println("Unable to perform action: "+kw.toUpperCase()+ e);
				result="Fail";
			}
			
		break;
		case "WAIT-HIGH":
			try {
				Thread.sleep(20000);
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
					util.highlightElement(eleType);
					eleType.clear();
					eleType.sendKeys(eVal1);
					result="Pass";
					String imgLoc1 = getScreen.screencapture(path1, path2);
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
				WebElement eleAsserte = driver.findElement(this.getObject(p, locName, LocType));
				boolean flag = eleAsserte.isDisplayed();
				util.highlightElement(eleAsserte);
				if(flag=Boolean.parseBoolean(eVal1)) {					
					result="Pass";
					String imgLoc1 = getScreen.screencapture(path1, path2);
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
		case "DBTEST":
				Connection c=null;
				Statement stmt= null;

	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/demo",
	            "postgres", "admin");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      
	      
	      stmt = c.createStatement();
	         String sql = eVal1;        
	         
	         ResultSet rs = stmt.executeQuery(sql);
	           
	           while ( rs.next() ) {
	              int id = rs.getInt("user_id");
	              String  name = rs.getString("username");
	              String pwd  = rs.getString("password");
	              String  email = rs.getString("email");
	              
	              System.out.println( "USER ID = " + id );
	              System.out.println( "USER NAME = " + name );	
	              
	              rs.close();
	              stmt.close();
	              c.close();
	              

	           } 
		break;
		
		
		default:
		break;
		}
		return result;
		
	}
		
	//APPOPEN
	public void OpenApp(String path, String browser, String os, String osVer, String url) {
	
		
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
	}
	
	//WAIT
	public void waitCustomized(long waittime) throws InterruptedException {
		Thread.sleep(waittime);
	}
	
	// Get Object Properties By options
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
