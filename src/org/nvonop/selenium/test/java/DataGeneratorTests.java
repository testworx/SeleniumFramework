package org.nvonop.selenium.test.java;

import org.nvonop.selenium.framework.datagenerator.DateGenerator;
import org.nvonop.selenium.framework.datagenerator.EmailGenerator;
import org.nvonop.selenium.framework.datagenerator.TextGenerator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataGeneratorTests {

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {

	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
	
	}
	
	/*
	 * DateGenerator Tests
	 */
	@Test
	public void TestTodaysDateNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysDate());
	}
	
	@Test
	public void TestTodaysDayNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysDay());
	}
	
	@Test
	public void TestTodaysMonthNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysMonth());
	}
	
	@Test
	public void TestTodaysYearNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysYear());
	}
	
	@Test
	public void TestDateInPastNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInPast(3000));
	}
	
	@Test
	public void TestPastDayNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInPast(3000));
	}
	
	@Test
	public void TestPastMonthNotNull() {
		Assert.assertNotNull(DateGenerator.getMonthInPast(3000));
	}
	
	@Test
	public void TestPastYearNotNull() {
		Assert.assertNotNull(DateGenerator.getYearInPast(3000));
	}
	
	@Test
	public void TestDateInFutureNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInFuture(3000));
	}
	
	@Test
	public void TestFutureDayNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInFuture(3000));
	}
	
	@Test
	public void TestFutureMonthNotNull() {
		Assert.assertNotNull(DateGenerator.getMonthInFuture(3000));
	}
	
	@Test
	public void TestFutureYearNotNull() {
		Assert.assertNotNull(DateGenerator.getYearInFuture(3000));
	}
	
	
	/*
	 * EmailGenerator Tests
	 */
	@Test
	public void TestValidEmailAddressNotNull() {
		Assert.assertNotNull(EmailGenerator.getValidEmailAddress());
	}
	
	@Test
	public void TestEmailAddressMissingDomainNotNull() {
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingDomain());
	}
	
	@Test
	public void TestEmailAddressMissingLocalPartNotNull() {
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingLocalPart());
	}
	
	@Test
	public void TestEmailAddressMissingAmpersandNotNull() {
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingAmpersand());
	}
	
	
	/*
	 * TextGenerator Tests
	 */
	@Test
	public void TestRandomStringOfFixedLengthNotNull() {
		Assert.assertNotNull(TextGenerator.getRandomString());
	}
	
	@Test
	public void TestRandomStringOfVariableLengthNotNull() {
		Assert.assertNotNull(TextGenerator.getRandomString(30));
	}
	
	@Test
	public void TestRandomStringOfVariableLengthCorrect() {
		Assert.assertEquals(TextGenerator.getRandomString(30).length(), 30);
	}
	
}
