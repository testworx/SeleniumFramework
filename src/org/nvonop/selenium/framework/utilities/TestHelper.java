package org.nvonop.selenium.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;

/**
 * @author nvonop
 *This class is intended to be a general utility class consisting of useful methods that can be called 
 *from any location without having to instantiate an object.
 */
public class TestHelper {

	private static WebDriver driver;

	/**
	 * This method parses a By object and returns is locator value.
	 * @param locator
	 * @return a string representing the locator value
	 */
	public static String getLocatorString(By locator) {
		String[] locatorArray = locator.toString().split("\\s");
		String locatorSubString = locatorArray[1];
		System.out.println("Parsing locator: " + locator.toString());
		System.out.println("Locator String: " + locatorArray[1]);

		return locatorSubString;
	}

	/**
	 * This method is used to obtain a string of random characters.  It is useful when unique String is 
	 * required for populating a form. 
	 * @return a random String
	 */
	public static String getRandomString() {
	    
		int length = 10; //String hard coded to length 10 for now
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		  StringBuilder randomString = new StringBuilder();
		  for (int i=0; i<length; i++) {
		    randomString.append(chars.charAt(rand.nextInt(chars.length())));
		  }
		  return randomString.toString();
	}
	
	/**
	 * This method is used to obtain a string of random characters.  It is useful when unique String is 
	 * required for populating a form. The length of the string is determined by stringLength
	 * @param stringLength the length of the String that will be returned
	 * @return a random String
	 */
	public static String getRandomString(int stringLength) {
	    
		int length = stringLength; //String hard coded to length 10 for now
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		  StringBuilder randomString = new StringBuilder();
		  for (int i=0; i<length; i++) {
		    randomString.append(chars.charAt(rand.nextInt(chars.length())));
		  }
		  return randomString.toString();
	}

	/**
	 * This method takes a screenshot and saves it in the location specified in 
	 * the system property "TEST_RESULTS_PATH".
	 */
	public static void getScreenshot() {

		try {

			String timestamp = getTimestamp();

			String screenshotPath = System.getProperty("TEST_RESULTS_PATH") + "//" + timestamp + ".JPG";
			
			File screenshot;

			if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
				screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(screenshotPath));
			} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
				driver = new Augmenter().augment(driver);
				screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(screenshotPath));
			} else if (Boolean.valueOf(System.getProperty("SAUCE_DRIVER"))) {
				System.out
						.println("Cannot take screenshot.  Driver is on Sauce.");
			} else {
				System.out
						.println("Cannot take screenshot.  Unable to identify driver type.");
			}
		} catch (WebDriverException e) {
			System.out.println("An exception occurred taking the screenshot.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An exception occurred taking the screenshot.");
			e.printStackTrace();
		}

	}

	/**
	 * This method takes a screenshot and saves it in the location specified by "path".
	 * @param path the location to save the screenshot to
	 */
	public static void getScreenshot(String path) {

		try {

			String screenshotPath = path;

			String timestamp = getTimestamp();

			screenshotPath = screenshotPath + "//" + timestamp + ".JPG";

			File screenshot;

			if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
				screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(screenshotPath));
			} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
				driver = new Augmenter().augment(driver);
				screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(screenshotPath));
			} else if (Boolean.valueOf(System.getProperty("SAUCE_DRIVER"))) {
				System.out
						.println("Cannot take screenshot.  Driver is on Sauce.");
			} else {
				System.out
						.println("Cannot take screenshot.  Unable to identify driver type.");
			}
		} catch (WebDriverException e) {
			System.out.println("An exception occurred taking the screenshot.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An exception occurred taking the screenshot.");
			e.printStackTrace();
		}

	}

	/**
	 * This method calculates the timestamp at the point the method is called and 
	 * returns it as a String.  It is useful when you need to timestamp a file such 
	 * as a screenshot.
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
	 * @param passedInDriver
	 */
	public static void setDriver(WebDriver passedInDriver) {
		driver = passedInDriver;
	}
	
	/**
	 * This method allows focus to switch to a different frame within a web page.
	 * @param locator
	 */
	public static void switchToFrame(By locator) {
		String locatorSubString = getLocatorString(locator);

		driver.switchTo().frame(locatorSubString);
	}
	
	/**
	 * This method allows the test to switch focus to a browser window with the
	 * title of "windowTitle".
	 * @param windowTitle
	 */
	public static void switchToWindow(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				System.out.println("Switching to window: " + driver.getTitle());
				return;
			} else {
				System.out.println("No match for window: " + driver.getTitle());
			}
		}
	}
	
	/**
	 * This method enables a By object to be created from a locator type and value.
	 * @param locatorType the locator type e.g. ID, CSS etc
	 * @param locatorValue the locator value e.g. an XPath of CSS locator
	 * @return a By object
	 * @throws Exception
	 */
	public By getCustomLocator(String locatorType, String locatorValue)
			throws Exception {

		// Return a instance of By class based on type of locator
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue); }
		else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue); }
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class"))) {
			return By.className(locatorValue); }
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag"))) {
			return By.className(locatorValue); }
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link"))) {
			return By.linkText(locatorValue); }
		else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue); }
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css"))) {
			return By.cssSelector(locatorValue); }
		else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue); }
		else {
			throw new Exception("Locator type '" + locatorType
					+ "' not defined!!"); }
	}

}
