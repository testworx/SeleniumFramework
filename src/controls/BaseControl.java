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
		
//		try {
//			System.out.println("Looking for WebElement by id: "+locator);
//			return driver.findElement(By.id(locator));
//		}
//		catch (NoSuchElementException e) {
//			try {
//				System.out.println("Looking for WebElement by name: "+locator);
//				return driver.findElement(By.name(locator));
//			}
//			catch (NoSuchElementException ee) {
//				try {
//					System.out.println("Looking for WebElement by xpath: "+locator);
//					return driver.findElement(By.xpath(locator));
//				}
//				catch (NoSuchElementException eee) {
//					try {
//						System.out.println("Looking for WebElement by cssSelector: "+locator);
//						return driver.findElement(By.cssSelector(locator));					
//					}
//					catch (NoSuchElementException eeee) {
//						System.out.println("Failed looking for WebElement: "+locator);
//						throw new NoSuchElementException(locator);
//					}
//				}
//			}
//		}
	}
}
