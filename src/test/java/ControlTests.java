package test.java;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.utilities.Basetest;
import framework.utilities.ObjectMap;
import framework.utilities.TestHelper;

public class ControlTests extends Basetest {

	TestHelper testHelper;
	ObjectMap map;
	private StringBuffer verificationErrors;
	private ControlsPage controlsPage;

	@Test(groups = { "frameworkTests" })
	public void CheckTestPageOpens() {
		AssertJUnit.assertEquals(driver.getTitle(), "Controls Page Title");
	}

	@Test(groups = { "frameworkTests" })
	public void CheckSwitchToFrame() {
		try {
			controlsPage.frame.switchToFrame();
			controlsPage.button1.click();
			AssertJUnit.assertTrue(controlsPage.message1.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButtonAndClick() {
		try {
			controlsPage.button1.click();
			AssertJUnit.assertTrue(controlsPage.message1.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindButton2AndClick() {

		try {
			controlsPage.button2.click();
			AssertJUnit.assertTrue(controlsPage.message2.exists());
		} catch (Exception e) {
		// Capture and append Exceptions/Errors
		verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindAlertAndClose() {
		try {
			controlsPage.button3.click();
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
			controlsPage.button2.click();
			AssertJUnit.assertTrue(controlsPage.message2.exists());
			controlsPage.button4.click();
			AssertJUnit.assertFalse(controlsPage.message2.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindRadioButtonAndClick() {
//		try {
			controlsPage.male.click();
			AssertJUnit.assertTrue(controlsPage.message3.exists());
//		} catch (Exception e) {
//			// Capture and append Exceptions/Errors
//			verificationErrors.append(e.toString());
//			AssertJUnit.fail(verificationErrors.toString());
//		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindRadioButton2AndClick() {
		try {
			controlsPage.female.click();
			AssertJUnit.assertTrue(controlsPage.message4.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindCheckboxAndSelect() {
		try {
			controlsPage.bike.click();
			AssertJUnit.assertTrue(controlsPage.message5.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindCheckbox2AndClick() {
		try {
			controlsPage.car.click();
			AssertJUnit.assertTrue(controlsPage.message6.exists());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindLinkAndClick() {
//		try {
			controlsPage.google.click();
			TestHelper.switchToWindow("Google");
			AssertJUnit.assertEquals(driver.getTitle(), "Google");
//		} catch (Exception e) {
//			// Capture and append Exceptions/Errors
//			verificationErrors.append(e.toString());
//			AssertJUnit.fail(verificationErrors.toString());
//		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndCountRows() {
		try {	
			AssertJUnit.assertEquals(controlsPage.table.countRows(), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndCountColumns() {
		try {
			AssertJUnit.assertEquals(controlsPage.table.countColumnsForRow(2), 3);
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndReadCell() {
		try {
			AssertJUnit.assertEquals(controlsPage.table.readCell(3, 3), "row 2, cell 3");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTableAndReadHeader() {
		try {
			AssertJUnit.assertEquals(controlsPage.table.readHeader(1), "Column 1");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTextBoxAndTypeText() {
		try {
			controlsPage.firstName.write("Nick");
			AssertJUnit.assertEquals(controlsPage.firstName.readValue(), "Nick");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindTextBoxAndReadValue() {
		try {
			AssertJUnit.assertEquals(controlsPage.lastName.readValue(), "");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndReadValue() {
		try {
			AssertJUnit.assertEquals(controlsPage.cars.readValue(), "Fiat");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByText() {
		try {
			controlsPage.cars.selectOptionByVisibleText("Volvo");
			AssertJUnit.assertEquals(controlsPage.cars.readValue(), "Volvo");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByValue() {
		try {
			controlsPage.cars.selectOptionByValue("volvo");
			AssertJUnit.assertEquals(controlsPage.cars.readValue(), "Volvo");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSelectListAndSelectOptionByIndex() {
		try {
			controlsPage.cars.selectOptionByIndex(3);
			AssertJUnit.assertEquals(controlsPage.cars.readValue(), "Audi");
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckFindSubmitButtonAndClick() {
		try {
			controlsPage.submit.click();
			AssertJUnit.assertEquals("Submitted Page Title", driver.getTitle());
		} catch (Exception e) {
			// Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
			AssertJUnit.fail(verificationErrors.toString());
		}
	}

	@Test(groups = { "frameworkTests" })
	public void CheckSubmitForm() {
		try {
			SubmittedPage submittedPage = controlsPage.submitForm();
			AssertJUnit.assertEquals("Submitted Page Title", driver.getTitle());
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
		map = new ObjectMap("ControlsPageObjectMap.properties");
		setWebDriver(BROWSER, VERSION, PLATFORM);
		TestHelper.setDriver(driver);
		 controlsPage = new ControlsPage(driver, "http://selenium-framework.site44.com/controls_page.html");
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
