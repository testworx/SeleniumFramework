package org.nvonop.selenium.framework.datagenerator;

/**
 * @author nvonop
 *This class is intended to be used for obtaining valid and invalid email addresses.
 */
public class EmailGenerator {

	/**
	 * This method returns the email address "test@test.com".  
	 * @return a valid email address
	 */
	public static String getValidEmailAddress() {
		return "test@test.com";
	}
	
	/**
	 * This method returns the invalid email address "test.com" that is missing its local part.  
	 * @return an invalid email address
	 */
	public static String getEmailAddressMissingLocalPart() {
		return "@test.com";
	}
	
	/**
	 * This method returns the invalid email address "test@" that is missing its domain.  
	 * @return an invalid email address
	 */
	public static String getEmailAddressMissingDomain() {
		return "test@";
	}
	
	/**
	 * This method returns the invalid email address "testtest.com" that is missing the ampersand.  
	 * @return an invalid email address
	 */
	public static String getEmailAddressMissingAmpersand() {
		return "testtest.com";
	}
}
