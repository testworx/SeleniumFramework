package framework.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	public WebDriver driver = null;
	protected static String APPLICATION_URL = System.getProperty(
			"APPLICATION_URL", "http://www.google.com/");
	protected static String BROWSER = System.getProperty("BROWSER",
			"firefox");
	protected static String VERSION = System.getProperty("VERSION", "19");
	protected static String PLATFORM = System.getProperty("PLATFORM",
			"MAC");
	protected static boolean LOCAL_DRIVER = Boolean.valueOf(System
			.getProperty("LOCAL_DRIVER", "true"));
	protected static boolean REMOTE_DRIVER = Boolean.valueOf(System
			.getProperty("REMOTE_DRIVER", "false"));
	protected static boolean SAUCE_DRIVER = Boolean.valueOf(System
			.getProperty("SAUCE_DRIVER", "false"));
	protected static String GRID_HOST = System.getProperty("GRID_HOST",
			"localhost");
	protected static int GRID_PORT = Integer.valueOf(System.getProperty(
			"GRID_PORT", "4444"));
	protected static String SAUCE_KEY = System.getProperty(
			"SAUCE_KEY", "http://USER:TOKEN@ondemand.saucelabs.com:80/wd/hub");
	protected static String TEST_RESULTS_PATH = System.getProperty(
			"TEST_RESULTS_PATH", "~");

	@BeforeClass
	public void beforeClass() {

	}

	@AfterClass
	protected void afterClass() {
	}

	protected void closeWebdriver() {
		driver.quit();
	}

	public void setLocalWebdriver(String browser, String version,
			String platform) {
		switch (browser.toLowerCase()) {
		case "internet explorer":
			driver = new InternetExplorerDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		}
	}

	public void setSauceWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (browser.toLowerCase()) {
		case "internet explorer":
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("version", version);
			break;
		case "firefox":
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("version", version);
			break;
		case "safari":
			capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("version", version);
			break;
		case "chrome":
			capabilities = DesiredCapabilities.chrome();
			break;
		}

		capabilities.setCapability("platform", platform);

		try {
			this.driver = new RemoteWebDriver(
					new URL(
							SAUCE_KEY),
					capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void setRemoteWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (browser.toLowerCase()) {
		case "internet explorer":
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("version", version);
			break;
		case "firefox":
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("version", version);
			break;
		case "safari":
			capabilities = DesiredCapabilities.safari();
			capabilities.setCapability("version", version);
			break;
		case "chrome":
			capabilities = DesiredCapabilities.chrome();
			break;
		}

		capabilities.setCapability("platform", platform);

		try {
			this.driver = new RemoteWebDriver(new URL("http://" + GRID_HOST
					+ ":" + GRID_PORT + "/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void setWebDriver(String browser, String version, String platform) {
		if (LOCAL_DRIVER) {
			setLocalWebdriver(browser, version, platform);
		} else if (REMOTE_DRIVER) {
			setRemoteWebdriver(browser, version, platform);
		} else if (SAUCE_DRIVER) {
			setSauceWebdriver(browser, version, platform);
		} else {
			throw new WebDriverException("Type of driver not specified!!!");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

}
