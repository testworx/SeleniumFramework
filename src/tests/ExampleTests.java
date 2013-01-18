package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.Homepage;
import pages.HomepageAlert;
import utils.BaseTest;


public class ExampleTests extends BaseTest {
  
	  @Test(dataProvider = "urlProvider")
	  public void OpenApplicationAndCheckAlertDisappears(Integer dataRecord, String url) {
		  HomepageAlert homepageAlert = new HomepageAlert(driver, url);
		  homepageAlert.get();
		  homepageAlert.closeAlert();
		  Assert.assertFalse(homepageAlert.initialAlert.exists());
	  }
	
  @Test(dataProvider = "urlProvider")
  public void OpenApplicationAndCheckAlertAppears(Integer dataRecord, String url) {
	  HomepageAlert homepageAlert = new HomepageAlert(driver, url);
	  homepageAlert.get();
	  Assert.assertTrue(homepageAlert.initialAlert.exists());
  }
  

  

  @DataProvider(name = "urlProvider")
  public Object[][] dp() {
    return new Object[][] {
      new Object[] {new Integer(1), "http://developer.yahoo.com/yui/examples/layout/adv_layout_source.html" }
    };
  }
  
  @BeforeMethod
  public void beforeTest() {
	  setLocalWebdriver("firefox", "17", "mac");
  }

  @AfterMethod
  public void afterTest() {
	  closeDriver();
		  
	  }
}
