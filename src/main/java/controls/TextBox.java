package main.java.controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBox extends BaseControl implements Clickable, Readable, Writeable {

	public TextBox(WebDriver driver, By locator) {
		baseWebElement = findControl(driver, locator);
		System.out.println("WebElement found.");
	}

	@Override
	public String readValue() {
		return baseWebElement.getAttribute("value");
	}

	@Override
	public void click() {
		baseWebElement.click();	
	}

	@Override
	public void write(String value) {
		baseWebElement.sendKeys(value);		
	}	
}
