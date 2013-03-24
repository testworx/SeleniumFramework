package framework.controls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.BaseControl;

public class Alert extends BaseControl {

	public Alert(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	// TODO Add methods wrap the Selenium Alert methods.
}
