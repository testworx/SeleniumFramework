package org.nvonop.selenium.test.java;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.nvonop.selenium.framework.*;
import org.nvonop.selenium.framework.utilities.TestHelper;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ControlTests {

	private static final Logger LOGGER = Logger.getLogger(ControlTests.class
			.getName());

	TestHelper testHelper;
	private ControlsPage controlsPage;

	private Browser browser;
	
	@Test(groups = { "frameworkTests" })
	public void checkTestPageOpensAndTakeScreenshot() {
		try {
			browser.getScreenshot();
			Assert.assertEquals(browser.getPageTitle(), "Controls Page Title");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkTestPageOpensAndTakeScreenshot Test", e);
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindAlertAndClose() {
		try {
			controlsPage.getButton3().click();
			Alert alert = browser.switchToAlert();
			String alertText = alert.getText();
			alert.accept();
			Assert.assertEquals(alertText, "Alert button clicked.");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindAlertAndClose Test", e);
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindLinkAndClick() {
		try {
			controlsPage.getGoogle().click();
			browser.switchToWindow("Google");
			Assert.assertEquals(browser.getPageTitle(), "Google");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception in checkFindLinkAndClick Test",
					e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTableAndCountRows() {
		try {
			Assert.assertEquals(controlsPage.getTable().countRows(), 3);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTableAndCountRows Test", e);
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindTextBoxAndReadValue() {
		try {
			Assert.assertEquals(controlsPage.getLastName().read(), "");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindTextBoxAndReadValue Test", e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSelectListAndReadValue() {
		try {
			Assert.assertEquals(controlsPage.getCars().read(), "Fiat");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindSelectListAndReadValue Test", e);
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void checkFindSubmitButtonAndClick() {
		try {
			controlsPage.getSubmit().click();
			Assert.assertEquals(browser.getPageTitle(), "Submitted Page Title");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"Exception in checkFindSubmitButtonAndClick Test", e);
			Assert.fail(e.getMessage());
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
			Assert.fail(e.getMessage());
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {
//		System.setProperty("LOCAL_DRIVER", "true");
//		System.setProperty("REMOTE_DRIVER", "false");
//		System.setProperty("SAUCE_DRIVER", "false");
//		System.setProperty("SAUCE_KEY", "http://nvonop:a391bf40-5eb1-44f9-812a-c1ce5492a5c9@ondemand.saucelabs.com:80/wd/hub");
//		System.setProperty("BROWSER", "firefox");
//		System.setProperty("VERSION", "20");
//		System.setProperty("PLATFORM", "Windows 2008");
//		System.setProperty("APPLICATION_URL", "http://selenium-framework.site44.com/controls_page.html");
		browser = new Browser();
		browser.open();
		controlsPage = (ControlsPage) browser.navigate(ControlsPage.class);
		
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		browser.close();
	}
}
