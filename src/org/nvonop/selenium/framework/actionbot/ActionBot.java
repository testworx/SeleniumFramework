package org.nvonop.selenium.framework.actionbot;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.nvonop.selenium.framework.controls.BaseControl;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionBot extends BaseBot {

	private static final Logger LOGGER = Logger.getLogger(ActionBot.class
			.getName());
	
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
	 * Checks visibility
	 * 
	 * @param locator
	 */
	public boolean checkVisibility(By locator) {
		try {
			return findControl(locator).isDisplayed();	
		} catch (ElementNotVisibleException e) {
			LOGGER.log(Level.INFO,
					"ElementNotVisibleException in exists() method", e);
			return false;
		} catch (TimeoutException e) {
			LOGGER.log(Level.INFO, "TimeoutException in exists() method", e);
			return false;
		}
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
	public String readText(By locator) {
		return findControl(locator).getText();
	}
	
	/**
	 * Reads the specified attribute of an element.
	 * 
	 * @param locator
	 * @param attribute
	 */
	public String readAttribute(By locator, String attribute) {
		return findControl(locator).getAttribute("value");
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
