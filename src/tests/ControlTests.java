package tests;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.xml.internal.bind.v2.TODO;

import controls.Button;
import controls.Link;
import controls.RadioButton;
import utils.Basetest;
import utils.TestHelper;



public class ControlTests extends Basetest {
  
	TestHelper testHelper;
	
	@Test
	  public void CheckTestPageOpens() { 
		  Assert.assertEquals(driver.getTitle(), "Controls Page Title");
	  }
	  
	@Test
	  public void CheckFindButtonByNameAndClick() { 
		  WebElement message1 = driver.findElement(By.id("pg1")); 
		  Button button1 = new Button(driver, By.name("button1"));
		  button1.click();
		  Assert.assertTrue(message1.isDisplayed());			  
	  }
	
	@Test
	  public void CheckFindButtonByIdAndClick() { 
		  WebElement message2 = driver.findElement(By.id("pg2")); 
		  Button button2 = new Button(driver, By.id("btn2"));
		  button2.click();
		  Assert.assertTrue(message2.isDisplayed());			  
	  }
	
	@Test
	  public void CheckFindButtonByCssAndClick() { 
		  Button button3 = new Button(driver, By.cssSelector("input#btn3"));
		  button3.click();
		  Alert alert = driver.switchTo().alert();
		  String alertText = alert.getText();
		  alert.dismiss();
		  Assert.assertEquals(alertText, "Alert button clicked.");	 
	  }

	@Test
	  public void CheckFindButtonByXpathAndClick() { 
		WebElement message2 = driver.findElement(By.id("pg2"));   
		Button button2 = new Button(driver, By.xpath("//*[@id=\"btn2\"]"));
		button2.click();
		  Assert.assertTrue(message2.isDisplayed());		
		  Button button4 = new Button(driver, By.xpath("//*[@id=\"btn4\"]"));
			button4.click();
			  Assert.assertFalse(message2.isDisplayed());	
	  }
	
	@Test
	  public void CheckFindRadioButtonByNameAndClick() { 
		  WebElement message = driver.findElement(By.id("pg3")); 
		  RadioButton radiobutton1 = new RadioButton(driver, By.name("sex"));
		  radiobutton1.click();
		  Assert.assertTrue(message.isDisplayed());			  
	  }
	
	@Test
	  public void CheckFindRadioButtonByIdAndClick() { 
		  WebElement message = driver.findElement(By.id("pg4")); 
		  RadioButton radiobutton1 = new RadioButton(driver, By.id("rb2"));
		  radiobutton1.click();
		  Assert.assertTrue(message.isDisplayed());			  
	  }
	
	@Test
	  public void CheckFindCheckboxByCssAndClick() { 
		  WebElement message = driver.findElement(By.id("pg5")); 
		  RadioButton checkbox = new RadioButton(driver, By.cssSelector("input#cb1"));
		  checkbox.click();
		  Assert.assertTrue(message.isDisplayed());			  
	  }
	
	@Test
	  public void CheckFindCheckboxByXpathAndClick() { 
		  WebElement message = driver.findElement(By.id("pg6")); 
		  RadioButton checkbox = new RadioButton(driver, By.xpath("//*[@id=\"cb2\"]"));
		  checkbox.click();
		  Assert.assertTrue(message.isDisplayed());			  
	  }
	
	@Test
	  public void CheckFindLinkByIdAndClick() { 
		  Link link = new Link(driver, By.id("lnk1"));
		  link.click();
		 testHelper.SwitchToWindow("Google");
		 Assert.assertEquals(driver.getTitle(), "Google");			  
	  }
	  
	
//	  @Test(dataProvider = "urlProvider")
//	  public void OpenTestPage(Integer dataRecord, String url) { 
//		  Assert.assertEquals(driver.getTitle(), "Controls Page Title");
//	  }

//  @DataProvider(name = "urlProvider")
//  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] {new Integer(1), "file:///Users/nvonop/Documents/Repos/SeleniumFramework/src/tests/controls_page.html" }
//    };
//  }
  
  @BeforeMethod
  public void beforeTest() {
	  setLocalWebdriver("firefox", "17", "mac");
	  driver.get("file:///Users/nvonop/Documents/Repos/SeleniumFramework/src/tests/controls_page.html");
      testHelper = new TestHelper(driver);
  }

  @AfterMethod
  public void afterTest() {
	  closeDriver();		  
  }
}
