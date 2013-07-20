package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Detectable;
import framework.controls.interfaces.Readable;

public class Message extends BaseControl implements Detectable, Readable {

	public Message(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public Object read() {
		return getUnderlyingWebElement().getText();
	}

}
