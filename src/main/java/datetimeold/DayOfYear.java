/**
 * 
 */
package datetimeold;

import java.util.Calendar;

/**
 * Get today's day of the year.
 */
public class DayOfYear {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println("Today is the " + c.get(Calendar.DAY_OF_YEAR) +
				" day of " + c.get(Calendar.YEAR));

	}

}
