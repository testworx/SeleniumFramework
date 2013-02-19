package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Basetest;
import utils.ObjectMap;
import utils.TestHelper;
import controls.Button;
import controls.Link;
import controls.RadioButton;
import controls.Table;

public class ControlTests extends Basetest {

	TestHelper testHelper;
	ObjectMap map;
	private StringBuffer verificationErrors;

	@Test
	public void CheckTestPageOpens() {
		Assert.assertEquals(driver.getTitle(), "Controls Page Title");
	}

	@Test
	public void CheckFindButtonAndClick() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("button_1_click_message"));
			Button button = new Button(driver, map.getLocator("button_1"));
			button.click();
			Assert.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindButton2AndClick() {

		try {
			WebElement message = driver.findElement(map
					.getLocator("button_2_click_message"));
			Button button = new Button(driver, map.getLocator("button_2"));
			button.click();
			Assert.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindAlertAndClose() {
		try {
			Button button = new Button(driver, map.getLocator("alert_button"));
			button.click();
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			alert.accept();
			Assert.assertEquals(alertText, "Alert button clicked.");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindButtonAndClickThenCheckElementDisappears() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("button_2_click_message"));
			Button button = new Button(driver, map.getLocator("button_2"));
			button.click();
			Assert.assertTrue(message.isDisplayed());
			Button button2 = new Button(driver, map.getLocator("button_4"));
			button2.click();
			Assert.assertFalse(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindRadioButtonAndClick() {
		try {
			WebElement message = driver.findElement(map.getLocator("radio_button_1_click_message"));
			RadioButton radiobutton = new RadioButton(driver, map.getLocator("radio_button_1"));
			radiobutton.click();
			Assert.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindRadioButton2AndClick() {
		try {
			WebElement message = driver.findElement(map.getLocator("radio_button_2_click_message"));
			RadioButton radiobutton = new RadioButton(driver, map.getLocator("radio_button_2"));
			radiobutton.click();
			Assert.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindCheckboxAndClick() {
		try {
			WebElement message = driver.findElement(map.getLocator("checkbox_1_click_message"));
			RadioButton checkbox = new RadioButton(driver, map.getLocator("checkbox_1"));
			checkbox.click();
			Assert.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindCheckbox2AndClick() {
		try {
			WebElement message = driver.findElement(map.getLocator("checkbox_2_click_message"));
			RadioButton checkbox = new RadioButton(driver, map.getLocator("checkbox_2"));
			checkbox.click();
			Assert.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test
	public void CheckFindLinkAndClick() {
		try {
			Link link = new Link(driver, map.getLocator("link_google"));
			link.click();
			testHelper.SwitchToWindow("Google");
			Assert.assertEquals(driver.getTitle(), "Google");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}
	
	@Test
	public void CheckFindTableAndCountRows() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			Assert.assertEquals(table.countRows(), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}
	
	@Test
	public void CheckFindTableAndCountColumns() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			Assert.assertEquals(table.countColumnsForRow(2), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}
	
	@Test
	public void CheckFindTableAndReadCell() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			Assert.assertEquals(table.readCell(3,3), "row 2, cell 3");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}
	
	@Test
	public void CheckFindTableAndReadHeader() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			Assert.assertEquals(table.readHeader(1), "Column 1");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	// @Test(dataProvider = "urlProvider")
	// public void OpenTestPage(Integer dataRecord, String url) {
	// Assert.assertEquals(driver.getTitle(), "Controls Page Title");
	// }

	// @DataProvider(name = "urlProvider")
	// public Object[][] dp() {
	// return new Object[][] {
	// new Object[] {new Integer(1),
	// "file:///Users/nvonop/Documents/Repos/SeleniumFramework/src/tests/controls_page.html"
	// }
	// };
	// }

	@BeforeMethod
	public void beforeTest() {
		verificationErrors = new StringBuffer();
		verificationErrors.append("");
		map = new ObjectMap("ControlsPageObjectMap.properties");
		setLocalWebdriver("firefox", "17", "mac");
		driver.get("file:///Users/nvonop/Documents/Repos/SeleniumFramework/src/tests/controls_page.html");
		testHelper = new TestHelper(driver);
	}

	@AfterMethod
	public void afterTest() {
		closeDriver();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			
			//System.out.println(verificationErrorString);
		}
	}
}
