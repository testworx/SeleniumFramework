package framework.controls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.BaseControl;
import framework.controls.Clickable;
import framework.controls.Readable;

public class Button extends BaseControl implements Clickable, Readable {

	public Button(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public Object readValue() {
		return getUnderlyingWebElement().getText();
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();

	}

}
