package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;
import framework.controls.interfaces.Writeable;

public class TextBox extends BaseControl implements Clickable, Readable,
		Writeable {

	public TextBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public String read() {
		return getUnderlyingWebElement().getAttribute("value");
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	@Override
	public void write(String value) {
		getUnderlyingWebElement().sendKeys(Keys.HOME);
		getUnderlyingWebElement().sendKeys(Keys.SHIFT, Keys.END, Keys.DELETE);
		getUnderlyingWebElement().sendKeys(value);
	}
}
