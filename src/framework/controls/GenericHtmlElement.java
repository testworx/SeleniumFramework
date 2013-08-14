package fiftyPlus.pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import framework.controls.Button;
import framework.controls.GenericHtmlElement;
import framework.controls.Link;
import framework.controls.Page;
import framework.controls.TextBox;
import framework.controls.interfaces.Form;
import framework.utilities.ObjectMap;

public class Thankyou extends Page {

	private ObjectMap map;

	private WebDriver driver;
    private String frameTitle = "Thanks for buying our 50 plus plan";
	
	private GenericHtmlElement html_heading;
	
	public Thankyou(WebDriver driver) {
		this.driver = driver;
	
		map = new ObjectMap("Thankyou.properties");

		try {
			html_heading = new GenericHtmlElement(driver, map.getLocatorFromMap("html_heading"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getTitle() {
		
		int i = 0;
		
		while (!driver.getPageSource().contains(frameTitle)& i< 10) {
			try {
				Thread.sleep(1000);
				i++;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return frameTitle;
	}

}
