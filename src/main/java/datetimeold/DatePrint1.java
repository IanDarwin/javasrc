package datetimeold;

import java.util.*;
/**
 * Show dates before 1970.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class DatePrint1 {
	public static void main(String[] argv) {
		Calendar c = new GregorianCalendar(1918, 10, 11);
		System.out.println(c.get(Calendar.DAY_OF_MONTH) + " " +
			c.get(Calendar.MONTH) + ", " +
			c.get(Calendar.YEAR) + " " +
			c.get(Calendar.ERA));
	}
}
