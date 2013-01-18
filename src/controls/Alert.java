package controls;

import org.openqa.selenium.WebDriver;

public class Alert extends BaseControl {

	public Alert(WebDriver driver, String locator) {
		baseWebElement = findControl(driver, locator);
		System.out.println("WebElement found.");
	}
}
