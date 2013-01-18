package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;


public class ExampleTests extends BaseTest {
  
  @Test
  public void OpenApplicationAndCheckAlertAppears() {
	  driver.get("http://developer.yahoo.com/yui/examples/layout/adv_layout_source.html");
  }
  

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
}
