package datetimeold;

import java.util.*;
/**
 * Show some calendar calculations.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class CalCalcs {
	public static void main(String[] argv) {
		Calendar c = Calendar.getInstance();
		System.out.println("I got a " + c.getClass());
		c.set(1951, 03, 24, 12, 30, 0);
		System.out.println("I set it to " + c.getTime().toString());
		System.out.println("I actually set the year to " +c.get(Calendar.YEAR));
		System.out.println("In milliseconds, that's " + c.getTime().getTime());
		System.out.println("Or, in seconds, " + c.getTime().getTime()/1000);
	}
}
