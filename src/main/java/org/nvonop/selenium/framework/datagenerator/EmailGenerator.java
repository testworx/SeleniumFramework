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

/**
 * @author nvonop This class is intended to be used for obtaining valid and
 *         invalid email addresses.
 */
public class EmailGenerator {

	private EmailGenerator() {

	}

	/**
	 * This method returns the email address "test@test.com".
	 * 
	 * @return a valid email address
	 */
	public static String getValidEmailAddress() {
		return "test@test.com";
	}

	/**
	 * This method returns the invalid email address "test.com" that is missing
	 * its local part.
	 * 
	 * @return an invalid email address
	 */
	public static String getEmailAddressMissingLocalPart() {
		return "@test.com";
	}

	/**
	 * This method returns the invalid email address "test@" that is missing its
	 * domain.
	 * 
	 * @return an invalid email address
	 */
	public static String getEmailAddressMissingDomain() {
		return "test@";
	}

	/**
	 * This method returns the invalid email address "testtest.com" that is
	 * missing the ampersand.
	 * 
	 * @return an invalid email address
	 */
	public static String getEmailAddressMissingAmpersand() {
		return "testtest.com";
	}
}
