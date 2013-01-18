package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
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
	
	public WebElement findControl(WebDriver driver, String locator) {
		
		try {
			System.out.println("Looking for WebElement by id");
			return driver.findElement(By.id(locator));
		}
		catch (ElementNotVisibleException e) {
			try {
				System.out.println("Looking for WebElement by name");
				return driver.findElement(By.name(locator));
			}
			catch (ElementNotVisibleException ee) {
				try {
					System.out.println("Looking for WebElement by cssSelector");
					return driver.findElement(By.cssSelector(locator));
				}
				catch (ElementNotVisibleException eee) {
					try {
						System.out.println("Looking for WebElement by xpath");
						return driver.findElement(By.xpath(locator));
					}
					catch (ElementNotVisibleException eeee) {
						System.out.println("Failed looking for WebElement");
						throw new ElementNotVisibleException(locator);
					}
				}
			}
		}
	}
}
