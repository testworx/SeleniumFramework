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
		System.out.println(DateGenerator.getTodaysDate());
		Assert.assertNotNull(DateGenerator.getTodaysDate());
	}
	
	@Test
	public void TestTodaysDayNotNull() {
		System.out.println(DateGenerator.getTodaysDay());
		Assert.assertNotNull(DateGenerator.getTodaysDay());
	}
	
	@Test
	public void TestTodaysMonthNotNull() {
		System.out.println(DateGenerator.getTodaysMonth());
		Assert.assertNotNull(DateGenerator.getTodaysMonth());
	}
	
	@Test
	public void TestTodaysYearNotNull() {
		System.out.println(DateGenerator.getTodaysYear());
		Assert.assertNotNull(DateGenerator.getTodaysYear());
	}
	
	@Test
	public void TestDateInPastNotNull() {
		System.out.println(DateGenerator.getDateInPast(3000));
		Assert.assertNotNull(DateGenerator.getDateInPast(3000));
	}
	
	@Test
	public void TestPastDayNotNull() {
		System.out.println(DateGenerator.getDayInPast(3000));
		Assert.assertNotNull(DateGenerator.getDateInPast(3000));
	}
	
	@Test
	public void TestPastMonthNotNull() {
		System.out.println(DateGenerator.getMonthInPast(3000));
		Assert.assertNotNull(DateGenerator.getMonthInPast(3000));
	}
	
	@Test
	public void TestPastYearNotNull() {
		System.out.println(DateGenerator.getYearInPast(3000));
		Assert.assertNotNull(DateGenerator.getYearInPast(3000));
	}
	
	@Test
	public void TestDateInFutureNotNull() {
		System.out.println(DateGenerator.getDateInFuture(3000));
		Assert.assertNotNull(DateGenerator.getDateInFuture(3000));
	}
	
	@Test
	public void TestFutureDayNotNull() {
		System.out.println(DateGenerator.getDayInFuture(3000));
		Assert.assertNotNull(DateGenerator.getDateInFuture(3000));
	}
	
	@Test
	public void TestFutureMonthNotNull() {
		System.out.println(DateGenerator.getMonthInFuture(3000));
		Assert.assertNotNull(DateGenerator.getMonthInFuture(3000));
	}
	
	@Test
	public void TestFutureYearNotNull() {
		System.out.println(DateGenerator.getYearInFuture(3000));
		Assert.assertNotNull(DateGenerator.getYearInFuture(3000));
	}
	
	
	/*
	 * EmailGenerator Tests
	 */
	@Test
	public void TestValidEmailAddressNotNull() {
		System.out.println(EmailGenerator.getValidEmailAddress());
		Assert.assertNotNull(EmailGenerator.getValidEmailAddress());
	}
	
	@Test
	public void TestEmailAddressMissingDomainNotNull() {
		System.out.println(EmailGenerator.getEmailAddressMissingDomain());
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingDomain());
	}
	
	@Test
	public void TestEmailAddressMissingLocalPartNotNull() {
		System.out.println(EmailGenerator.getEmailAddressMissingLocalPart());
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingLocalPart());
	}
	
	@Test
	public void TestEmailAddressMissingAmpersandNotNull() {
		System.out.println(EmailGenerator.getEmailAddressMissingAmpersand());
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingAmpersand());
	}
	
	
	/*
	 * TextGenerator Tests
	 */
	@Test
	public void TestRandomStringOfFixedLengthNotNull() {
		System.out.println(TextGenerator.getRandomString());
		Assert.assertNotNull(TextGenerator.getRandomString());
	}
	
	@Test
	public void TestRandomStringOfVariableLengthNotNull() {
		System.out.println(TextGenerator.getRandomString(30));
		Assert.assertNotNull(TextGenerator.getRandomString(30));
	}
	
	@Test
	public void TestRandomStringOfVariableLengthCorrect() {
		Assert.assertEquals(TextGenerator.getRandomString(30).length(), 30);
	}
	
}
