package org.nvonop.selenium.test.groovy

import spock.lang.*
import org.nvonop.selenium.framework.actionbot.*
import org.nvonop.selenium.framework.*
import org.openqa.selenium.WebDriver;

class ActionBotSpec  extends Specification {

	ActionBot actionBot
	ObjectMap objectMap
	Browser browser
	WebDriver driver

	def setupSpec() {
		
	}

	def setup() {
		browser = new Browser()
		browser.setBrowserCapability("build", System.getProperty("BUILD"))
		browser.setBrowserCapability("tags", "ActionBotSpec")
		browser.open()
		browser.navigate(System.getProperty("APPLICATION_URL"))
		actionBot = new ActionBot(browser.getWebdriver())
		objectMap = new ObjectMap("ControlsPageObjectMap.properties")
	}

	def "A Bot can click on a control"() {
		when:
		actionBot.click(objectMap.getLocatorFromMap("button_1"))
		then:
		assert actionBot.checkVisibility(objectMap.getLocatorFromMap("button_1_click_message"))
	}

	def "A Bot can read a control"() {
		when: "The action bot reads a control's text value"
		def buttonText = actionBot.readText(objectMap.getLocatorFromMap("button_1"))
		then: "The text value is read correctly"
		assert buttonText == "Button 1"
	}

	def "A Bot can write to a control"() {
		when: "The action bot writes to a control"
		def text = "Some Text"
		actionBot.type(objectMap.getLocatorFromMap("text_firstname"), text)
		then: "The value is written correctly"
		assert actionBot.readAttribute(objectMap.getLocatorFromMap("text_firstname"), "value") == text
	}

	def cleanup() {
		browser.close()
	}
}
