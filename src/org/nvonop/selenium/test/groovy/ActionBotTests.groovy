package org.nvonop.selenium.test.groovy

import spock.lang.*
import org.nvonop.selenium.framework.actionbot.*
import org.nvonop.selenium.framework.utilities.*
import org.openqa.selenium.WebDriver;

class ActionBotTests  extends Specification {
	
	ActionBot actionBot
	ObjectMap objectMap
	BaseTest baseTest
	WebDriver driver
	
	def setupSpec() {
		System.setProperty("BROWSER", "Internet Explorer")
		System.setProperty("VERSION", "")
		System.setProperty("PLATFORM", "")
		System.setProperty("LOCAL_DRIVER", "true")
		System.setProperty("REMOTE_DRIVER", "false")
		System.setProperty("SAUCE_DRIVER", "false")
		System.setProperty("webdriver.ie.driver", "C:\\Automation\\lib\\IEDriverServer.exe");
		System.setProperty("IGNORE_SECURITY_DOMAINS", "true")
	}
	
	def setup() {
		baseTest = new BaseTest()
		baseTest.setWebDriver(System.getProperty("BROWSER"),
			System.getProperty("VERSION"), System.getProperty("PLATFORM"))
		driver = baseTest.getWebDriver()
		TestHelper.setDriver(driver)
		actionBot = new ActionBot(driver)
		objectMap = new ObjectMap("ControlsPageObjectMap.properties")
		driver.navigate().to(new URL("http://selenium-framework.site44.com/controls_page.html"))
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
		driver.quit()
	}
}
