package org.nvonop.selenium.framework.controls;

import org.nvonop.selenium.framework.controls.interfaces.Clickable;
import org.nvonop.selenium.framework.controls.interfaces.Readable;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


/**
 * This class comprises of functionality that would be used 
 * when interacting with a typical select box control.
 * @author nvonop
 *
 */
public class SelectBox extends BaseControl implements Clickable, Readable {

	Select selectBox;

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public SelectBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);

	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public Object read() {
		Select selectBox = new Select(getUnderlyingWebElement());
		WebElement selectedOption = null;
		try { 
			selectedOption = selectBox.getFirstSelectedOption();
		} catch (StaleElementReferenceException e) {
			read();
		}
		return selectedOption.getText();
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Clickable#click()
	 */
	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	/**
	 * This method enables a select box option to be selected by it's value.
	 * @param option The String value of the option to be selected
	 */
	public void selectOptionByValue(String option) {
		selectBox = new Select(getUnderlyingWebElement());
		
		try {
			selectBox.selectByValue(option);
		} catch (StaleElementReferenceException e) {
			selectOptionByValue(option);
		}
	}

	/**
	 * This method enables a select box option to be selected by it's visible text.
	 * @param option The String value of the visible text for the option
	 */
	public void selectOptionByVisibleText(String option) {
		selectBox = new Select(getUnderlyingWebElement());
		
		try {
			selectBox.selectByVisibleText(option);
		} catch (StaleElementReferenceException e) {
			selectOptionByVisibleText(option);
		}
	}

	/**
	 * This method enables a select box option to be selected by it's index.
	 * @param index The index of the option to be selected
	 */
	public void selectOptionByIndex(int index) {
		selectBox = new Select(getUnderlyingWebElement());
		try {
			selectBox.selectByIndex(index);
		} catch (StaleElementReferenceException e) {
			selectOptionByIndex(index);
		}
	}
}
