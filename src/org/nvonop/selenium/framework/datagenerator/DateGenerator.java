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
        public static String getTodaysDate() {
                Calendar date = Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates the day at the point the method is called and 
         * returns it as a String in the format dd.  
         * @return todays day
         */
        public static String getTodaysDay() {
                Calendar date = Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd");
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates the month at the point the method is called and 
         * returns it as a String in the format MM.  
         * @return todays month
         */
        public static String getTodaysMonth() {
                Calendar date = Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("MM");
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates the year at the point the method is called and 
         * returns it as a String in the format yyyy.  
         * @return todays day
         */
        public static String getTodaysYear() {
                Calendar date = Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy");
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates a past date based on today's date minus the amount of days specified
         * and returns it as a String in the format dd/mm/yyyy. 
         * @param daysToSubtract - the number of days to subtract from today's date
         * @return todays date
         */
        public static String getDateInPast(int daysToSubtract) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
                date.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates the day for a past date based on today's date minus the amount of days specified
         * and returns it as a String in the format dd. 
         * @param daysToSubtract - the number of days to subtract from today's date
         * @return day
         */
        public static String getDayInPast(int daysToSubtract) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd");
                date.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates month for a past date based on today's date minus the amount of days specified
         * and returns it as a String in the format dd/mm/yyyy. 
         * @param daysToSubtract - the number of days to subtract from today's date
         * @return month
         */
        public static String getMonthInPast(int daysToSubtract) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("MM");
                date.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates year for a past date based on today's date minus the amount of days specified
         * and returns it as a String in the format yyyy. 
         * @param daysToSubtract - the number of days to subtract from today's date
         * @return year
         */
        public static String getYearInPast(int daysToSubtract) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy");
                date.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates a future date based on today's date plus the amount of days specified
         * and returns it as a String in the format dd/mm/yyyy. 
         * @param daysToAdd - the number of days to add to today's date
         * @return todays date
         */
        public static String getDateInFuture(int daysToAdd) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
                date.add(Calendar.DAY_OF_MONTH, daysToAdd);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates day for a future date based on today's date plus the amount of days specified
         * and returns it as a String in the format dd. 
         * @param daysToAdd - the number of days to add to today's date
         * @return  day
         */
        public static String getDayInFuture(int daysToAdd) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd");
                date.add(Calendar.DAY_OF_MONTH, daysToAdd);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates month for a future date based on today's date plus the amount of days specified
         * and returns it as a String in the format mm. 
         * @param daysToAdd - the number of days to add to today's date
         * @return month
         */
        public static String getMonthInFuture(int daysToAdd) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("MM");
                date.add(Calendar.DAY_OF_MONTH, daysToAdd);
                return formattedDate.format(date.getTime());
        }
        
        /**
         * This method calculates year for a future date based on today's date plus the amount of days specified
         * and returns it as a String in the format dd/mm/yyyy. 
         * @param daysToAdd - the number of days to add to today's date
         * @return year
         */
        public static String getYearInFuture(int daysToAdd) {
                GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
                SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy");
                date.add(Calendar.DAY_OF_MONTH, daysToAdd);
                return formattedDate.format(date.getTime());
        }
}