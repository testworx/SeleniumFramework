package framework.utilities;

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

public class BaseTest {

	public WebDriver driver = null;
	protected static String applicationUrl = System.getProperty(
			"APPLICATION_URL", "http://www.google.com/");
	protected static String browser = System.getProperty("BROWSER", "firefox");
	protected static String version = System.getProperty("VERSION", "19");
	protected static String platform = System.getProperty("PLATFORM", "MAC");
	protected static boolean localDriver = Boolean.valueOf(System.getProperty(
			"LOCAL_DRIVER", "true"));
	protected static boolean remoteDriver = Boolean.valueOf(System
			.getProperty("REMOTE_DRIVER", "false"));
	protected static boolean sauceDriver = Boolean.valueOf(System.getProperty(
			"SAUCE_DRIVER", "false"));
	protected static String gridHost = System.getProperty("GRID_HOST",
			"localhost");
	protected static int gridPort = Integer.valueOf(System.getProperty(
			"GRID_PORT", "4444"));
	protected static String sauceKey = System.getProperty("SAUCE_KEY",
			"http://USER:TOKEN@ondemand.saucelabs.com:80/wd/hub");
	protected static String testResultsPath = System.getProperty(
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
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("handlesAlerts", true);
		switch (getBrowserId(browser)) {
		case 0:
			throw new WebDriverException("Browser: " + browser + " not found.");
		case 1:
			driver = new InternetExplorerDriver(capabilities);
			break;
		case 2:
			driver = new FirefoxDriver(capabilities);
			break;
		case 3:
			driver = new SafariDriver(capabilities);
			break;
		case 4:
			driver = new ChromeDriver(capabilities);
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

	public void setSauceWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (getBrowserId(browser)) {
		case 0:
			throw new WebDriverException("Browser: " + browser + " not found.");
		case 1:
			capabilities = DesiredCapabilities.internetExplorer();
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
			this.driver = new RemoteWebDriver(new URL(sauceKey), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void setRemoteWebdriver(String browser, String version,
			String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (getBrowserId(browser)) {
		case 0:
			throw new WebDriverException("Browser: " + browser + " not found.");
		case 1:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("version", version);
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
			this.driver = new RemoteWebDriver(new URL("http://" + gridHost
					+ ":" + gridPort + "/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void setWebDriver(String browser, String version, String platform) {
		if (localDriver) {
			setLocalWebdriver(browser, version, platform);
		} else if (remoteDriver) {
			setRemoteWebdriver(browser, version, platform);
		} else if (sauceDriver) {
			setSauceWebdriver(browser, version, platform);
		} else {
			throw new WebDriverException("Type of driver not specified!!!");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

}
