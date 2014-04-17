package io;

import java.util.Calendar;
import java.util.Date;

/** Demonstrate some usage patterns and format-code examples 
 * of the Formatter class.
 */
// BEGIN main
public class FormatterDates {
	public static void main(String[] args) {

		// Format number as dates e.g., 2014-06-28
		System.out.printf("%4d-%02d-%2d%n", 2014, 6, 28);

		// Format fields directly from a Date object: multiple fields from "1$"
		// (hard-coded formatting for Date not advisable; see I18N chapter)
		Date today = Calendar.getInstance().getTime();
		// Might print e.g., July 4, 2015:
		System.out.printf("Today is %1$tB %1$td, %1$tY%n", today);
	}
}
// END main
