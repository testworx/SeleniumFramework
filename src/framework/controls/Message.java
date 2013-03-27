package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Message extends BaseControl implements Detectable, Readable {

	public Message(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public Object readValue() {
		return getUnderlyingWebElement().getText();
	}

}
