package framework.utilities;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestHelper {

	public static WebDriver DRIVER;

	public static void setDriver(WebDriver passedInDriver) {
		DRIVER = passedInDriver;
	}

	public static void switchToWindow(String windowTitle) {
		Set<String> windows = DRIVER.getWindowHandles();

		for (String window : windows) {
			DRIVER.switchTo().window(window);
			if (DRIVER.getTitle().contains(windowTitle)) {
				System.out.println("Switching to window: " + DRIVER.getTitle());
				return;
			} else {
				System.out.println("No match for window: " + DRIVER.getTitle());
			}
		}
	}

	public static void switchToFrame(By locator) {
		String locatorSubString = getLocatorString(locator);

		DRIVER.switchTo().frame(locatorSubString);
	}

	public static String getLocatorString(By locator) {
		String[] locatorArray = locator.toString().split("\\s");
		String locatorSubString = locatorArray[1];
		System.out.println("Parsing locator: " + locator.toString());
		System.out.println("Locator String: " + locatorArray[1]);
		
		return locatorSubString;
	}

}
