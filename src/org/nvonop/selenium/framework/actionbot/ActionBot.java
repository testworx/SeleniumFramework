package org.nvonop.selenium.framework.actionbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionBot extends BaseBot {

	public ActionBot(WebDriver driver) {
		super.driver = driver;
	}

	/**
	 * Clicks the element.
	 * 
	 * @param locator
	 */
	public void click(By locator) {
		findControl(locator).click();
	}

	/**
	 * Submits an element.
	 * 
	 * @param locator
	 */
	public void submit(By locator) {
		findControl(locator).submit();
	}

	/**
	 * Reads the text value of an element.
	 * 
	 * @param locator
	 */
	public void read(By locator) {
		findControl(locator).getText();
	}

	/**
	 * Type something into an input field. WebDriver doesn't normally clear
	 * these before typing, so this method does that first. It also sends a
	 * return key to move the focus out of the element.
	 * 
	 * @param locator
	 */
	public void type(By locator, String text) {
		WebElement element = findControl(locator);
		element.sendKeys(text);
	}
}
