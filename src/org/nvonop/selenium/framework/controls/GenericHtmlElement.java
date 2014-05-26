package org.nvonop.selenium.framework.controls;

import org.nvonop.selenium.framework.controls.interfaces.Clickable;
import org.nvonop.selenium.framework.controls.interfaces.Readable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class comprises of functionality that would be used when interacting
 * with a typical HTML element. It is used in cases where you might have a UI
 * element that you only want to read or click but shouldn't strictly be
 * identified as a Button.
 * 
 * @author nvonop
 * 
 */
public class GenericHtmlElement extends BaseControl implements Clickable,
		Readable {

	/**
	 * Constructor that takes a WebDriver object and By object. These are then
	 * set in the base class.
	 * 
	 * @param driver
	 * @param locator
	 */
	public GenericHtmlElement(WebDriver driver, By locator) {
		this.driver = driver;
		setLocator(locator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public String read() {
		return getUnderlyingWebElement().getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.controls.interfaces.Clickable#click()
	 */
	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

}
