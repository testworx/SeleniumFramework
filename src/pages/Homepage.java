package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import controls.BasePage;

public class Homepage extends LoadableComponent<Homepage> {

	private WebDriver driver;
	String pageUrl;
	
	public Homepage  (WebDriver testDriver, String url) {
		driver = testDriver;
		pageUrl = url;
	}
	
	public Homepage  (WebDriver testDriver) {
		driver = testDriver;
	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		Assert.assertEquals(driver.getTitle(), "YUI: Complex Application");
		
	}
	@Override
	protected void load() {
		driver.get(pageUrl);		
	}
	
}
