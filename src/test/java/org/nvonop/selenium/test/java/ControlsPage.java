package org.nvonop.selenium.test.java;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.nvonop.selenium.framework.ObjectMap;
import org.nvonop.selenium.framework.controls.Button;
import org.nvonop.selenium.framework.controls.CheckBox;
import org.nvonop.selenium.framework.controls.Frame;
import org.nvonop.selenium.framework.controls.Link;
import org.nvonop.selenium.framework.controls.Message;
import org.nvonop.selenium.framework.controls.Page;
import org.nvonop.selenium.framework.controls.RadioButton;
import org.nvonop.selenium.framework.controls.SelectBox;
import org.nvonop.selenium.framework.controls.Table;
import org.nvonop.selenium.framework.controls.TextBox;
import org.nvonop.selenium.framework.controls.interfaces.Form;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.junit.*;

public class ControlsPage extends Page implements
		Form {

	private static final Logger LOGGER = Logger.getLogger(ControlsPage.class
			.getName());

	private ObjectMap map;

	private String pageUrl;

	private Frame frame;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button submit;
	private RadioButton male;
	private RadioButton female;
	private CheckBox bike;
	private CheckBox car;
	private Link google;
	private Table table;
	private TextBox firstName;
	private TextBox lastName;
	private SelectBox cars;

	private Message message1;
	private Message message2;
	private Message message3;
	private Message message4;
	private Message message5;
	private Message message6;
	
	public ControlsPage(WebDriver testDriver) {
		super(testDriver);
		map = new ObjectMap("ControlsPageObjectMap.properties");
		url = System.getProperty("APPLICATION_URL");
		try {
			setFrame(new Frame(driver, map.getLocatorFromMap("iframe_1")));
			setButton1(new Button(driver, map.getLocatorFromMap("button_1")));
			setButton2(new Button(driver, map.getLocatorFromMap("button_2")));
			setButton3(new Button(driver, map.getLocatorFromMap("alert_button")));
			setButton4(new Button(driver, map.getLocatorFromMap("button_4")));
			setSubmit(new Button(driver, map.getLocatorFromMap("button_submit")));
			setMale(new RadioButton(driver,
					map.getLocatorFromMap("radio_button_1")));
			setFemale(new RadioButton(driver,
					map.getLocatorFromMap("radio_button_2")));
			setBike(new CheckBox(driver, map.getLocatorFromMap("checkbox_1")));
			setCar(new CheckBox(driver, map.getLocatorFromMap("checkbox_2")));
			setGoogle(new Link(driver, map.getLocatorFromMap("link_google")));
			setTable(new Table(driver, map.getLocatorFromMap("table_1")));
			setFirstName(new TextBox(driver,
					map.getLocatorFromMap("text_firstname")));
			setLastName(new TextBox(driver,
					map.getLocatorFromMap("text_lastname")));
			setCars(new SelectBox(driver, map.getLocatorFromMap("select_cars")));

			setMessage1(new Message(driver,
					map.getLocatorFromMap("button_1_click_message")));
			setMessage2(new Message(driver,
					map.getLocatorFromMap("button_2_click_message")));
			setMessage3(new Message(driver,
					map.getLocatorFromMap("radio_button_1_click_message")));
			setMessage4(new Message(driver,
					map.getLocatorFromMap("radio_button_2_click_message")));
			setMessage5(new Message(driver,
					map.getLocatorFromMap("checkbox_1_click_message")));
			setMessage6(new Message(driver,
					map.getLocatorFromMap("checkbox_2_click_message")));

		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Exception in ControlsPage() constructor", e);
		}

	}

	@Override
	public void fillForm(String[] args) {
		getFirstName().write(args[0]);
		getLastName().write(args[1]);
	}

	@Override
	public SubmittedPage submitForm() {
		getLastName().getUnderlyingWebElement().submit();
		return new SubmittedPage(driver);
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public Button getButton1() {
		return button1;
	}

	public void setButton1(Button button1) {
		this.button1 = button1;
	}

	public Message getMessage1() {
		return message1;
	}

	public void setMessage1(Message message1) {
		this.message1 = message1;
	}

	public Button getButton2() {
		return button2;
	}

	public void setButton2(Button button2) {
		this.button2 = button2;
	}

	public Message getMessage2() {
		return message2;
	}

	public void setMessage2(Message message2) {
		this.message2 = message2;
	}

	public Button getButton3() {
		return button3;
	}

	public void setButton3(Button button3) {
		this.button3 = button3;
	}

	public Button getButton4() {
		return button4;
	}

	public void setButton4(Button button4) {
		this.button4 = button4;
	}

	public RadioButton getMale() {
		return male;
	}

	public void setMale(RadioButton male) {
		this.male = male;
	}

	public Message getMessage3() {
		return message3;
	}

	public void setMessage3(Message message3) {
		this.message3 = message3;
	}

	public RadioButton getFemale() {
		return female;
	}

	public void setFemale(RadioButton female) {
		this.female = female;
	}

	public Message getMessage4() {
		return message4;
	}

	public void setMessage4(Message message4) {
		this.message4 = message4;
	}

	public CheckBox getBike() {
		return bike;
	}

	public void setBike(CheckBox bike) {
		this.bike = bike;
	}

	public Message getMessage5() {
		return message5;
	}

	public void setMessage5(Message message5) {
		this.message5 = message5;
	}

	public CheckBox getCar() {
		return car;
	}

	public void setCar(CheckBox car) {
		this.car = car;
	}

	public Message getMessage6() {
		return message6;
	}

	public void setMessage6(Message message6) {
		this.message6 = message6;
	}

	public Link getGoogle() {
		return google;
	}

	public void setGoogle(Link google) {
		this.google = google;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TextBox getFirstName() {
		return firstName;
	}

	public void setFirstName(TextBox firstName) {
		this.firstName = firstName;
	}

	public TextBox getLastName() {
		return lastName;
	}

	public void setLastName(TextBox lastName) {
		this.lastName = lastName;
	}

	public SelectBox getCars() {
		return cars;
	}

	public void setCars(SelectBox cars) {
		this.cars = cars;
	}

	public Button getSubmit() {
		return submit;
	}

	public void setSubmit(Button submit) {
		this.submit = submit;
	}

}
