package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Alert extends BaseControl {

	public Alert(WebDriver driver, By locator) {
		baseWebElement = findControl(driver, locator);
		System.out.println("WebElement found.");
	}
	
	//TODO Add methods wrap the Selenium Alert methods.
}
