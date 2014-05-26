package org.nvonop.selenium.framework.datagenerator;

import java.util.Random;

/**
 * @author nvonop This class is intended to be used for obtaining text strings
 *         of random characters. It is useful for obtaining text data for data
 *         input.
 */
public class TextGenerator {

	private static String ALLOWABLE_CHARS = "abcdefghijklmnopqrstuvwxyz";

	private TextGenerator() {

	}

	/**
	 * This method is used to obtain a string of random characters. It is useful
	 * when unique String is required for populating a form.
	 * 
	 * @return a random String
	 */
	public static String getRandomString() {
		// String hard coded to length 10 for now
		int length = 10;
		Random rand = new Random();
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < length; i++) {
			randomString.append(ALLOWABLE_CHARS.charAt(rand
					.nextInt(ALLOWABLE_CHARS.length())));
		}
		return randomString.toString();
	}

	/**
	 * This method is used to obtain a string of random characters. It is useful
	 * when unique String is required for populating a form. The length of the
	 * string is determined by stringLength
	 * 
	 * @param stringLength
	 *            the length of the String that will be returned
	 * @return a random String
	 */
	public static String getRandomString(int stringLength) {

		int length = stringLength;
		Random rand = new Random();
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < length; i++) {
			randomString.append(ALLOWABLE_CHARS.charAt(rand
					.nextInt(ALLOWABLE_CHARS.length())));
		}
		return randomString.toString();
	}
}
