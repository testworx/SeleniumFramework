/*
The MIT License (MIT)

Copyright (c) 2015 Nicholas Oppersdorff

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

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
		return buildRandomString(length);
	}

	/**
	 * This method is used to obtain a string of random characters. It is useful
	 * when unique String is required for populating a form. The length of the
	 * string is determined by stringLength
	 * 
	 * @param length
	 *            the length of the String that will be returned
	 * @return a random String
	 */
	public static String getRandomString(int length) {

		return buildRandomString(length);
	}
	
	private static String buildRandomString(int length) {

		Random rand = new Random();
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < length; i++) {
			randomString.append(ALLOWABLE_CHARS.charAt(rand
					.nextInt(ALLOWABLE_CHARS.length())));
		}
		return randomString.toString();
	}
}
