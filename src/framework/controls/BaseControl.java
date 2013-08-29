package framework.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.controls.interfaces.Detectable;

public abstract class BaseControl implements Detectable {

	private WebDriver driver;
	private WebElement baseWebElement;
	private By locator;
	private long timeout = 10;
	private long delay = 500;

	public WebElement getUnderlyingWebElement() {
		return findControl(driver, locator);
	}

	private void setUnderlyingWebElement(WebElement element) {
		baseWebElement = element;
		System.out.println("WebElement found.");
	}

	@Override
	public boolean exists() {
		try {
			findControl();
			return baseWebElement.isDisplayed();
		} catch (ElementNotVisibleException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected WebElement findControl(WebDriver theDriver, By theLocator) {
		try {
			setDriver(theDriver);
			setLocator(theLocator);
			System.out.println("Looking for WebElement: " + locator.toString());
			setUnderlyingWebElement(driver.findElement(locator));

			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			try {
				baseWebElement = wait.until(ExpectedConditions
						.elementToBeClickable(getLocator()));
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (baseWebElement == null) {
				baseWebElement = driver.findElement(locator);
			}
			return baseWebElement;

		} catch (NoSuchElementException e) {
			System.out.println("Failed looking for WebElement: "
					+ locator.toString());
			throw new NoSuchElementException(locator.toString());
		}
	}

	protected WebElement findControl() {
		try {

			System.out.println("Looking for WebElement: " + locator.toString());
			setUnderlyingWebElement(driver.findElement(locator));

			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			try {
				baseWebElement = wait.until(ExpectedConditions
						.elementToBeClickable(getLocator()));
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (baseWebElement == null) {
				baseWebElement = driver.findElement(locator);
			}

			return baseWebElement;

		} catch (NoSuchElementException e) {
			System.out.println("Failed looking for WebElement: "
					+ locator.toString());
			throw new NoSuchElementException(locator.toString());
		}
	}

	protected void setLocator(By theLocator) {
		locator = theLocator;
	}

	protected By getLocator() {
		return locator;
	}

	protected void setDriver(WebDriver theDriver) {
		driver = theDriver;
	}

	protected WebDriver getDriver() {
		return driver;
	}
}
