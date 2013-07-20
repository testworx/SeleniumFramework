package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;

public class Button extends BaseControl implements Clickable, Readable {

	public Button(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public Object read() {
		return getUnderlyingWebElement().getText();
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();

	}

}
