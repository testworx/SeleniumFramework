package org.nvonop.selenium.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;

/**
 * This class provides functionality for reading locator information in from a
 * property file. The file must end with ".properties". The file consists of
 * rows of properties. Each property record has the following syntax:
 * PROPERTY_NAME=LOCATOR_TYPE>LOCATOR_VALUE e.g. button_1=id>btn1
 * 
 * @author nvonop
 * 
 */
public class ObjectMap {

	private static final String MESSAGE_IO_EXCEPTION_IN_OBJECT_MAP_CONSTRUCTOR = "IOException in ObjectMap() constructor: ";

	private static final String MESSAGE_LOCATOR_TYPE_NOT_DEFINED = "Locator type not defined: ";

	private static final Logger LOGGER = Logger.getLogger(ObjectMap.class
			.getName());

	Properties properties;

	/**
	 * This constructor loads a set of Properties from a file matching the name
	 * of mapFile. These properties consist of various locator strings to
	 * uniquely identify UI controls on a page.
	 * 
	 * @param mapFile
	 *            The name of the properties file containing UI locator strings
	 */
	public ObjectMap(String mapFile) {
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(mapFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			LOGGER.log(Level.INFO,
					MESSAGE_IO_EXCEPTION_IN_OBJECT_MAP_CONSTRUCTOR, e);
		}

	}

	private By buildLocator(String locatorType, String locatorValue) {
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
			LOGGER.log(Level.SEVERE, MESSAGE_LOCATOR_TYPE_NOT_DEFINED
					+ locatorType);
			return null;
		}
	}

	/**
	 * This method returns a new locator object based on the locator type value
	 * from the property named elementName and locatorName. This is useful if
	 * the locator value is dynamic but the locator type is known.
	 * 
	 * @param logicalElementName
	 *            The name of the property containing the relevant locator
	 *            information
	 * @param locatorValue
	 *            The locator String value of the property
	 * @return a By object consisting of the locator type and locator string
	 * @throws Exception
	 *             To indicate the locator type could not be determined
	 */
	public By getLocatorAndOverride(String logicalElementName,
			String locatorValue) {
		// Split the value which contains locator type and locator value
		String locatorType = getLocatorType(logicalElementName);

		return buildLocator(locatorType, locatorValue);
	}

	/**
	 * This method returns a new locator object based on the locator type and
	 * locator substring values from the property named elementName.
	 * 
	 * @param elementName
	 *            The name of the property containing the relevant locator
	 *            information
	 * @return a By object consisting of the locator type and locator string
	 * @throws Exception
	 *             To indicate the locator type could not be determined
	 */
	public By getLocatorFromMap(String logicalElementName) {

		// Split the value which contains locator type and locator value
		String locatorType = getLocatorType(logicalElementName);
		String locatorValue = getLocatorString(logicalElementName);
		// Return a instance of By class based on type of locator
		return buildLocator(locatorType, locatorValue);
	}

	/**
	 * This method extracts the locator value from the property matching
	 * logicalElementName. It is useful if you want to modify the locator value
	 * in some way or add dynamic information using the getLocatorAndOverride
	 * method.
	 * 
	 * @param logicalElementName
	 * @return a new String consisting of the locatorValue substring
	 */
	public String getLocatorString(String logicalElementName) {
		String locator = readLocatorDataFromPropertiesFile(logicalElementName);
		// Split the value which contains locator type and locator value
		return locator.split(">")[1];
	}

	/**
	 * This method extracts the locator type from the property matching
	 * logicalElementName. It is useful if you want to modify the locator type
	 * in some way or add dynamic information using the getLocatorAndOverride
	 * method.
	 * 
	 * @param logicalElementName
	 * @return a new String consisting of the locatorValue substring
	 */
	public String getLocatorType(String logicalElementName) {
		String locator = readLocatorDataFromPropertiesFile(logicalElementName);
		// Split the value which contains locator type and locator value
		return locator.split(">")[0];
	}

	private String readLocatorDataFromPropertiesFile(String logicalElementName) {
		// Read value using the logical name as Key
		return properties.getProperty(logicalElementName);
	}

}
