package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;
import framework.controls.interfaces.Writeable;

/**
 * This class comprises of functionality that would be used 
 * when interacting with a typical text box control.
 * @author nvonop
 *
 */
public class TextBox extends BaseControl implements Clickable, Readable,
		Writeable {

	/**
	 * Constructor that takes a WebDriver object and By object.  
	 * These are then set in the base class.
	 * @param driver
	 * @param locator
	 */
	public TextBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Readable#read()
	 */
	@Override
	public String read() {
		return getUnderlyingWebElement().getAttribute("value");
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Clickable#click()
	 */
	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Writeable#write(java.lang.String)
	 */
	@Override
	public void write(String value) {
		getUnderlyingWebElement().sendKeys(Keys.HOME);
		getUnderlyingWebElement().sendKeys(Keys.SHIFT, Keys.END, Keys.DELETE);
		getUnderlyingWebElement().sendKeys(value);
	}
}
