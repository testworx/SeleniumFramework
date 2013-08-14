package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;
import framework.controls.interfaces.Writeable;

public class GenericHtmlElement extends BaseControl implements Clickable, Readable {

	public GenericHtmlElement(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public String read() {
		return getUnderlyingWebElement().getText();
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

}
