package controls;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends BaseControl implements Clickable, Readable {

	public Button(WebDriver driver, String locator) {
		baseWebElement = findControl(driver, locator);
		System.out.println("WebElement found.");
	}

	@Override
	public Object readValue() {
		// TODO Auto-generated method stub
		return baseWebElement.getText();
	}

	@Override
	public void click() {
		baseWebElement.click();
		
	}

	
}
