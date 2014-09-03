package org.nvonop.selenium.test.groovy

import java.util.logging.Level
import java.util.logging.Logger

import org.nvonop.selenium.framework.Browser
import org.nvonop.selenium.framework.ObjectMap
import org.nvonop.selenium.framework.utilities.TestHelper
import org.nvonop.selenium.test.java.ControlsPage
import org.nvonop.selenium.test.java.SubmittedPage
import org.openqa.selenium.Alert
import org.openqa.selenium.WebDriver
import org.testng.Assert
import org.testng.annotations.Test

import spock.lang.*

class ControlsSpec  extends Specification {

	ObjectMap objectMap
	Browser browser
	WebDriver driver

	def setupSpec() {
//			System.setProperty("BROWSER", "Firefox")
//			System.setProperty("VERSION", "")
//			System.setProperty("PLATFORM", "")
//			System.setProperty("LOCAL_DRIVER", "true")
//			System.setProperty("REMOTE_DRIVER", "false")
//			System.setProperty("SAUCE_DRIVER", "false")
//			System.setProperty("webdriver.ie.driver", "C:\\Automation\\lib\\IEDriverServer.exe");
//			System.setProperty("IGNORE_SECURITY_DOMAINS", "true")
//			System.setProperty("TEST_RESULTS_PATH", "./build/Screenshots");
//			System.setProperty("TIMEOUT", "10")
//			System.setProperty("APPLICATION_URL", "http://selenium-framework.site44.com/controls_page.html")
	}

	def setup() {
		browser = new Browser()
		browser.open()
		controlsPage = (ControlsPage)browser.navigate(ControlsPage.class)
	}
	
	def cleanup() {
		browser.close()
	}

	def testHelper
	def controlsPage

	private Browser browser

	def "Check Test Page Opens And Take Screenshot"() {
		when: "we are on the Test page"
		browser.getScreenshot()
		then:
		browser.getPageTitle() == "Controls Page Title"
	}

	def "Check Switch To Frame"() {
		when: "we switch to the new frame"
		controlsPage.getFrame().switchToFrame()
		controlsPage.getButton1().click()
		then: "the message is displayed"
		controlsPage.getMessage1().exists()
	}

	def "Check Find Button And Click"() {
		when:
		controlsPage.getButton1().click()
		then:
		controlsPage.getMessage1().exists()
	}

	def "Check Find Button 2 And Click"() {
		when:
		controlsPage.getButton2().click()
		then:
		controlsPage.getMessage2().exists()
	}

	def "Check Find Alert And Close"() {
		when:
		controlsPage.getButton3().click()
		Alert alert = browser.switchToAlert()
		String alertText = alert.getText()
		alert.accept()
		then:
		alertText == "Alert button clicked."
	}

	def "Check Find Button And Click Then Check Element Disappears"() {
		when:
			controlsPage.getButton2().click()
		then:
			controlsPage.getMessage2().exists() == true
		when: 
			controlsPage.getButton4().click()
		then:
			controlsPage.getMessage2().exists() == false
	}

	def "Check Find Radio Button And Click"() {
		when:
		controlsPage.getMale().click()
		then:
		controlsPage.getMessage3().exists()
	}

	def "Check Find Radio Button 2 And Click"() {
		when:
		controlsPage.getFemale().click()
		then:
		controlsPage.getMessage4().exists()
	}

	def "Check Find Checkbox And Select"() {
		when:
		controlsPage.getBike().click()
		then:
		controlsPage.getMessage5().exists()
	}

	def "Check Find Checkbox 2 And Click"() {
		when:
		controlsPage.getCar().click()
		then:
		controlsPage.getMessage6().exists()
	}

	def "Check Find Link And Click"() {
		when:
		controlsPage.getGoogle().click()
		browser.switchToWindow("Google")
		then:
		browser.getPageTitle() == "Google"
	}

	def "Check Find Table And Count Rows"() {
		when: "we count the rows in the table"
		int noOfRows = controlsPage.getTable().countRows()
		then: "the number of rows is correctly counted"
		noOfRows == 3
	}

	def "Check Find Table And Search For Text"() {
		when: "we search the table for a particular String"
		int row = 2
		int column = 1
		int[] tableCellCoordinates = controlsPage.getTable()
				.searchTableForText("row 1, cell 1")
		then: "the coordinates are correctly returned"
		tableCellCoordinates[0] == row
		tableCellCoordinates[1] == column
	}

	def "Check Find Table And Count Columns"() {
		when: "we count the columns in the table row"
		int noOfColumns = controlsPage.getTable().countColumnsForRow(2)
		then: "the number of columns is counted correctly"
		noOfColumns == 3
	}

	def "Check Find Table And Read Cell"() {
		when: "we read the contents of a specific cell"
		String cellContents = controlsPage.getTable().readCell(3, 3)
		then: "the cell contents is read correctly"
		cellContents == "row 2, cell 3"
	}

	def "Check Find Table And Read Header"() {
		String columnHeader;
		when: "we read the header of a column"
		columnHeader = controlsPage.getTable().readHeader(1)
		then: "the column header is read correctly"
		columnHeader == "Column 1"
	}

	def "Check Find Text Box And Type Text"() {
		when:
		controlsPage.getFirstName().write("Nick")
		then:
		controlsPage.getFirstName().read() == "Nick"
	}

	def "Check Find Text Box And Read Value"() {
		when: "we read an empty text box"
		String textBoxContents = controlsPage.getLastName().read()
		then: "then contents is blank"
		textBoxContents == ""
	}

	def "Check Find Select List And Read Value"() {
		when: "we read the value of a select box"
		String selectedOption = controlsPage.getCars().read()
		then: "the value is read correctly"
		selectedOption == "Fiat"
	}

	def "Check Find Select List And Select Option By Text"() {
		when:
		controlsPage.getCars().selectOptionByVisibleText("Volvo")
		then:
		controlsPage.getCars().read() == "Volvo"
	}

	def "Check Find Select List And Select Option By Value"() {
		when:
		controlsPage.getCars().selectOptionByValue("volvo")
		then:
		controlsPage.getCars().read() == "Volvo"
	}

	def "Check Find Select List And Select Option By Index"() {
		when:
		controlsPage.getCars().selectOptionByIndex(3)
		then:
		controlsPage.getCars().read() == "Audi"
	}

	def "Check Find Submit Button And Click"() {
		when:
		controlsPage.getSubmit().click()
		then:
		browser.getPageTitle() == "Submitted Page Title"
	}

	def "Check Submit Form"() {
		when: "we submit the form"
		SubmittedPage submittedPage = controlsPage.submitForm()
		then:
		submittedPage.getTitle() == "Submitted Page Title"
	}

}
