package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import controls.Alert;
import controls.BasePage;
import controls.Button;

public class HomepageAlert extends LoadableComponent<HomepageAlert> {

	private WebDriver driver;
	String pageUrl;
	
	String popup_alertId = "alert";
	//String anchor_closeClass = "container-close";
	String button_okId = "yui-gen10-button";
	public Alert initialAlert;
	Button okButton;
	
	public HomepageAlert  (WebDriver testDriver, String url) {
		driver = testDriver;
		pageUrl = url;
	}
	
	@Override
	protected void isLoaded() throws Error {
		Assert.assertEquals(driver.findElements(By.id(popup_alertId)).size()>0, true);		
	}
	
	@Override
	protected void load() {
		driver.get(pageUrl);
		initialAlert = new Alert(driver, popup_alertId);
		okButton = new Button(driver, button_okId);
	}
	
	public Homepage closeAlert() {		 
		 okButton.click();		 
		 return new Homepage(driver, pageUrl);
	}
	
}
