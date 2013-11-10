package org.nvonop.selenium.framework.datagenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author nvonop
 *This class is intended to be used for obtaining 
 *date data including dates in the future and in the past.
 */
public class DateGenerator {

	/**
	 * This method calculates the date at the point the method is called and 
	 * returns it as a String in the format dd/mm/yyyy.  
	 * @return todays date
	 */
	public String getTodaysDate() {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd/mm/yyyy");
		return formattedDate.format(date.getTime());
	}
	
	/**
	 * This method calculates a past date based on today's date minus the amount of days specified
	 * and returns it as a String in the format dd/mm/yyyy. 
	 * @param amount - the number of days to subtract from today's date
	 * @return todays date
	 */
	public String getDateInPast(int amount) {
		GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd/mm/yyyy");
		date.add(Calendar.DAY_OF_MONTH, -amount);
		return formattedDate.format(date.getTime());
	}
	
	/**
	 * This method calculates a future date based on today's date plus the amount of days specified
	 * and returns it as a String in the format dd/mm/yyyy. 
	 * @param amount - the number of days to add to today's date
	 * @return todays date
	 */
	public String getDateInFuture(int amount) {
		GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd/mm/yyyy");
		date.add(Calendar.DAY_OF_MONTH, amount);
		return formattedDate.format(date.getTime());
	}
}
