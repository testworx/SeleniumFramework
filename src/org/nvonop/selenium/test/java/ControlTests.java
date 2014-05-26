package org.nvonop.selenium.test.java;

import org.nvonop.selenium.framework.utilities.BaseTest;
import org.nvonop.selenium.framework.utilities.TestHelper;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.logging.*;

public class ControlTests extends BaseTest {

	private static final Logger LOGGER = Logger.getLogger(ControlTests.class
			.getName());

	TestHelper testHelper;
	private StringBuilder verificationErrors;
	private ControlsPage controlsPage;

	@Test(groups = { "frameworkTests" })
	public void checkTestPageOpensAndTakeScreenshot() {
		try {
			TestHelper.getScreenshot();
			Assert.assertEquals(driver.get().getTitle(), "Controls Page Title");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkTestPageOpensAndTakeScreenshot Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkSwitchToFrame() {
		try {
			controlsPage.getFrame().switchToFrame();
			controlsPage.getButton1().click();
			Assert.assertTrue(controlsPage.getMessage1().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception in checkSwitchToFrame Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindButtonAndClick() {
		try {
			controlsPage.getButton1().click();
			Assert.assertTrue(controlsPage.getMessage1().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindButtonAndClick Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindButton2AndClick() {

		try {
			controlsPage.getButton2().click();
			Assert.assertTrue(controlsPage.getMessage2().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindButton2AndClick Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindAlertAndClose() {
		try {
			controlsPage.getButton3().click();
			Alert alert = driver.get().switchTo().alert();
			String alertText = alert.getText();
			alert.accept();
			Assert.assertEquals(alertText, "Alert button clicked.");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindAlertAndClose Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindButtonAndClickThenCheckElementDisappears() {
		try {
			controlsPage.getButton2().click();
			Assert.assertTrue(controlsPage.getMessage2().exists());
			controlsPage.getButton4().click();
			Assert.assertFalse(controlsPage.getMessage2().exists());
		} catch (Exception e) {
			LOGGER.log(
					Level.SEVERE,
					"Exception in checkFindButtonAndClickThenCheckElementDisappears Test",
					e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindRadioButtonAndClick() {
		try {
			controlsPage.getMale().click();
			Assert.assertTrue(controlsPage.getMessage3().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindRadioButtonAndClick Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindRadioButton2AndClick() {
		try {
			controlsPage.getFemale().click();
			Assert.assertTrue(controlsPage.getMessage4().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindRadioButton2AndClick Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindCheckboxAndSelect() {
		try {
			controlsPage.getBike().click();
			Assert.assertTrue(controlsPage.getMessage5().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindCheckboxAndSelect Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindCheckbox2AndClick() {
		try {
			controlsPage.getCar().click();
			Assert.assertTrue(controlsPage.getMessage6().exists());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindCheckbox2AndClick Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindLinkAndClick() {
		try {
			controlsPage.getGoogle().click();
			TestHelper.switchToWindow("Google");
			Assert.assertEquals(driver.get().getTitle(), "Google");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception in checkFindLinkAndClick Test",
					e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTableAndCountRows() {
		try {
			Assert.assertEquals(controlsPage.getTable().countRows(), 3);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTableAndCountRows Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTableAndSearchForText() {
		try {
			int row = 2;
			int column = 1;
			int[] tableCellCoordinates = controlsPage.getTable()
					.searchTableForText("row 1, cell 1");
			Assert.assertEquals(tableCellCoordinates[0], row);
			Assert.assertEquals(tableCellCoordinates[1], column);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTableAndSearchForText Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTableAndCountColumns() {
		try {
			Assert.assertEquals(controlsPage.getTable().countColumnsForRow(2),
					3);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTableAndCountColumns Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTableAndReadCell() {
		try {
			Assert.assertEquals(controlsPage.getTable().readCell(3, 3),
					"row 2, cell 3");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTableAndReadCell Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTableAndReadHeader() {
		try {
			Assert.assertEquals(controlsPage.getTable().readHeader(1),
					"Column 1");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTableAndReadHeader Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTextBoxAndTypeText() {
		try {
			controlsPage.getFirstName().write("Nick");
			Assert.assertEquals(controlsPage.getFirstName().read(), "Nick");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTextBoxAndTypeText Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTextBoxAndReadValue() {
		try {
			Assert.assertEquals(controlsPage.getLastName().read(), "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTextBoxAndReadValue Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSelectListAndReadValue() {
		try {
			Assert.assertEquals(controlsPage.getCars().read(), "Fiat");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindSelectListAndReadValue Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSelectListAndSelectOptionByText() {
		try {
			controlsPage.getCars().selectOptionByVisibleText("Volvo");
			Assert.assertEquals(controlsPage.getCars().read(), "Volvo");
		} catch (Exception e) {
			LOGGER.log(
					Level.SEVERE,
					"Exception in checkFindSelectListAndSelectOptionByText Test",
					e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSelectListAndSelectOptionByValue() {
		try {
			controlsPage.getCars().selectOptionByValue("volvo");
			Assert.assertEquals(controlsPage.getCars().read(), "Volvo");
		} catch (Exception e) {
			LOGGER.log(
					Level.SEVERE,
					"Exception in checkFindSelectListAndSelectOptionByValue Test",
					e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSelectListAndSelectOptionByIndex() {
		try {
			controlsPage.getCars().selectOptionByIndex(3);
			Assert.assertEquals(controlsPage.getCars().read(), "Audi");
		} catch (Exception e) {
			LOGGER.log(
					Level.SEVERE,
					"Exception in checkFindSelectListAndSelectOptionByIndex Test",
					e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSubmitButtonAndClick() {
		try {
			controlsPage.getSubmit().click();
			Assert.assertEquals("Submitted Page Title", driver.get().getTitle());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindSubmitButtonAndClick Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkSubmitForm() {
		try {
			SubmittedPage submittedPage = controlsPage.submitForm();
			Assert.assertEquals(submittedPage.getTitle(),
					"Submitted Page Title");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception in checkSubmitForm Test", e);
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			Assert.fail(verificationErrors.toString());
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {
		verificationErrors = new StringBuilder();
		verificationErrors.append("");
		setWebDriver(System.getProperty("BROWSER"),
				System.getProperty("VERSION"), System.getProperty("PLATFORM"));
		TestHelper.setDriver(driver.get());
		controlsPage = new ControlsPage(driver.get(),
				"http://selenium-framework.site44.com/controls_page.html");
		controlsPage.get();
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		if (driver.get() != null) {
			closeWebdriver();
		}
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			LOGGER.log(Level.SEVERE, "There were errors running the test: "
					+ verificationErrorString);
		}
	}
}
