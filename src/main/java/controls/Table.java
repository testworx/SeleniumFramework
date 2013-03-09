package main.java.controls;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Table extends BaseControl  {

	public Table(WebDriver driver, By locator) {
		baseWebElement = findControl(driver, locator);
		System.out.println("WebElement found.");
	}

	public int countRows() {
		List<WebElement> rows =
			    baseWebElement.findElements(By.tagName("tr"));
		
		return rows.size();
	}
	
	public int countColumnsForRow(int row) {
		List<WebElement> rows =
			    baseWebElement.findElements(By.tagName("tr"));
		
		WebElement tableRow = rows.get(row - 1);
		
		List<WebElement> columns =
			    tableRow.findElements(By.tagName("td"));
		
		return columns.size();		
	}
	
	public String readHeader(int column) {
		List<WebElement> rows =
			    baseWebElement.findElements(By.tagName("tr"));
		
		WebElement tableRow = rows.get(0);
		
		List<WebElement> headers =
			    tableRow.findElements(By.tagName("th"));
		
		WebElement header = headers.get(column - 1);	
		
		return header.getText();
	}
	
	public String readCell(int row, int column) {
		List<WebElement> rows =
			    baseWebElement.findElements(By.tagName("tr"));
		
		WebElement tableRow = rows.get(row - 1);
		
		List<WebElement> columns =
			    tableRow.findElements(By.tagName("td"));
		
		WebElement tableCell = columns.get(column - 1);
		
		return tableCell.getText();
	}
		
	}

	

