package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility {
	public WebDriver driver;
	 
	public Utility(WebDriver driver) {
		this.driver = driver;
	}
	
	public void highlightElement(WebElement element) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: orange; border: 2px solid RED;");
			Thread.sleep(200);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

}
