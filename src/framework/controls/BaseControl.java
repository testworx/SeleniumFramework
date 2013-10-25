package framework.controls;

import java.util.concurrent.TimeUnit;

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
import framework.controls.interfaces.Detectable;

/**
 * This abstract class will be extended by various UI control classes.  It simply acts as a container for 
 * functionality that could be considered common to all UI controls.
 * @author nvonop
 *
 */
public abstract class BaseControl implements Detectable {

	private WebDriver driver;
	private WebElement baseWebElement;
	private By locator;
	private long timeout = 10;
	private long delay = 500;

	/**
	 * This method enables a test to gain access to the WebElement object and 
	 * access the WebDriver API directly.
	 * @return	The underlying WebElement object.
	 */
	public WebElement getUnderlyingWebElement() {
		return findControl(driver, locator);
	}

	private void setUnderlyingWebElement(WebElement element) {
		baseWebElement = element;
		System.out.println("WebElement found.");
	}

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
	 * This method wraps the findElement method and returns a WebElement using theLocator parameter.
	 * By default it will attempt to wait for an element to be clickable before returning it.
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
				Point elementStartLocation = baseWebElement.getLocation();
				Thread.sleep(delay);
				Point elementCurrentLocation = baseWebElement.getLocation();
				while (!elementCurrentLocation.equals(elementStartLocation)) {
					elementStartLocation = elementCurrentLocation;
					Thread.sleep(delay);
					elementCurrentLocation = baseWebElement.getLocation();
			}
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
				Point elementStartLocation = baseWebElement.getLocation();
				Thread.sleep(delay);
				Point elementCurrentLocation = baseWebElement.getLocation();
				while (!elementCurrentLocation.equals(elementStartLocation)) {
					elementStartLocation = elementCurrentLocation;
					Thread.sleep(delay);
					elementCurrentLocation = baseWebElement.getLocation();
				}
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
