import java.util.Date;

/**
 * Extend Date to provide Julian date. 
 *
 * An example of a class that adds methods, but no data
 *
 * @author  	Ian Darwin, ian@darwinsys.com
 * @version	$Revision$, $Date$
 */
public class JulianDate extends Date {

	/**
	 * isLeap() returns true if the Date object is a Leap Year.
	 *
	 * "a year is a leap year if it is divisible by 4
	 * but not by 100, except that years divisible by 400
	 * *are* leap years." 
	 *	-- Kernighan & Ritchie, _The C Programming Language_, p 37.
	 */
	public boolean isLeap() {
		int year = 1900 + getYear();
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		return false;
	}

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
		for (int i = 0; i<getMonth(); i++)
			t += dom[i];

		if (isLeap() && getMonth() > 1)
			t++;

		t += getDate();	// Days so far this month.

		return t;
	}
	//* Constructors
	JulianDate() {
		super();
	}
	JulianDate(int yy, int mm, int dd) {
		super(yy,mm,dd);
	}
}
