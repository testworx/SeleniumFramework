package framework.controls;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.controls.BaseControl;

public class Table extends BaseControl {

	public Table(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);
	}

	public int countRows() {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		return rows.size();
	}

	public int countColumnsForRow(int row) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		WebElement tableRow = rows.get(row - 1);

		List<WebElement> columns = tableRow.findElements(By.tagName("td"));

		return columns.size();
	}

	public String readHeader(int column) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		WebElement tableRow = rows.get(0);

		List<WebElement> headers = tableRow.findElements(By.tagName("th"));

		WebElement header = headers.get(column - 1);

		return header.getText();
	}

	public String readCell(int row, int column) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		WebElement tableRow = rows.get(row - 1);

		List<WebElement> columns = tableRow.findElements(By.tagName("td"));

		WebElement tableCell = columns.get(column - 1);

		return tableCell.getText();
	}

}
