package framework.controls;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.controls.Detectable;

public abstract class BaseControl implements Detectable {

	private WebDriver driver;
	private WebElement baseWebElement;
	private By locator;

	public WebElement getUnderlyingWebElement() {
		return findControl(driver, locator);
	}

	private void setUnderlyingWebElement(WebElement element) {
		baseWebElement = element;
	}

	@Override
	public boolean exists() {
		try {
			return baseWebElement.isDisplayed();
		} catch (ElementNotVisibleException e) {
			return false;
		}
	}

	protected WebElement findControl(WebDriver theDriver, By theLocator) {
		try {
			setDriver(theDriver);
			setLocator(theLocator);
			System.out.println("Looking for WebElement: " + locator.toString());
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			System.out.println("Failed looking for WebElement: "
					+ locator.toString());
			throw new NoSuchElementException(locator.toString());
		}
	}

	protected WebElement findControl() {
		try {
			System.out.println("Looking for WebElement: " + locator.toString());
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			System.out.println("Failed looking for WebElement: "
					+ locator.toString());
			throw new NoSuchElementException(locator.toString());
		}
	}

	protected void setLocator(By theLocator) {
		locator = theLocator;
	}

	protected void setDriver(WebDriver theDriver) {
		driver = theDriver;
	}
}
