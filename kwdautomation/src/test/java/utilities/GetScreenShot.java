package utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class GetScreenShot {
	public WebDriver driver;
	String path = System.getProperty("user.dir")+ "\\Reports\\";

	public GetScreenShot(WebDriver driver) {
		this.driver = driver;
	}

	public String screencapture(String filepath1, String filepath2) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String dest = filepath2;

		String finalDest = filepath1 + filepath2;
		System.out.println("ScreenShot file name: " + finalDest);

		File destination = new File(finalDest);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dest;
	}
}
