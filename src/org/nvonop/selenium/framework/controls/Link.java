package org.nvonop.selenium.framework.controls;

import org.nvonop.selenium.framework.controls.interfaces.Clickable;
import org.nvonop.selenium.framework.controls.interfaces.Readable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class comprises of functionality that would be used when interacting
 * with a typical link control.
 * 
 * @author nvonop
 * 
 */
public class Link extends BaseControl implements Clickable, Readable {

	/**
	 * Constructor that takes a WebDriver object and By object. These are then
	 * set in the base class.
	 * 
	 * @param driver
	 * @param locator
	 */
	public Link(WebDriver driver, By locator) {
		this.driver = driver;
		setLocator(locator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public Object read() {
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
