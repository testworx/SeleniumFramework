package org.nvonop.selenium.framework.controls;

import org.openqa.selenium.WebDriver;

/**
 * This class acts as a container for any functionality that can be considered
 * common to all pages in general. IT IS STILL A WORK IN PROGRESS.
 * 
 * @author nvonop
 * 
 */
public class Page {

	protected WebDriver driver;
	public String url;
	
	public Page(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getURL() {
		return url;
	}
}
