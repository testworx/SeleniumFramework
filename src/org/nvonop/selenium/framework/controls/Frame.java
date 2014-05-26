package org.nvonop.selenium.framework.controls;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class comprises of functionality that would be used when interacting
 * with a typical frame within a page.
 * 
 * @author nvonop
 * 
 */
public class Frame extends BaseControl {

	private static final Logger LOGGER = Logger
			.getLogger(Frame.class.getName());

	/**
	 * Constructor that takes a WebDriver object and By object. These are then
	 * set in the base class.
	 * 
	 * @param driver
	 * @param locator
	 */
	public Frame(WebDriver driver, By locator) {
		this.driver = driver;
		setLocator(locator);
	}

	/**
	 * This method enables focus to switch to the frame when it is called.
	 */
	public void switchToFrame() {
		String locatorSubString = getLocatorString(getLocator());

		driver.switchTo().frame(locatorSubString);
	}

	private static String getLocatorString(By locator) {
		String[] locatorArray = locator.toString().split("\\s");
		String locatorSubString = locatorArray[1];
		LOGGER.log(Level.FINEST, "Parsing locator: " + locator.toString());
		LOGGER.log(Level.FINEST, "Locator String: " + locatorArray[1]);

		return locatorSubString;
	}
}
