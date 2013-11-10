package org.nvonop.selenium.framework.controls;

import java.util.concurrent.TimeUnit;

import org.nvonop.selenium.framework.controls.interfaces.Detectable;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This abstract class will be extended by various UI control classes.  It simply acts as a container for 
 * functionality that could be considered common to all UI controls.
 * @author nvonop
 *
 */
public abstract class BaseControl implements Detectable {

	private WebElement baseWebElement;
	private long delay = 500;
	private WebDriver driver;
	private By locator;
	private long timeout = Long.valueOf(System.getProperty("TIMEOUT", "10")).longValue();

	/* (non-Javadoc)
	 * @see framework.controls.interfaces.Detectable#exists()
	 */
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

	/*
	 * This method wraps the findElement method and returns a WebElement.
	 * By default it will attempt to wait for an element to be clickable before returning it.
	 * If this fails them after 10 seconds it will simply match the element if possible and return it.
	 */
	protected WebElement findControl() {
		try {

			System.out.println("Looking for WebElement: " + locator.toString());
		
			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			try {
				baseWebElement = wait.until(ExpectedConditions
						.elementToBeClickable(getLocator()));
				waitForControlToStopMoving();
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

	/*
	 * This method wraps the findElement method and returns a WebElement using theLocator parameter.
	 * By default it will attempt to wait for an element to be clickable and stationary before returning it.
	 * If this fails them after 10 seconds it will simply match the element if possible and return it.
	 */
	protected WebElement findControl(WebDriver theDriver, By theLocator) {
		try {
			setDriver(theDriver);
			setLocator(theLocator);
			System.out.println("Looking for WebElement: " + locator.toString());

			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
						
			try {
				baseWebElement = wait.until(ExpectedConditions
						.elementToBeClickable(getLocator()));
				waitForControlToStopMoving();
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

	protected WebDriver getDriver() {
		return driver;
	}


	protected By getLocator() {
		return locator;
	}

	/**
	 * This method enables a test to gain access to the WebElement object and 
	 * access the WebDriver API directly.
	 * @return	The underlying WebElement object.
	 */
	public WebElement getUnderlyingWebElement() {
		return findControl(driver, locator);
	}

	protected void setDriver(WebDriver theDriver) {
		driver = theDriver;
	}

	protected void setLocator(By theLocator) {
		locator = theLocator;
	}

	private void setUnderlyingWebElement(WebElement element) {
		baseWebElement = element;
		System.out.println("WebElement found.");
	}
	
	private void waitForControlToStopMoving() throws InterruptedException {
		Point elementStartLocation = baseWebElement.getLocation();
		Thread.sleep(delay);
		Point elementCurrentLocation = baseWebElement.getLocation();
		while (!elementCurrentLocation.equals(elementStartLocation)) {
			elementStartLocation = elementCurrentLocation;
			Thread.sleep(delay);
			elementCurrentLocation = baseWebElement.getLocation();
		}
	}
}
