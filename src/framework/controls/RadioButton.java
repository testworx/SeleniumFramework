package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.controls.interfaces.Clickable;
import framework.controls.interfaces.Readable;

public class RadioButton extends BaseControl implements Clickable, Readable {

	public RadioButton(WebDriver driver, By locator) {
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
