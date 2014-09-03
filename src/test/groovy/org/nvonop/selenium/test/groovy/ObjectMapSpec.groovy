package org.nvonop.selenium.test.groovy

import org.nvonop.selenium.framework.actionbot.ActionBot;
import org.nvonop.selenium.framework.utilities.BaseTest;
import org.nvonop.selenium.framework.ObjectMap;
import org.nvonop.selenium.framework.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import spock.lang.*

class ObjectMapSpec  extends Specification {

	ObjectMap objectMap


	def setupSpec() {
		
	}

	def setup() {
		objectMap = new ObjectMap("ControlsPageObjectMap.properties")
	}

	def cleanup() {
	}


	def "The getLocatorAndOverride method returns a By object"() {

		when: "I call getLocatorAndOverride"
		then: "The type of object returned is By"
		assert objectMap.getLocatorAndOverride("text_firstname", "abc").getClass().toString().contains("org.openqa.selenium.By")
	}

	def "The getLocatorFromMap method returns a By object"() {

		when: "I call getLocatorFromMap"
		then: "The type of object returned is By"
		assert objectMap.getLocatorFromMap("text_firstname").getClass().toString().contains("org.openqa.selenium.By")
	}

	def "The getLocatorString method returns the correct Locator String"() {

		when: "I call getLocatorString"
		def locatorString = objectMap.getLocatorString("text_firstname")
		then: "The correct Locator String is returned"
		assert locatorString == "input#txt1"
	}

	def "The getLocatorType method returns the correct Locator Type String"() {

		when: "I call getLocatorString"
		def locatorType = objectMap.getLocatorType("text_firstname")
		then: "The correct Locator String is returned"
		assert locatorType == "css"
	}

	def "The getLocatorAndOverride method allows a locator String to be overridden"() {

		given: "I choose to modify a locator within the object map"
		when: "I supply updated locator details of abc"
		By locator = objectMap.getLocatorAndOverride("text_firstname", "abc")
		System.out.println("LOCATOR:  :"+ locator.toString())
		then: "The locator string is updated to abc"
		assert locator.toString().contains("abc")
	}
}
