package controls;

import org.openqa.selenium.WebElement;

public abstract class BaseControl {

	WebElement baseWebElement;
	
	public WebElement getUnderlyingWebElement() {
		return baseWebElement;
	}
	
	public void setUnderlyingWebElement(WebElement element) {
		baseWebElement = element;
	}
}
