package org.nvonop.selenium.framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class provides access to functionality for Alert popups.  
 * IT IS STILL A WORK IN PROGRESS.
 * @author nvonop
 *
 */
public class Alert extends BaseControl {

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public Alert(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	// TODO Add methods wrap the Selenium Alert methods.
}
