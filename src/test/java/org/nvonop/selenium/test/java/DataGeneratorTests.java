package org.nvonop.selenium.test.java;

import org.nvonop.selenium.framework.datagenerator.DateGenerator;
import org.nvonop.selenium.framework.datagenerator.EmailGenerator;
import org.nvonop.selenium.framework.datagenerator.TextGenerator;
import org.junit.*;

public class DataGeneratorTests {

	/*
	 * DateGenerator Tests
	 */
	@Test
	public void checkTodaysDateNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysDate());
	}

	@Test
	public void checkTodaysDayNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysDay());
	}

	@Test
	public void checkTodaysMonthNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysMonth());
	}

	@Test
	public void checkTodaysYearNotNull() {
		Assert.assertNotNull(DateGenerator.getTodaysYear());
	}

	@Test
	public void checkDateInPastNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInPast(3000));
	}

	@Test
	public void checkPastDayNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInPast(3000));
	}

	@Test
	public void checkPastMonthNotNull() {
		Assert.assertNotNull(DateGenerator.getMonthInPast(3000));
	}

	@Test
	public void checkPastYearNotNull() {
		Assert.assertNotNull(DateGenerator.getYearInPast(3000));
	}

	@Test
	public void checkDateInFutureNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInFuture(3000));
	}

	@Test
	public void checkFutureDayNotNull() {
		Assert.assertNotNull(DateGenerator.getDateInFuture(3000));
	}

	@Test
	public void checkFutureMonthNotNull() {
		Assert.assertNotNull(DateGenerator.getMonthInFuture(3000));
	}

	@Test
	public void checkFutureYearNotNull() {
		Assert.assertNotNull(DateGenerator.getYearInFuture(3000));
	}

	/*
	 * EmailGenerator Tests
	 */
	@Test
	public void checkValidEmailAddressNotNull() {
		Assert.assertNotNull(EmailGenerator.getValidEmailAddress());
	}

	@Test
	public void checkEmailAddressMissingDomainNotNull() {
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingDomain());
	}

	@Test
	public void checkEmailAddressMissingLocalPartNotNull() {
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingLocalPart());
	}

	@Test
	public void checkEmailAddressMissingAmpersandNotNull() {
		Assert.assertNotNull(EmailGenerator.getEmailAddressMissingAmpersand());
	}

	/*
	 * TextGenerator Tests
	 */
	@Test
	public void checkRandomStringOfFixedLengthNotNull() {
		Assert.assertNotNull(TextGenerator.getRandomString());
	}

	@Test
	public void checkRandomStringOfVariableLengthNotNull() {
		Assert.assertNotNull(TextGenerator.getRandomString(30));
	}

	@Test
	public void checkRandomStringOfVariableLengthCorrect() {
		Assert.assertEquals(TextGenerator.getRandomString(30).length(), 30);
	}

}
