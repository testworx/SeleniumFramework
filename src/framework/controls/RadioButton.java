package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;

/**
 * This class comprises of functionality that would be used 
 * when interacting with a typical radio button control.
 * @author nvonop
 *
 */
public class RadioButton extends BaseControl implements Clickable, Readable {

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public RadioButton(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public Object read() {
		return getUnderlyingWebElement().getText();
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Clickable#click()
	 */
	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

}
