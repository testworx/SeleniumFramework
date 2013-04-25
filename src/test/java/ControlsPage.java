package test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import framework.controls.Button;
import framework.controls.CheckBox;
import framework.controls.Frame;
import framework.controls.Link;
import framework.controls.Message;
import framework.controls.RadioButton;
import framework.controls.SelectBox;
import framework.controls.Table;
import framework.controls.TextBox;
import framework.controls.interfaces.Form;
import framework.utilities.ObjectMap;

public class ControlsPage extends LoadableComponent<ControlsPage> implements Form {

	private ObjectMap map;

	private WebDriver driver;
	private String pageUrl;
	
	public Frame frame;
	public Button button1;
	public Button button2;
	public Button button3;
	public Button button4;
	public Button submit;
	public RadioButton male;
	public RadioButton female;
	public CheckBox bike;
	public CheckBox car;
	public Link google;
	public Table table;
	public TextBox firstName;
	public TextBox lastName;
	public SelectBox cars;
	
	public Message message1;
	public Message message2;
	public Message message3;
	public Message message4;
	public Message message5;
	public Message message6;
	
	public ControlsPage(WebDriver testDriver, String url) {
		driver = testDriver;
		pageUrl = url;		
		map = new ObjectMap("ControlsPageObjectMap.properties");
		
		try {
			frame = new Frame(driver, map.getLocator("iframe_1"));
			button1 = new Button(driver, map.getLocator("button_1"));
			button2 = new Button(driver, map.getLocator("button_2"));
			button3 = new Button(driver, map.getLocator("alert_button"));
			button4 = new Button(driver, map.getLocator("button_4"));
			submit = new Button(driver, map.getLocator("button_submit"));
			male = new RadioButton(driver,
					map.getLocator("radio_button_1"));
			female = new RadioButton(driver,
					map.getLocator("radio_button_2"));
			bike = new CheckBox(driver,
					map.getLocator("checkbox_1"));
			car = new CheckBox(driver,
					map.getLocator("checkbox_2"));
			google = new Link(driver, map.getLocator("link_google"));
			table = new Table(driver, map.getLocator("table_1"));
			firstName = new TextBox(driver,
					map.getLocator("text_firstname"));
			lastName = new TextBox(driver,
					map.getLocator("text_lastname"));
			cars = new SelectBox(driver,
					map.getLocator("select_cars"));
			
			message1 = new Message(driver, map
					.getLocator("button_1_click_message"));
			message2 = new Message(driver, map
					.getLocator("button_2_click_message"));
			message3 = new Message(driver, map
					.getLocator("radio_button_1_click_message"));
			message4 = new Message(driver, map
					.getLocator("radio_button_2_click_message"));
			message5 = new Message(driver, map
					.getLocator("checkbox_1_click_message"));
			message6 = new Message(driver, map
					.getLocator("checkbox_2_click_message"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ControlsPage(WebDriver testDriver) {
		driver = testDriver;
	}
	
	@Override
	protected void isLoaded() throws Error {
		Assert.assertEquals(driver.getTitle(), "Controls Page Title");		
	}
	
	@Override
	protected void load() {
		driver.get(pageUrl);		
	}
	
	@Override
	public void fillForm(String[] args) {
		firstName.write(args[0]);
		lastName.write(args[1]);
	}

	@Override
	public SubmittedPage submitForm() {
		lastName.getUnderlyingWebElement().submit();
		return new SubmittedPage(driver);
	}



}
