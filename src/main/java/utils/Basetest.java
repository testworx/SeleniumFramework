package main.java.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Basetest {

	public WebDriver driver = null;
	private String runLocally;
	
  @BeforeClass
  public void beforeClass() {
	  runLocally = "true";
  }

  @AfterClass
  protected void afterClass() {
  }

 

  protected void closeDriver() {
	driver.quit();
  }
  
  public void setLocalWebdriver(String browser, String version, String platform) {
	  switch (browser.toLowerCase())
      {
          case "internet explorer":
              driver = new InternetExplorerDriver();
              break;
          case "firefox":
              driver = new FirefoxDriver();
              break;
      } 
  }
  
  public void setRemoteWebdriver(String browser, String version, String platform) {
	  
  }

public void SetWebDriver(String browser, String version, String platform) {
	if (runLocally.toLowerCase() == "true")
    {
        setLocalWebdriver(browser, version, platform);
    }
    else
    {
        setRemoteWebdriver(browser, version, platform);
    }
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
}


}
