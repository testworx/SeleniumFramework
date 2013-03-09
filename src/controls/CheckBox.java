package controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBox extends BaseControl implements Clickable, Selectable, Readable {

	public CheckBox(WebDriver driver, By locator) {
		baseWebElement = findControl(driver, locator);
		System.out.println("WebElement found.");
	}

	@Override
	public Boolean readValue() {
		return new Boolean(baseWebElement.isSelected());
	}

	@Override
	public void click() {
		baseWebElement.click();	
	}

	@Override
	public void select() {
		if (!baseWebElement.isSelected()) {
			baseWebElement.click();
		}
		
	}

	@Override
	public void deSelect() {
		if (baseWebElement.isSelected()) {
			baseWebElement.click();
		}
	}

	
}
