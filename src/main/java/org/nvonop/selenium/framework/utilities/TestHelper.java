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

package org.nvonop.selenium.framework.utilities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author nvonop This class is intended to be a general utility class
 *         consisting of useful methods that can be called from any location
 *         without having to instantiate an object.
 */
public class TestHelper {

	private final Logger LOGGER = Logger.getLogger(TestHelper.class
			.getName());
	private WebDriver driver;


	/**
	 * This method parses a By object and returns is locator value.
	 * 
	 * @param locator
	 * @return a string representing the locator value
	 */
	public String getLocatorString(By locator) {
		String[] locatorArray = locator.toString().split("\\s");
		String locatorSubString = locatorArray[1];
		LOGGER.log(Level.FINEST, "Parsing locator: " + locator.toString());
		LOGGER.log(Level.FINEST, "Locator String: " + locatorArray[1]);

		return locatorSubString;
	}


	

	/**
	 * This method calculates the timestamp at the point the method is called
	 * and returns it as a String. It is useful when you need to timestamp a
	 * file such as a screenshot.
	 * 
	 * @return the timestamp
	 */
	public String getTimestamp() {

		Date date = new java.util.Date();
		String timestamp = (new Timestamp(date.getTime())).toString();

		timestamp = timestamp.replaceAll("/", "-");
		timestamp = timestamp.replaceAll(":", ".");
		return timestamp;
	}

	private By buildLocator(String locatorType, String locatorValue) {
		// Return a instance of By class based on type of locator
		if ("id".equalsIgnoreCase(locatorType)) {
			return By.id(locatorValue);
		} else if ("name".equalsIgnoreCase(locatorType)) {
			return By.name(locatorValue);
		} else if (("classname".equalsIgnoreCase(locatorType))
				|| ("class".equalsIgnoreCase(locatorType))) {
			return By.className(locatorValue);
		} else if (("tagname".equalsIgnoreCase(locatorType))
				|| ("tag".equalsIgnoreCase(locatorType))) {
			return By.className(locatorValue);
		} else if (("linktext".equalsIgnoreCase(locatorType))
				|| ("link".equalsIgnoreCase(locatorType))) {
			return By.linkText(locatorValue);
		} else if ("partiallinktext".equalsIgnoreCase(locatorType)) {
			return By.partialLinkText(locatorValue);
		} else if (("cssselector".equalsIgnoreCase(locatorType))
				|| ("css".equalsIgnoreCase(locatorType))) {
			return By.cssSelector(locatorValue);
		} else if ("xpath".equalsIgnoreCase(locatorType)) {
			return By.xpath(locatorValue);
		} else {
			LOGGER.log(Level.SEVERE, "Locator type '" + locatorType
					+ "' not defined!!");
			return null;
		}
	}

	/**
	 * This method enables a By object to be created from a locator type and
	 * value.
	 * 
	 * @param locatorType
	 *            the locator type e.g. ID, CSS etc
	 * @param locatorValue
	 *            the locator value e.g. an XPath of CSS locator
	 * @return a By object
	 * @throws Exception
	 */
	public By getCustomLocator(String locatorType, String locatorValue) {

		return buildLocator(locatorType, locatorValue);
	}

}
