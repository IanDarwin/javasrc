import java.util.*;

/**
 * Extend GregorianCalendar to provide Julian date. 
 *
 * An example of a class that adds methods, but no data
 *
 * @author  	Ian Darwin, ian@darwinsys.com
 * @version	$Revision$, $Date$
 */
public class JulianDate extends GregorianCalendar {

	public final static int dom[] = {
			31, 28, 31, 30,	/* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31	/* sep oct nov dec */
	};

	/**
	 * getDayOfYear returns the Julian "DayOfYear": Jan 1 = 0,
	 * December 31 = 365 or 366.
	 */
	public int getDayOfYear() {
		int t = 0;

		/* Add up months so far, not including current month. */
		for (int i = 0; i<get(MONTH); i++)
			t += dom[i];

		if (isLeapYear(get(YEAR)) && get(MONTH) > 1)
			t++;

		t += get(DAY_OF_MONTH);	// Days so far this month.

		return t;
	}

	//* Constructors
	JulianDate() {
		super();
	}
	JulianDate(int yy, int mm, int dd) {
		super(yy,mm,dd);
	}

	public static void main(String args[]) {
		new JulianDate().dump();
		new JulianDate(1951, 04-1, 24).dump();
		new JulianDate(2000, 00, 01).dump();
	}

	private void dump() {
		System.out.println("Date is " + getTime());
		System.out.println("The year " + (isLeapYear(get(YEAR))?"is":"is not") +
			" a leap year");
		System.out.println("The day of year is " + getDayOfYear());
	}
}
