package lang;

import java.util.*;

/** 
 * RefValues - program to show Reference values vs scalars.
 */
public class RefValues {

	public static void main(String[] argv) {
		@SuppressWarnings("unused")
		RefValues s = new RefValues();
		// Nothing needed - all that's interesting happens in the constructor
	}

	@SuppressWarnings("unused")
	public RefValues() {
		long longValue = 420;
		GregorianCalendar d1 = new GregorianCalendar(1971,04,24);

		System.out.println("Before demoMethod, long = " + longValue + 
			" Year = " + d1.get(Calendar.YEAR));

		demoMethod(longValue, d1);

		System.out.println("After  demoMethod, long = " + longValue + 
			" Year = " + d1.get(Calendar.YEAR));
		if (d1 == null) {	/* "can't happen" */
			System.err.println("demoMethod changed my date obj!");
			return;
		}
	}

	public void demoMethod(long myLong, GregorianCalendar myCal) {
		myLong = 24;		// does not affect main.longValue
		myCal.set(Calendar.YEAR, 1999);  // DOES affect main's d1
		myCal = null;		// no affect on main's d1
	}

}
