package framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

/**
 * This class provides functionality for reading locator information in from a property file.
 * The file must end with ".properties".  
 * The file consists of rows of properties.  Each property record has the following syntax:  
 * PROPERTY_NAME=LOCATOR_TYPE>LOCATOR_VALUE e.g. button_1=id>btn1
 * @author nvonop
 *
 */
public class ObjectMap {
	Properties properties;

	/**
	 * This constructor loads a set of Properties from a file matching the name of mapFile.
	 * These properties consist of various locator strings to uniquely identify UI controls 
	 * on a page.
	 * @param mapFile The name of the properties file containing UI locator strings
	 */
	public ObjectMap(String mapFile) {
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(mapFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method returns a new locator object based on the locator type and locator substring 
	 * values from the property named elementName.
	 * @param elementName The name of the property containing the relevant locator information
	 * @return a By object consisting of the locator type and locator string
	 * @throws Exception To indicate the locator type could not be determined
	 */
	public By getLocatorFromMap(String elementName) throws Exception {
		// Read value using the logical name as Key
		String locator = properties.getProperty(elementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.substring(locator.indexOf(">")+1);
		// Return a instance of By class based on type of locator
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);
		} else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);
		} else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class"))) {
			return By.className(locatorValue);
		} else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag"))) {
			return By.className(locatorValue);
		} else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link"))) {
			return By.linkText(locatorValue);
		} else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		} else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css"))) {
			return By.cssSelector(locatorValue);
		} else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);
		} else {
			throw new Exception("Locator type '" + locatorType
					+ "' not defined!!");
		}
	}

	/**
	 * This method returns a new locator object based on the locator type value from the property 
	 * named elementName and locatorName.  This is useful if the locator value is dynamic but the 
	 * locator type is known.
	 * @param logicalElementName The name of the property containing the relevant locator information
	 * @param locatorValue The locator String value of the property
	 * @return a By object consisting of the locator type and locator string
	 * @throws Exception To indicate the locator type could not be determined
	 */
	public By getLocatorAndOverride(String logicalElementName,
			String locatorValue) throws Exception {
		// Read value using the logical name as Key
		String locator = properties.getProperty(logicalElementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(">")[0];

		// Return a instance of By class based on type of locator
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);
		} else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);
		} else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class"))) {
			return By.className(locatorValue);
		} else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag"))) {
			return By.className(locatorValue);
		} else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link"))) {
			return By.linkText(locatorValue);
		} else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		} else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css"))) {
			return By.cssSelector(locatorValue);
		} else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);
		} else {
			throw new Exception("Locator type '" + locatorType
					+ "' not defined!!");
		}
	}

	/**
	 * This method extracts the locator value from the property matching logicalElementName.  It
	 * is useful if you want to modify the locator value in some way or add dynamic information 
	 * using the getLocatorAndOverride method.
	 * @param logicalElementName
	 * @return a new String consisting of the locatorValue substring
	 */
	public String getLocatorString(String logicalElementName) {
		// Read value using the logical name as Key
		String locator = properties.getProperty(logicalElementName);
		// Split the value which contains locator type and locator value
		String locatorValue = locator.split(">")[1];

		return locatorValue;
	}

	/**
	 * This method extracts the locator type from the property matching logicalElementName.  It
	 * is useful if you want to modify the locator type in some way or add dynamic information 
	 * using the getLocatorAndOverride method.
	 * @param logicalElementName
	 * @return a new String consisting of the locatorValue substring
	 */
	public String getLocatorType(String logicalElementName) {
		// Read value using the logical name as Key
		String locator = properties.getProperty(logicalElementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(">")[0];

		return locatorType;
	}

}
