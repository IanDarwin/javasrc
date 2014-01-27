package datetimeold;

import java.util.*;

/** Show use of Calendar objects */
public class GregCalDemo {

	public static void main(String[] av) {
		GregorianCalendar d1 = new GregorianCalendar(1986, 04, 05); // May 5
		GregorianCalendar d2 = new GregorianCalendar();	// today
		Calendar d3 = Calendar.getInstance();	// today

		System.out.println("It was then " + d1.getTime());
		System.out.println("It is now " + d2.getTime());
		System.out.println("It is now " + d3.getTime());
		d3.set(Calendar.YEAR, 1915);
		d3.set(Calendar.MONTH, Calendar.APRIL);
		d3.set(Calendar.DAY_OF_MONTH, 12);
		System.out.println("D3 set to " + d3.getTime());
	}
}
