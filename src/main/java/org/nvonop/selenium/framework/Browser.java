package org.nvonop.selenium.framework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.nvonop.selenium.framework.controls.Page;
import org.nvonop.selenium.framework.utilities.TestHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


/**
 * This class contains the base functionality for instantiating a thread safe
 * web browser for a test. Tests should be able to run in parallel with no
 * issues.
 * 
 * @author nvonop
 * 
 */
public class Browser {

	private String browser;
	private String version;
	private String platform;
	
	private static final Logger LOGGER = Logger.getLogger(Browser.class
			.getName());
	private static final String MESSAGE_REMOTE_SCREENSHOT_SUCCESSFUL = "Remote screenshot successful:";
	private static final String MESSAGE_LOCAL_SCREENSHOT_SUCCESSFUL = "Local screenshot successful:";
	private static final String MESSAGE_SAUCE_SCREENSHOT_FAILURE = "Cannot take screenshot.  Driver is on Sauce.";
	private static final String MESSAGE_IO_EXCEPTION_IN_GET_SCREENSHOT_METHOD = "IOException in getScreenshot() method";
	private static final String MESSAGE_WEB_DRIVER_EXCEPTION_IN_GET_SCREENSHOT_METHOD = "WebDriverException in getScreenshot() method";
	private static final String MESSAGE_CANNOT_TAKE_SCREENSHOT_UNABLE_TO_IDENTIFY_DRIVER_TYPE = "Cannot take screenshot.  Unable to identify driver type.";

	private File screenshot;
	
	private DesiredCapabilities extraCapabilities = new DesiredCapabilities();
	private DesiredCapabilities capabilities;
	
	private void writeScreenshotToFileSystem(String screenshotPath)
			throws IOException {
		FileUtils.copyFile(screenshot, new File(screenshotPath));
	}
	TestHelper testHelper;

	/**
	 * A ThreadLocal instance of a WebDriver object. It should be thread safe
	 * and enable tests to run in parallel.
	 */
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public Browser() {
		browser = System.getProperty("BROWSER");
		version = System.getProperty("VERSION");
		platform = System.getProperty("PLATFORM");
		
		testHelper= new TestHelper();
	}

	/**
	 * This method closes the browser associated with the ThreadLocal WebDriver
	 * object. It also the WebDriver instance for the ThreadLocal value to null.
	 */
	public void close() {
		if (driver.get()!=null) {
			driver.get().quit();
			driver.set(null);
		}
	}

	private int getBrowserId(String browser) {
		if (browser.toLowerCase().contains("internet explorer")) {
			return 0;
		} else if (browser.toLowerCase().contains("firefox")) {
			return 1;
		} else if (browser.toLowerCase().contains("safari")) {
			return 2;
		} else if (browser.toLowerCase().contains("chrome")) {
			return 3;
		} else {
			return 10;
		}
	}

	/**
	 * This method gets the current page source and returns as a String
	 * 
	 */
	public String getPageSource() {
		return driver.get().getPageSource();
	}
	
	/**
	 * This method returns the WebDriver object
	 * 
	 */
	public WebDriver getWebdriver() {
		return driver.get();
	}

	/**
	 * This method gets the current page title and returns as a String
	 * 
	 */
	public String getPageTitle() {
		return driver.get().getTitle();
	}

	/**
	 * This method takes a screenshot and saves it in the location specified in
	 * the system property "TEST_RESULTS_PATH".
	 */
	public void getScreenshot() {
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
	public void getScreenshot(String path) {

		String screenshotPath;
		String timestamp = testHelper.getTimestamp();

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
	 * 
	 * This method gets the browser to navigate to the specified URL
	 * @param URL
	 * @return Page
	 */
	public Page navigate(String URL) {
		driver.get().navigate().to(URL);
		
		return new Page(driver.get());
	}
	
	/**
	 * 
	 * This method gets the browser to navigate to the specified URL
	 * @param URL
	 * @return Page
	 */
	public Object navigate(Class <? extends Page> pageObject) {
		
		Page page;
		
		try {
			page = pageObject.getConstructor(WebDriver.class).newInstance(driver.get());
			driver.get().navigate().to(page.getURL());
//			driver.get().navigate().to(System.getProperty("APPLICATION_URL"));
			return page;
			
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			LOGGER.log(Level.SEVERE,
					"ERROR loading pageObject", e);
			return null;
		}
		
	}
	
	/**
	 * This method opens a new web browser
	 * 
	 */
	public void open() {
		setWebDriver();
	}
	
	/**
	 * This method initialises the ThreadLocal WebDriver object according to the
	 * supplied parameters to enable tests to run on a users local machine.
	 * 
	 */
	private void setLocalWebdriver() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("handlesAlerts", true);
		switch (getBrowserId(browser)) {
		case 0:
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			driver.set(new InternetExplorerDriver(capabilities));
			break;
		case 1:
			driver.set(new FirefoxDriver(capabilities));
			break;
		case 2:
			driver.set(new SafariDriver(capabilities));
			break;
		case 3:
			driver.set(new ChromeDriver(capabilities));
			break;
		default:
			throw new WebDriverException("Browser not found: "+browser);
		}
	}
	
	
	/**
	 * This method initialises the ThreadLocal WebDriver object according to the
	 * supplied parameters to enable tests to run remotely on a grid of test
	 * machines.
	 * 
	 */
	private void setRemoteWebdriver() {

		switch (getBrowserId(browser)) {
		case 0:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			break;
		case 1:
			capabilities = DesiredCapabilities.firefox();
			break;
		case 2:
			capabilities = DesiredCapabilities.safari();
			break;
		case 3:
			capabilities = DesiredCapabilities.chrome();
			break;
		default:
			throw new WebDriverException("Browser not found: "+browser);
		}
		capabilities.setCapability("javascriptEnabled", true);
		capabilities.setCapability("platform", platform);
		capabilities.setCapability("version", version);
		capabilities.merge(extraCapabilities);

		try {
			this.driver.set(new RemoteWebDriver(new URL("http://"
					+ System.getProperty("GRID_HOST") + ":"
					+ System.getProperty("GRID_PORT") + "/wd/hub"),
					capabilities));
		} catch (MalformedURLException e) {
			LOGGER.log(Level.INFO,
					"MalformedURLException in setRemoteWebdriver() method", e);
		}
	}

	/**
	 * This method initialises the ThreadLocal WebDriver object according to the
	 * supplied parameters to enable tests to run on Sauce Labs cloud
	 * infrastructure.
	 * 
	 */
	private void setSauceWebdriver() {

		switch (getBrowserId(browser)) {
		case 0:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			break;
		case 1:
			capabilities = DesiredCapabilities.firefox();
			break;
		case 2:
			capabilities = DesiredCapabilities.safari();
			break;
		case 3:
			capabilities = DesiredCapabilities.chrome();
			break;
		default:
			throw new WebDriverException("Browser not found: "+browser);
		}
		capabilities.merge(extraCapabilities);
		capabilities.setCapability("javascriptEnabled", true);
		capabilities.setCapability("platform", platform);
		capabilities.setCapability("version", version);

		try {
			this.driver.set(new RemoteWebDriver(new URL(System
					.getProperty("SAUCE_KEY")), capabilities));
		} catch (MalformedURLException e) {
			LOGGER.log(Level.INFO,
					"MalformedURLException in setSauceWebdriver() method", e);
		}
	}
	
	private void setUpScreenshot(String screenshotPath)
			throws IOException {
		if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
			takeScreenshot();
			writeScreenshotToFileSystem(screenshotPath);
			LOGGER.log(Level.FINEST, MESSAGE_LOCAL_SCREENSHOT_SUCCESSFUL
					+ screenshotPath);
		} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
			driver.set(new Augmenter().augment(driver.get()));
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

	/**
	 * This method provides a way of initialising a ThreadLocal WebDriver object
	 * in a consistent way regardless of where the test will run. The following
	 * system properties must be set to "true" or "false" for this method to
	 * function correctly: LOCAL_DRIVER, REMOTE_DRIVER, SAUCE_DRIVER. Setting
	 * one of the properties to "true" will ensure the ThreadLocal WebDriver
	 * object is initialised appropriately.
	 * 
	 */
	private void setWebDriver() {
		if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
			setLocalWebdriver();
		} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
			setRemoteWebdriver();
		} else if (Boolean.valueOf(System.getProperty("SAUCE_DRIVER"))) {
			setSauceWebdriver();
		} else {
			throw new WebDriverException("Type of driver not specified!!!");
		}
		this.driver.get().manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get().manage().window().maximize();
	}
	
	/**
	 * This method provides a way of specifying browser capabilities manually
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return boolean
	 */
	public boolean setBrowserCapability(String key, String value) {
		if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
			return false;
		} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
			extraCapabilities.setCapability(key, value);
			return true;
		} else if (Boolean.valueOf(System.getProperty("SAUCE_DRIVER"))) {
			extraCapabilities.setCapability(key, value);
			return true;
		} else {
			throw new WebDriverException("Type of driver not specified!!!");
		}
	}

	/**
	 * This method allows focus to switch to a different frame within a web
	 * page.
	 * 
	 * @param locator
	 */
	public void switchToFrame(By locator) {
		String locatorSubString = testHelper.getLocatorString(locator);

		driver.get().switchTo().frame(locatorSubString);
	}
	
	/**
	 * This method allows focus to switch to an alert
	 */
	public Alert switchToAlert() {
		
		return driver.get().switchTo().alert();
	}
	
	/**
	 * This method allows the test to switch focus to a browser window with the
	 * title of "windowTitle".
	 * 
	 * @param windowTitle
	 */
	public void switchToWindow(String windowTitle) {
		Set<String> windows = driver.get().getWindowHandles();

		for (String window : windows) {
			driver.get().switchTo().window(window);
			if (driver.get().getTitle().contains(windowTitle)) {
				LOGGER.log(Level.FINEST,
						"Switching to window: " + driver.get().getTitle());
				return;
			} else {
				LOGGER.log(Level.FINEST,
						"No match for window: " + driver.get().getTitle());
			}
		}
	}

	private void takeScreenshot() {
		screenshot = ((TakesScreenshot) driver.get())
				.getScreenshotAs(OutputType.FILE);
	}

}
