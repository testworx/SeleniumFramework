package framework.controls;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectBox extends BaseControl implements Clickable, Readable {

	Select selectBox;

	public SelectBox(WebDriver driver, By locator) {
		setDriver(driver);
		setLocator(locator);

	}

	@Override
	public Object readValue() {
		selectBox = new Select(getUnderlyingWebElement());
		WebElement selectedOption = selectBox.getFirstSelectedOption();
		return selectedOption.getText();
	}

	@Override
	public void click() {
		getUnderlyingWebElement().click();
	}

	public void selectOptionByValue(String option) {
		selectBox = new Select(getUnderlyingWebElement());
		selectBox.selectByValue(option);
	}

	public void selectOptionByVisibleText(String option) {
		selectBox = new Select(getUnderlyingWebElement());
		selectBox.selectByVisibleText(option);
	}

	public void selectOptionByIndex(int index) {
		selectBox = new Select(getUnderlyingWebElement());
		selectBox.selectByIndex(index);
	}
}
