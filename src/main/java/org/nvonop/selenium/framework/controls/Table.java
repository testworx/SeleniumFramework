/*
The MIT License (MIT)

Copyright (c) 2015 Nicholas Oppersdorff

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package org.nvonop.selenium.framework.controls;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class comprises of functionality that would be used when interacting
 * with a typical HTML table.
 * 
 * @author nvonop
 * 
 */
public class Table extends BaseControl {

	private static final Logger LOGGER = Logger
			.getLogger(Table.class.getName());

	/**
	 * Constructor that takes a WebDriver object and By object. These are then
	 * set in the base class.
	 * 
	 * @param driver
	 * @param locator
	 */
	public Table(WebDriver driver, By locator) {
		this.driver = driver;
		setLocator(locator);
	}

	/**
	 * This method counts the rows within the table.
	 * 
	 * @return an int for the row count
	 */
	public int countRows() {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		return rows.size();
	}

	/**
	 * This method counts the number of columns within a given row.
	 * 
	 * @param row
	 * @return an int for the column count
	 */
	public int countColumnsForRow(int row) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		WebElement tableRow = rows.get(row - 1);

		List<WebElement> columns = tableRow.findElements(By.tagName("td"));

		return columns.size();
	}

	/**
	 * This method reads the header for a given column.
	 * 
	 * @param column
	 * @return a String value for the column header
	 */
	public String readHeader(int column) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		WebElement tableRow = rows.get(0);

		List<WebElement> headers = tableRow.findElements(By.tagName("th"));

		WebElement header = headers.get(column - 1);

		return header.getText();
	}

	/**
	 * This method reads the contents of a given cell.
	 * 
	 * @param row
	 * @param column
	 * @return a String value for the cell contents
	 */
	public String readCell(int row, int column) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));

		WebElement tableRow = rows.get(row - 1);

		List<WebElement> columns = tableRow.findElements(By.tagName("td"));

		WebElement tableCell = columns.get(column - 1);

		return tableCell.getText();
	}

	/**
	 * This method iterates through every cell row by row searching for the
	 * given String value. Useful for finding particular values of interest in
	 * tables of variable size.
	 * 
	 * @param searcheableText
	 * @return an int[] containing the coordinates of the cell containing the
	 *         searcheableText
	 */
	public int[] searchTableForText(String searcheableText) {
		List<WebElement> rows = getUnderlyingWebElement().findElements(
				By.tagName("tr"));
		WebElement tableRow;
		List<WebElement> columns;
		String cellText = "";

		int[] cellReference = new int[2];

		for (int row = 1; row <= rows.size(); row++) {
			tableRow = rows.get(row - 1);
			columns = tableRow.findElements(By.tagName("td"));
			for (int column = 1; column <= columns.size(); column++) {
				try {
					cellText = readCell(row, column);
				} catch (IndexOutOfBoundsException e) {
					// We catch the exception caused by trying to read a bad
					// cell
					LOGGER.log(
							Level.INFO,
							"IndexOutOfBoundsException in searchTableForText() method",
							e);

				}
				if (cellText.contains(searcheableText)) {
					cellReference[0] = row;
					cellReference[1] = column;
					break;
				}
			}
		}
		return cellReference;
	}

}
