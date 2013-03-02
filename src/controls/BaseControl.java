package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseControl implements Detectable {

	WebElement baseWebElement;
	
	public WebElement getUnderlyingWebElement() {
		return baseWebElement;
	}
	
	public void setUnderlyingWebElement(WebElement element) {
		baseWebElement = element;
	}
	
	@Override
	public boolean exists() {
		try {
			return baseWebElement.isDisplayed();
		}
		catch (ElementNotVisibleException e) {
			return false;
		}
	}
	
	public WebElement findControl(WebDriver driver, By locator) {
		try {
			System.out.println("Looking for WebElement: "+locator.toString());
			return driver.findElement(locator);					
		}
		catch (NoSuchElementException e) {
			System.out.println("Failed looking for WebElement: "+locator.toString());
			throw new NoSuchElementException(locator.toString());
		}
	}
}
