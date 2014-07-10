package org.nvonop.selenium.test.groovy

import org.nvonop.selenium.framework.Browser;
import org.nvonop.selenium.framework.ObjectMap;
import org.openqa.selenium.WebDriver;

import spock.lang.*

class ControlsSpec  extends Specification {
	
	ObjectMap objectMap
	Browser browser
	WebDriver driver
	
	def setupSpec() {
	//		System.setProperty("BROWSER", "Internet Explorer")
	//		System.setProperty("VERSION", "")
	//		System.setProperty("PLATFORM", "")
	//		System.setProperty("LOCAL_DRIVER", "true")
	//		System.setProperty("REMOTE_DRIVER", "false")
	//		System.setProperty("SAUCE_DRIVER", "false")
	//		System.setProperty("webdriver.ie.driver", "C:\\Automation\\lib\\IEDriverServer.exe");
	//		System.setProperty("IGNORE_SECURITY_DOMAINS", "true")
		}
		
		def setup() {
			browser = new Browser()
			browser.open()
			browser.navigate(ControlsPage.class)
			objectMap = new ObjectMap("ControlsPageObjectMap.properties")
		}
}
