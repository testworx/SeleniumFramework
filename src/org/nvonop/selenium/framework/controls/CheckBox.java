package org.nvonop.selenium.framework.controls;

import org.nvonop.selenium.framework.controls.interfaces.Clickable;
import org.nvonop.selenium.framework.controls.interfaces.Readable;
import org.nvonop.selenium.framework.controls.interfaces.Selectable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * This class comprises of functionality that would be used 
 * when interacting with a typical checkbox control.
 * @author nvonop
 *
 */
public class CheckBox extends BaseControl implements Clickable, Selectable,
		Readable {
	
	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public CheckBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public Boolean read() {
		return new Boolean(getUnderlyingWebElement().isSelected());
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Clickable#click()
	 */
	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Selectable#select()
	 */
	@Override
	public void select() {
		if (!getUnderlyingWebElement().isSelected()) {
			getUnderlyingWebElement().click();
		}

	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Selectable#deSelect()
	 */
	@Override
	public void deSelect() {
		if (getUnderlyingWebElement().isSelected()) {
			getUnderlyingWebElement().click();
		}
	}

}
