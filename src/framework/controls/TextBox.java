package framework.controls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.BaseControl;
import framework.controls.Clickable;
import framework.controls.Readable;
import framework.controls.Writeable;

public class TextBox extends BaseControl implements Clickable, Readable,
		Writeable {

	public TextBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public String readValue() {
		return getUnderlyingWebElement().getAttribute("value");
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	@Override
	public void write(String value) {
		getUnderlyingWebElement().sendKeys(value);
	}
}
