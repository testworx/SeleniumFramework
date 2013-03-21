package test.java;

import java.io.File;

import main.java.controls.Button;
import main.java.controls.CheckBox;
import main.java.controls.Link;
import main.java.controls.RadioButton;
import main.java.controls.SelectBox;
import main.java.controls.Table;
import main.java.controls.TextBox;
import main.java.utils.Basetest;
import main.java.utils.ObjectMap;
import main.java.utils.TestHelper;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ControlTests extends Basetest {

	TestHelper testHelper;
	ObjectMap map;
	private StringBuffer verificationErrors;

	@Test(groups = { "frameworkTests" })
	public void CheckTestPageOpens() {
		AssertJUnit.assertEquals(driver.getTitle(), "Controls Page Title");
	}

	@Test(groups = { "frameworkTests" })
	public void CheckSwitchToFrame() {
		try {
			testHelper.SwitchToFrame(map.getLocator("iframe_1"));
			WebElement message = driver.findElement(map
					.getLocator("button_1_click_message"));
			Button button = new Button(driver, map.getLocator("button_1"));
			button.click();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButtonAndClick() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("button_1_click_message"));
			Button button = new Button(driver, map.getLocator("button_1"));
			button.click();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButton2AndClick() {

		try {
			WebElement message = driver.findElement(map
					.getLocator("button_2_click_message"));
			Button button = new Button(driver, map.getLocator("button_2"));
			button.click();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindAlertAndClose() {
		try {
			Button button = new Button(driver, map.getLocator("alert_button"));
			button.click();
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			alert.accept();
			AssertJUnit.assertEquals(alertText, "Alert button clicked.");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButtonAndClickThenCheckElementDisappears() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("button_2_click_message"));
			Button button = new Button(driver, map.getLocator("button_2"));
			button.click();
			AssertJUnit.assertTrue(message.isDisplayed());
			Button button2 = new Button(driver, map.getLocator("button_4"));
			button2.click();
			AssertJUnit.assertFalse(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindRadioButtonAndClick() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("radio_button_1_click_message"));
			RadioButton radiobutton = new RadioButton(driver,
					map.getLocator("radio_button_1"));
			radiobutton.click();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindRadioButton2AndClick() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("radio_button_2_click_message"));
			RadioButton radiobutton = new RadioButton(driver,
					map.getLocator("radio_button_2"));
			radiobutton.click();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindCheckboxAndSelect() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("checkbox_1_click_message"));
			CheckBox checkbox = new CheckBox(driver,
					map.getLocator("checkbox_1"));
			checkbox.select();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindCheckbox2AndClick() {
		try {
			WebElement message = driver.findElement(map
					.getLocator("checkbox_2_click_message"));
			CheckBox checkbox = new CheckBox(driver,
					map.getLocator("checkbox_2"));
			checkbox.click();
			AssertJUnit.assertTrue(message.isDisplayed());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindLinkAndClick() {
		try {
			Link link = new Link(driver, map.getLocator("link_google"));
			link.click();
			testHelper.SwitchToWindow("Google");
			AssertJUnit.assertEquals(driver.getTitle(), "Google");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndCountRows() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			AssertJUnit.assertEquals(table.countRows(), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndCountColumns() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			AssertJUnit.assertEquals(table.countColumnsForRow(2), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndReadCell() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			AssertJUnit.assertEquals(table.readCell(3, 3), "row 2, cell 3");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndReadHeader() {
		try {
			Table table = new Table(driver, map.getLocator("table_1"));
			AssertJUnit.assertEquals(table.readHeader(1), "Column 1");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTextBoxAndTypeText() {
		try {
			TextBox firstName = new TextBox(driver,
					map.getLocator("text_firstname"));
			firstName.write("Nick");
			AssertJUnit.assertEquals(firstName.readValue(), "Nick");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTextBoxAndReadValue() {
		try {
			TextBox lastName = new TextBox(driver,
					map.getLocator("text_lastname"));
			AssertJUnit.assertEquals(lastName.readValue(), "");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndReadValue() {
		try {
			SelectBox cars = new SelectBox(driver,
					map.getLocator("select_cars"));
			AssertJUnit.assertEquals(cars.readValue(), "Fiat");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByText() {
		try {
			SelectBox cars = new SelectBox(driver,
					map.getLocator("select_cars"));
			cars.selectOptionByVisibleText("Volvo");
			AssertJUnit.assertEquals(cars.readValue(), "Volvo");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByValue() {
		try {
			SelectBox cars = new SelectBox(driver,
					map.getLocator("select_cars"));
			cars.selectOptionByValue("volvo");
			AssertJUnit.assertEquals(cars.readValue(), "Volvo");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByIndex() {
		try {
			SelectBox cars = new SelectBox(driver,
					map.getLocator("select_cars"));
			cars.selectOptionByIndex(3);
			AssertJUnit.assertEquals(cars.readValue(), "Audi");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSubmitButtonAndClick() {
		try {
			Button submitButton = new Button(driver,
					map.getLocator("button_submit"));
			submitButton.click();
			AssertJUnit.assertEquals(driver.getTitle(), "Submitted Page Title");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckSubmitForm() {
		try {
			Button submitButton = new Button(driver,
					map.getLocator("button_submit"));
			WebElement element = submitButton.getUnderlyingWebElement();
			element.submit();
			AssertJUnit.assertEquals(driver.getTitle(), "Submitted Page Title");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	// @Test(groups = {"frameworkTests"})(dataProvider = "urlProvider")
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

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {
		verificationErrors = new StringBuffer();
		verificationErrors.append("");
		File file = new File("src/test/resources/controls_page.html");

		map = new ObjectMap("ControlsPageObjectMap.properties");
		// TODO data drive the driver setup
		SetWebDriver(BROWSER, VERSION, PLATFORM);
		driver.get("http://selenium-framework.site44.com/controls_page.html");
		testHelper = new TestHelper(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		if (driver != null) {
			closeWebdriver();
		}
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {

			// System.out.println(verificationErrorString);
		}
	}
}
