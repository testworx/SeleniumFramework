package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Detectable;
import framework.controls.interfaces.Readable;

/**
 * This class comprises of functionality that would be used 
 * when interacting with a validation message of some sort.
 * @author nvonop
 *
 */
public class Message extends BaseControl implements Detectable, Readable {

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public Message(WebDriver driver, By locator) {
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

}
