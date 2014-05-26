package org.nvonop.selenium.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;

/**
 * @author nvonop This class is intended to be a general utility class
 *         consisting of useful methods that can be called from any location
 *         without having to instantiate an object.
 */
public class TestHelper {

	private static final String MESSAGE_REMOTE_SCREENSHOT_SUCCESSFUL = "Remote screenshot successful:";
	private static final String MESSAGE_LOCAL_SCREENSHOT_SUCCESSFUL = "Local screenshot successful:";
	private static final String MESSAGE_SAUCE_SCREENSHOT_FAILURE = "Cannot take screenshot.  Driver is on Sauce.";
	private static final String MESSAGE_IO_EXCEPTION_IN_GET_SCREENSHOT_METHOD = "IOException in getScreenshot() method";
	private static final String MESSAGE_WEB_DRIVER_EXCEPTION_IN_GET_SCREENSHOT_METHOD = "WebDriverException in getScreenshot() method";
	private static final String MESSAGE_CANNOT_TAKE_SCREENSHOT_UNABLE_TO_IDENTIFY_DRIVER_TYPE = "Cannot take screenshot.  Unable to identify driver type.";
	private static final Logger LOGGER = Logger.getLogger(TestHelper.class
			.getName());
	private static WebDriver driver;
	private static String ALLOWABLE_CHARS = "abcdefghijklmnopqrstuvwxyz";
	private static File screenshot;

	private static String buildRandomString(int length) {

		Random rand = new Random();
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < length; i++) {
			randomString.append(ALLOWABLE_CHARS.charAt(rand
					.nextInt(ALLOWABLE_CHARS.length())));
		}
		return randomString.toString();
	}

	/**
	 * This method parses a By object and returns is locator value.
	 * 
	 * @param locator
	 * @return a string representing the locator value
	 */
	public static String getLocatorString(By locator) {
		String[] locatorArray = locator.toString().split("\\s");
		String locatorSubString = locatorArray[1];
		LOGGER.log(Level.FINEST, "Parsing locator: " + locator.toString());
		LOGGER.log(Level.FINEST, "Locator String: " + locatorArray[1]);

		return locatorSubString;
	}

	/**
	 * This method is used to obtain a string of random characters. It is useful
	 * when unique String is required for populating a form.
	 * 
	 * @return a random String
	 */
	public static String getRandomString() {

		// String hard coded to length 10 for now
		int length = 10;
		return buildRandomString(length);
	}

	/**
	 * This method is used to obtain a string of random characters. It is useful
	 * when unique String is required for populating a form. The length of the
	 * string is determined by stringLength
	 * 
	 * @param length
	 *            the length of the String that will be returned
	 * @return a random String
	 */
	public static String getRandomString(int length) {

		return buildRandomString(length);
	}

	/**
	 * This method takes a screenshot and saves it in the location specified in
	 * the system property "TEST_RESULTS_PATH".
	 */
	public static void getScreenshot() {
		String screenshotPath = System.getProperty("TEST_RESULTS_PATH");
		getScreenshot(screenshotPath);
	}

	/**
	 * This method takes a screenshot and saves it in the location specified by
	 * "path".
	 * 
	 * @param path
	 *            the location to save the screenshot to
	 */
	public static void getScreenshot(String path) {

		String screenshotPath;
		String timestamp = getTimestamp();

		screenshotPath = path + "//" + timestamp + ".JPG";
		try {
			setUpScreenshot(screenshotPath);
		} catch (WebDriverException e) {
			LOGGER.log(Level.INFO,
					MESSAGE_WEB_DRIVER_EXCEPTION_IN_GET_SCREENSHOT_METHOD, e);
		} catch (IOException e) {
			LOGGER.log(Level.INFO,
					MESSAGE_IO_EXCEPTION_IN_GET_SCREENSHOT_METHOD, e);
		}

	}

	/**
	 * This method calculates the timestamp at the point the method is called
	 * and returns it as a String. It is useful when you need to timestamp a
	 * file such as a screenshot.
	 * 
	 * @return the timestamp
	 */
	public static String getTimestamp() {

		Date date = new java.util.Date();
		String timestamp = (new Timestamp(date.getTime())).toString();

		timestamp = timestamp.replaceAll("/", "-");
		timestamp = timestamp.replaceAll(":", ".");
		return timestamp;
	}

	/**
	 * This method set driver to be passedInDriver.
	 * 
	 * @param passedInDriver
	 */
	public static void setDriver(WebDriver passedInDriver) {
		driver = passedInDriver;
	}

	/**
	 * This method allows focus to switch to a different frame within a web
	 * page.
	 * 
	 * @param locator
	 */
	public static void switchToFrame(By locator) {
		String locatorSubString = getLocatorString(locator);

		driver.switchTo().frame(locatorSubString);
	}

	/**
	 * This method allows the test to switch focus to a browser window with the
	 * title of "windowTitle".
	 * 
	 * @param windowTitle
	 */
	public static void switchToWindow(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				LOGGER.log(Level.FINEST,
						"Switching to window: " + driver.getTitle());
				return;
			} else {
				LOGGER.log(Level.FINEST,
						"No match for window: " + driver.getTitle());
			}
		}
	}

	private static void setUpScreenshot(String screenshotPath)
			throws IOException {
		if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
			takeScreenshot();
			writeScreenshotToFileSystem(screenshotPath);
			LOGGER.log(Level.FINEST, MESSAGE_LOCAL_SCREENSHOT_SUCCESSFUL
					+ screenshotPath);
		} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
			driver = new Augmenter().augment(driver);
			takeScreenshot();
			writeScreenshotToFileSystem(screenshotPath);
			LOGGER.log(Level.FINEST, MESSAGE_REMOTE_SCREENSHOT_SUCCESSFUL
					+ screenshotPath);
		} else if (Boolean.valueOf(System.getProperty("SAUCE_DRIVER"))) {
			LOGGER.log(Level.WARNING, MESSAGE_SAUCE_SCREENSHOT_FAILURE);
		} else {
			LOGGER.log(Level.WARNING,
					MESSAGE_CANNOT_TAKE_SCREENSHOT_UNABLE_TO_IDENTIFY_DRIVER_TYPE);
		}
	}

	private static void takeScreenshot() {
		screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
	}

	private static void writeScreenshotToFileSystem(String screenshotPath)
			throws IOException {
		FileUtils.copyFile(screenshot, new File(screenshotPath));
	}

	private By buildLocator(String locatorType, String locatorValue) {
		// Return a instance of By class based on type of locator
		if ("id".equalsIgnoreCase(locatorType)) {
			return By.id(locatorValue);
		} else if ("name".equalsIgnoreCase(locatorType)) {
			return By.name(locatorValue);
		} else if (("classname".equalsIgnoreCase(locatorType))
				|| ("class".equalsIgnoreCase(locatorType))) {
			return By.className(locatorValue);
		} else if (("tagname".equalsIgnoreCase(locatorType))
				|| ("tag".equalsIgnoreCase(locatorType))) {
			return By.className(locatorValue);
		} else if (("linktext".equalsIgnoreCase(locatorType))
				|| ("link".equalsIgnoreCase(locatorType))) {
			return By.linkText(locatorValue);
		} else if ("partiallinktext".equalsIgnoreCase(locatorType)) {
			return By.partialLinkText(locatorValue);
		} else if (("cssselector".equalsIgnoreCase(locatorType))
				|| ("css".equalsIgnoreCase(locatorType))) {
			return By.cssSelector(locatorValue);
		} else if ("xpath".equalsIgnoreCase(locatorType)) {
			return By.xpath(locatorValue);
		} else {
			LOGGER.log(Level.SEVERE, "Locator type '" + locatorType
					+ "' not defined!!");
			return null;
		}
	}

	/**
	 * This method enables a By object to be created from a locator type and
	 * value.
	 * 
	 * @param locatorType
	 *            the locator type e.g. ID, CSS etc
	 * @param locatorValue
	 *            the locator value e.g. an XPath of CSS locator
	 * @return a By object
	 * @throws Exception
	 */
	public By getCustomLocator(String locatorType, String locatorValue) {

		return buildLocator(locatorType, locatorValue);
	}

}
