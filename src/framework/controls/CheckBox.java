package framework.controls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBox extends BaseControl implements Clickable, Selectable,
		Readable {

	public CheckBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	@Override
	public Boolean readValue() {
		return new Boolean(getUnderlyingWebElement().isSelected());
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	@Override
	public void select() {
		if (!getUnderlyingWebElement().isSelected()) {
			getUnderlyingWebElement().click();
		}

	}

	@Override
	public void deSelect() {
		if (getUnderlyingWebElement().isSelected()) {
			getUnderlyingWebElement().click();
		}
	}

}
