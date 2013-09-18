package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class comprises of functionality that would be used 
 * when interacting with a typical frame within a page.
 * @author nvonop
 *
 */
public class Frame extends BaseControl {

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public Frame(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	/**
	 * This method enables focus to switch to the frame when it is called. 
	 */
	public void switchToFrame() {
		String locatorSubString = getLocatorString(getLocator());

		getDriver().switchTo().frame(locatorSubString);
	}

	private static String getLocatorString(By locator) {
		String[] locatorArray = locator.toString().split("\\s");
		String locatorSubString = locatorArray[1];
		System.out.println("Parsing locator: " + locator.toString());
		System.out.println("Locator String: " + locatorArray[1]);

		return locatorSubString;
	}
}
