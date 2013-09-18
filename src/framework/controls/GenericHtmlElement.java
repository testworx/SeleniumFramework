package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;
import framework.controls.interfaces.Writeable;

/**
 * This class comprises of functionality that would be used 
 * when interacting with a typical HTML element.  
 * It is used in cases where you might have a UI element that 
 * you only want to read or click but shouldn't strictly be identified as a Button.
 * @author nvonop
 *
 */
public class GenericHtmlElement extends BaseControl implements Clickable, Readable {

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public GenericHtmlElement(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public String read() {
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
