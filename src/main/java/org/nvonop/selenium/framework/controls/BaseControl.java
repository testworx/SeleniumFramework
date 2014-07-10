package org.nvonop.selenium.framework.controls;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.nvonop.selenium.framework.controls.interfaces.Detectable;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This abstract class will be extended by various UI control classes. It simply
 * acts as a container for functionality that could be considered common to all
 * UI controls.
 * 
 * @author nvonop
 * 
 */
public abstract class BaseControl implements Detectable {

	private static final Logger LOGGER = Logger.getLogger(BaseControl.class
			.getName());

	private WebElement baseWebElement;
	private long delay = Long.valueOf(System.getProperty("DELAY", "10"))
			.longValue();
	protected WebDriver driver;
	private By locator;
	private long timeout = Long.valueOf(System.getProperty("TIMEOUT", "10"))
			.longValue();

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.controls.interfaces.Detectable#exists()
	 */
	@Override
	public boolean exists() {
		try {
			findControl();
			return baseWebElement.isDisplayed();
		} catch (ElementNotVisibleException e) {
			LOGGER.log(Level.INFO,
					"ElementNotVisibleException in exists() method", e);
			return false;
		} catch (TimeoutException e) {
			LOGGER.log(Level.INFO, "TimeoutException in exists() method", e);
			return false;
		}
	}

	/*
	 * This method wraps the findElement method and returns a WebElement. By
	 * default it will attempt to wait for an element to be clickable before
	 * returning it. If this fails then after 10 seconds it will simply match
	 * the element if possible and return it.
	 */
	protected WebElement findControl() {
		try {
			LOGGER.log(Level.FINEST,
					"Looking for WebElement: " + locator.toString());

			waitForElementThenInitialise();

			return baseWebElement;

		} catch (NoSuchElementException e) {
			LOGGER.log(Level.SEVERE,
					"NoSuchElementException in findControl() method for: "
							+ locator.toString(), e);
			throw new NoSuchElementException(locator.toString());
		}
	}

	private void waitForElementThenInitialise() {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			baseWebElement = wait.until(ExpectedConditions
					.elementToBeClickable(getLocator()));
			waitForControlToStopMoving();
		} catch (InterruptedException e) {
			LOGGER.log(Level.INFO,
					"InterruptedException in findControl() method", e);
		} catch (TimeoutException e) {
			LOGGER.log(Level.INFO, "TimeoutException in findControl() method",
					e);
		}
		if (baseWebElement == null) {
			baseWebElement = driver.findElement(locator);
		}
	}

	/*
	 * This method wraps the findElement method and returns a WebElement using
	 * theLocator parameter. By default it will attempt to wait for an element
	 * to be clickable and stationary before returning it. If this fails then
	 * after 10 seconds it will simply match the element if possible and return
	 * it.
	 */
	protected WebElement findControl(By theLocator) {
		try {
			setLocator(theLocator);
			LOGGER.log(Level.FINEST,
					"Looking for WebElement: " + locator.toString());

			waitForElementThenInitialise();
			return baseWebElement;

		} catch (NoSuchElementException e) {
			LOGGER.log(Level.SEVERE,
					"NoSuchElementException in findControl() method for: "
							+ locator.toString(), e);
			throw new NoSuchElementException(locator.toString());
		}
	}

	protected By getLocator() {
		return locator;
	}

	/**
	 * This method enables a test to gain access to the WebElement object and
	 * access the WebDriver API directly.
	 * 
	 * @return The underlying WebElement object.
	 */
	public WebElement getUnderlyingWebElement() {
		return findControl(locator);
	}

	protected void setLocator(By theLocator) {
		locator = theLocator;
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
