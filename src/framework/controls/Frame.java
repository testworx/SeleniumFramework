package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Frame extends BaseControl {

	public Frame(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}
	
//	public void switchTo() {
//		TestHelper.switchToFrame(getLocator());
//	}
	
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
