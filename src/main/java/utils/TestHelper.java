package main.java.utils;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestHelper {

	private WebDriver driver;

	public TestHelper(WebDriver passedInDriver) {
		driver = passedInDriver;
	}

	public void SwitchToWindow(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				System.out.println("Switching to window: " + driver.getTitle());
				return;
			} else {
				System.out.println("No match for window: " + driver.getTitle());
			}
		}
	}

	public void SwitchToFrame(By locator) {
		String locatorSubString = getLocatorString(locator);

		driver.switchTo().frame(locatorSubString);
	}

	private String getLocatorString(By locator) {
		String[] frameLocator = locator.toString().split("\\s");
		String locatorSubString = frameLocator[1];
		System.out.println("Parsing locator: " + locator.toString());
		System.out.println("Locator String: " + frameLocator[1]);
		// for (int i = 0; i<frameLocator.length; i++) {
		// System.out.println(frameLocator[i]);
		// }
		return locatorSubString;
	}

}
