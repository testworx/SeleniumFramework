package test.java;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.utilities.BaseTest;
import framework.utilities.ObjectMap;
import framework.utilities.TestHelper;

public class ControlTests extends BaseTest {

	TestHelper testHelper;
	private StringBuffer verificationErrors;
	private ControlsPage controlsPage;

	@Test(groups = { "frameworkTests" })
	public void CheckTestPageOpensAndTakeScreenshot() {
		TestHelper.getScreenshot(TEST_RESULTS_PATH);
		Assert.assertEquals(driver.getTitle(), "Controls Page Title");
	}

	@Test(groups = { "frameworkTests" })
	public void CheckSwitchToFrame() {
		try {
			controlsPage.frame.switchToFrame();
			controlsPage.button1.click();
			Assert.assertTrue(controlsPage.message1.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButtonAndClick() {
		try {
			controlsPage.button1.click();
			Assert.assertTrue(controlsPage.message1.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButton2AndClick() {

		try {
			controlsPage.button2.click();
			Assert.assertTrue(controlsPage.message2.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindAlertAndClose() {
		try {
			controlsPage.button3.click();
			// TODO remove native selenium Alert code
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

	@Test(groups = { "frameworkTests" })
	public void CheckFindButtonAndClickThenCheckElementDisappears() {
		try {
			controlsPage.button2.click();
			Assert.assertTrue(controlsPage.message2.exists());
			controlsPage.button4.click();
			Assert.assertFalse(controlsPage.message2.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindRadioButtonAndClick() {
		try {
			controlsPage.male.click();
			Assert.assertTrue(controlsPage.message3.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindRadioButton2AndClick() {
		try {
			controlsPage.female.click();
			Assert.assertTrue(controlsPage.message4.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindCheckboxAndSelect() {
		try {
			controlsPage.bike.click();
			Assert.assertTrue(controlsPage.message5.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindCheckbox2AndClick() {
		try {
			controlsPage.car.click();
			Assert.assertTrue(controlsPage.message6.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindLinkAndClick() {
		try {
			controlsPage.google.click();
			TestHelper.switchToWindow("Google");
			Assert.assertEquals(driver.getTitle(), "Google");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndCountRows() {
		try {
			Assert.assertEquals(controlsPage.table.countRows(), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}
	
	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndSearchForText() {
		try {
			int row = 2;
			int column = 1;
			int[] tableCellCoordinates = controlsPage.table.searchTableForText("row 1, cell 1");
			Assert.assertEquals(tableCellCoordinates[0], row);
			Assert.assertEquals(tableCellCoordinates[1], column);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndCountColumns() {
		try {
			Assert.assertEquals(controlsPage.table.countColumnsForRow(2), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndReadCell() {
		try {
			Assert.assertEquals(controlsPage.table.readCell(3, 3),
					"row 2, cell 3");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndReadHeader() {
		try {
			Assert.assertEquals(controlsPage.table.readHeader(1), "Column 1");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTextBoxAndTypeText() {
		try {
			controlsPage.firstName.write("Nick");
			Assert.assertEquals(controlsPage.firstName.readValue(), "Nick");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTextBoxAndReadValue() {
		try {
			Assert.assertEquals(controlsPage.lastName.readValue(), "");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndReadValue() {
		try {
			Assert.assertEquals(controlsPage.cars.readValue(), "Fiat");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByText() {
		try {
			controlsPage.cars.selectOptionByVisibleText("Volvo");
			Assert.assertEquals(controlsPage.cars.readValue(), "Volvo");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByValue() {
		try {
			controlsPage.cars.selectOptionByValue("volvo");
			Assert.assertEquals(controlsPage.cars.readValue(), "Volvo");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByIndex() {
		try {
			controlsPage.cars.selectOptionByIndex(3);
			Assert.assertEquals(controlsPage.cars.readValue(), "Audi");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSubmitButtonAndClick() {
		try {
			controlsPage.submit.click();
			Assert.assertEquals("Submitted Page Title", driver.getTitle());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckSubmitForm() {
		try {
			SubmittedPage submittedPage = controlsPage.submitForm();
			Assert.assertEquals("Submitted Page Title", driver.getTitle());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {
		verificationErrors = new StringBuffer();
		verificationErrors.append("");
		setWebDriver(BROWSER, VERSION, PLATFORM);
		TestHelper.setDriver(driver, LOCAL_DRIVER, REMOTE_DRIVER, SAUCE_DRIVER);
		controlsPage = new ControlsPage(driver,
				"http://selenium-framework.site44.com/controls_page.html");
		controlsPage.get();
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
