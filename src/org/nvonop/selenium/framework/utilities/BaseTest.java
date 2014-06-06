package org.nvonop.selenium.framework.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
public class BaseTest {

	private static final Logger LOGGER = Logger.getLogger(BaseTest.class
			.getName());

	/**
	 * A ThreadLocal instance of a WebDriver object. It should be thread safe
	 * and enable tests to run in parallel.
	 */
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	/**
	 * This method closes the browser associated with the ThreadLocal WebDriver
	 * object. It also the WebDriver instance for the ThreadLocal value to null.
	 */
	public void closeWebdriver() {
		driver.get().quit();
		driver.set(null);
	}

	/**
	 * This method initialises the ThreadLocal WebDriver object according to the
	 * supplied parameters to enable tests to run on a users local machine.
	 * 
	 * @param browser
	 *            This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version
	 *            This should be set to reflect the browser required. It is only
	 *            used when matching capabilities for a RemoteWebDriver object
	 *            however.
	 * @param platform
	 *            This should be set to whatever platform the test is required
	 *            to run on e.g. "WINDOWS", "MAC", "LINUX". It is only used when
	 *            matching capabilities for a RemoteWebDriver object however.
	 */
	public void setLocalWebdriver(String browser, String version,
			String platform) {

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
	 * This method initialises the ThreadLocal WebDriver object according to the
	 * supplied parameters to enable tests to run on Sauce Labs cloud
	 * infrastructure.
	 * 
	 * @param browser
	 *            This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version
	 *            This should be set to reflect the browser required. It is only
	 *            used when matching capabilities for a RemoteWebDriver object
	 *            however.
	 * @param platform
	 *            This should be set to whatever platform the test is required
	 *            to run on e.g. "WINDOWS", "MAC", "LINUX". It is only used when
	 *            matching capabilities for a RemoteWebDriver object however.
	 */
	public void setSauceWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

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
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform);

		try {
			this.driver.set(new RemoteWebDriver(new URL(System
					.getProperty("SAUCE_KEY")), capabilities));
		} catch (MalformedURLException e) {
			LOGGER.log(Level.INFO,
					"MalformedURLException in setSauceWebdriver() method", e);
		}
	}

	/**
	 * This method initialises the ThreadLocal WebDriver object according to the
	 * supplied parameters to enable tests to run remotely on a grid of test
	 * machines.
	 * 
	 * @param browser
	 *            This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version
	 *            This should be set to reflect the browser required. It is only
	 *            used when matching capabilities for a RemoteWebDriver object
	 *            however.
	 * @param platform
	 *            This should be set to whatever platform the test is required
	 *            to run on e.g. "WINDOWS", "MAC", "LINUX". It is only used when
	 *            matching capabilities for a RemoteWebDriver object however.
	 */
	public void setRemoteWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (getBrowserId(browser)) {
		case 0:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("version", version);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			break;
		case 1:
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("version", version);
			break;
		case 2:
			capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("version", version);
			break;
		case 3:
			capabilities = DesiredCapabilities.chrome();
			break;
		default:
			throw new WebDriverException("Browser not found: "+browser);
		}
		capabilities.setCapability("javascriptEnabled", true);
		capabilities.setCapability("platform", platform);

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
	 * This method provides a way of initialising a ThreadLocal WebDriver object
	 * in a consistent way regardless of where the test will run. The following
	 * system properties must be set to "true" or "false" for this method to
	 * function correctly: LOCAL_DRIVER, REMOTE_DRIVER, SAUCE_DRIVER. Setting
	 * one of the properties to "true" will ensure the ThreadLocal WebDriver
	 * object is initialised appropriately.
	 * 
	 * @param browser
	 *            This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version
	 *            This should be set to reflect the browser required. It is only
	 *            used when matching capabilities for a RemoteWebDriver object
	 *            however.
	 * @param platform
	 *            This should be set to whatever platform the test is required
	 *            to run on e.g. "WINDOWS", "MAC", "LINUX". It is only used when
	 *            matching capabilities for a RemoteWebDriver object however.
	 */
	public void setWebDriver(String browser, String version, String platform) {
		if (Boolean.valueOf(System.getProperty("LOCAL_DRIVER"))) {
			setLocalWebdriver(browser, version, platform);
		} else if (Boolean.valueOf(System.getProperty("REMOTE_DRIVER"))) {
			setRemoteWebdriver(browser, version, platform);
		} else if (Boolean.valueOf(System.getProperty("SAUCE_DRIVER"))) {
			setSauceWebdriver(browser, version, platform);
		} else {
			throw new WebDriverException("Type of driver not specified!!!");
		}
		this.driver.get().manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get().manage().window().maximize();
	}
	
	/**
	 * This method returns a ,ThreadLocal> WebDriver object
	 * 
	 * @param browser
	 *            This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version
	 *            This should be set to reflect the browser required. It is only
	 *            used when matching capabilities for a RemoteWebDriver object
	 *            however.
	 * @param platform
	 *            This should be set to whatever platform the test is required
	 *            to run on e.g. "WINDOWS", "MAC", "LINUX". It is only used when
	 *            matching capabilities for a RemoteWebDriver object however.
	 */
	public WebDriver getWebDriver() {
		return driver.get();
	}

}
