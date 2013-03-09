package main.java.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectBox extends BaseControl implements Clickable, Readable {

	Select selectBox;
	
	public SelectBox(WebDriver driver, By locator) {
		baseWebElement = findControl(driver, locator);
		selectBox = new Select(baseWebElement);
		System.out.println("WebElement found.");
	}
	
	@Override
	public Object readValue() {
		WebElement selectedOption = selectBox.getFirstSelectedOption();
		return selectedOption.getText(); 
	}

	@Override
	public void click() {
		baseWebElement.click();
		
	}
	
	public void selectOptionByValue(String option) {
		selectBox.selectByValue(option);
	}
	
	public void selectOptionByVisibleText(String option) {
		selectBox.selectByVisibleText(option);
	}
	
	public void selectOptionByIndex(int index) {
		selectBox.selectByIndex(index);
	}
}
