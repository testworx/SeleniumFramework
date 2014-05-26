package org.nvonop.selenium.test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class SubmittedPage extends LoadableComponent<SubmittedPage> {

	WebDriver driver;
	String pageUrl;

	public SubmittedPage(WebDriver testDriver, String url) {
		driver = testDriver;
		pageUrl = url;
	}

	public SubmittedPage(WebDriver testDriver) {
		driver = testDriver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}


	@Override
	protected void isLoaded() throws Error {
		Assert.assertEquals(driver.getTitle(), "Submitted Page Title");
	}

	@Override
	protected void load() {
		driver.get(pageUrl);
	}

}
