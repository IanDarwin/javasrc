package datetimeold;

import java.util.Date;

/**
 * Try to find the closest thing to an ISO date that the antidiluvial (1.0) Date.parse() can hack.
 * Needed because some old turkey of a program still uses that antiquity to parse dates.
 */
public class DateParse {
	public static void main(String[] args) {
		long l = Date.parse("2010 Jan 01 00:00:00 EST");
		Date d = new Date(l);
		System.out.println(d);
	}
}
