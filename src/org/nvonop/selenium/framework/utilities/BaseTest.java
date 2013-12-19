package org.nvonop.selenium.framework.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * This class contains the base functionality for instantiating a thread safe web 
 * browser for a test.  Tests should be able to run in parallel with no issues.
 * @author nvonop
 *
 */
public class BaseTest {

	/**
	 * A ThreadLocal instance of a WebDriver object.  
	 * It should be thread safe and enable tests to run in parallel.
	 */
	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
//	protected static String applicationUrl = System.getProperty(
//			"APPLICATION_URL", "http://www.google.com/");
//	protected static String browser = System.getProperty("BROWSER", "firefox");
//	protected static String version = System.getProperty("VERSION", "19");
//	protected static String platform = System.getProperty("PLATFORM", "MAC");
//	protected static boolean localDriver = Boolean.valueOf(System.getProperty(
//			"LOCAL_DRIVER", "false"));
//	protected static boolean remoteDriver = Boolean.valueOf(System
//			.getProperty("REMOTE_DRIVER", "true"));
//	protected static boolean sauceDriver = Boolean.valueOf(System.getProperty(
//			"SAUCE_DRIVER", "false"));
//	protected static String gridHost = System.getProperty("GRID_HOST",
//			"localhost");
//	protected static int gridPort = Integer.valueOf(System.getProperty(
//			"GRID_PORT", "4444"));
//	protected static String sauceKey = System.getProperty("SAUCE_KEY",
//			"http://USER:TOKEN@ondemand.saucelabs.com:80/wd/hub");
//	protected static String testResultsPath = System.getProperty(
//			"TEST_RESULTS_PATH", "~");

	@BeforeClass
	protected void beforeClass() {
//		System.setProperty("LOCAL_DRIVER", "false");
//		System.setProperty("REMOTE_DRIVER", "true");
//		System.setProperty("SAUCE_DRIVER", "false");
//		
//		System.setProperty("BROWSER", "firefox");
//		System.setProperty("VERSION", "19");
//		System.setProperty("PLATFORM", "MAC");
//		
//		System.setProperty("GRID_HOST", "localhost");
//		System.setProperty("GRID_PORT", "4444");
	}

	@AfterClass
	protected void afterClass() {
	}

	/**
	 * This method closes the browser associated with the ThreadLocal WebDriver object.
	 * It also the WebDriver instance for the ThreadLocal value to null.
	 */
	public void closeWebdriver() {
		driver.get().quit();
		driver.set(null);
	}

	/**
	 * This method initialises the ThreadLocal WebDriver object according to the supplied parameters to enable tests to run on a users local machine.
	 * @param browser This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version This should be set to reflect the browser required.  It is only used when matching capabilities for a RemoteWebDriver object however.
	 * @param platform This should be set to whatever platform the test is required to run on e.g. "WINDOWS", "MAC", "LINUX".  It is only used when matching capabilities for a RemoteWebDriver object however.
	 */
	public void setLocalWebdriver(String browser, String version,
			String platform) {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("handlesAlerts", true);
		switch (getBrowserId(browser)) {
		case 0:
			throw new WebDriverException("Browser: " + browser + " not found.");
		case 1:
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			driver.set(new InternetExplorerDriver(capabilities));
			break;
		case 2:
			driver.set(new FirefoxDriver(capabilities));
			break;
		case 3:
			driver.set(new SafariDriver(capabilities));
			break;
		case 4:
			driver.set(new ChromeDriver(capabilities));
			break;
		}
	}

	private int getBrowserId(String browser) {
		if (browser.toLowerCase().contains("internet explorer")) {
			return 1;
		} else if (browser.toLowerCase().contains("firefox")) {
			return 2;
		} else if (browser.toLowerCase().contains("safari")) {
			return 3;
		} else if (browser.toLowerCase().contains("chrome")) {
			return 4;
		} else {
			return 0;
		}
	}

	/**
	 * This method initialises the ThreadLocal WebDriver object according to the supplied parameters to enable tests to run on Sauce Labs cloud infrastructure.
	 * @param browser This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version This should be set to reflect the browser required.  It is only used when matching capabilities for a RemoteWebDriver object however.
	 * @param platform This should be set to whatever platform the test is required to run on e.g. "WINDOWS", "MAC", "LINUX".  It is only used when matching capabilities for a RemoteWebDriver object however.
	 */
	public void setSauceWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (getBrowserId(browser)) {
		case 0:
			throw new WebDriverException("Browser: " + browser + " not found.");
		case 1:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			break;
		case 2:
			capabilities = DesiredCapabilities.firefox();
			break;
		case 3:
			capabilities = DesiredCapabilities.safari();
			break;
		case 4:
			capabilities = DesiredCapabilities.chrome();
			break;
		}
		capabilities.setCapability("javascriptEnabled", true);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform);

		try {
			this.driver.set(new RemoteWebDriver(new URL(System.getProperty("SAUCE_KEY")), capabilities));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initialises the ThreadLocal WebDriver object according to the supplied parameters to enable tests to run remotely on a grid of test machines.
	 * @param browser This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version This should be set to reflect the browser required.  It is only used when matching capabilities for a RemoteWebDriver object however.
	 * @param platform This should be set to whatever platform the test is required to run on e.g. "WINDOWS", "MAC", "LINUX".  It is only used when matching capabilities for a RemoteWebDriver object however.
	 */
	public void setRemoteWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (getBrowserId(browser)) {
		case 0:
			throw new WebDriverException("Browser: " + browser + " not found.");
		case 1:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("version", version);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, Boolean.valueOf(System.getProperty("IGNORE_SECURITY_DOMAINS", "false")));
			break;
		case 2:
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("version", version);
			break;
		case 3:
			capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("version", version);
			break;
		case 4:
			capabilities = DesiredCapabilities.chrome();
			break;
		}
		capabilities.setCapability("javascriptEnabled", true);
		capabilities.setCapability("platform", platform);

		try {
			this.driver.set(new RemoteWebDriver(new URL("http://" + System.getProperty("GRID_HOST")
					+ ":" + System.getProperty("GRID_PORT") + "/wd/hub"), capabilities));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method provides a way of initialising a ThreadLocal WebDriver object in a consistent way regardless of where the test will run.
	 * The following system properties must be set to "true" or "false" for this method to function correctly:  LOCAL_DRIVER, REMOTE_DRIVER, SAUCE_DRIVER.
	 * Setting one of the properties to "true" will ensure the ThreadLocal WebDriver object is initialised appropriately.
	 * @param browser This can be set to "chrome", "internet explorer" or "firefox"
	 * @param version This should be set to reflect the browser required.  It is only used when matching capabilities for a RemoteWebDriver object however.
	 * @param platform This should be set to whatever platform the test is required to run on e.g. "WINDOWS", "MAC", "LINUX".  It is only used when matching capabilities for a RemoteWebDriver object however.
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
		this.driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get().manage().window().maximize();
	}

}
