import java.util.*;
import java.text.*;

/** Print a month page.
 * Only works for the Western calendar. 
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class CalendarPage {

	/** The names of the months */
	String[] months = {
		"January", "February", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};

	/** The days in each month. */
	public final static int dom[] = {
			31, 28, 31, 30,	/* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31	/* sep oct nov dec */
	};

	/** Compute which days to put where, in the Cal panel */
	public void print(int yy, int mm) {
		/** The number of days to leave blank at the start of this month */
		int leadGap = 0;

		System.out.print(months[mm]);		// print month and year
		System.out.print(" ");
		System.out.print(yy);
		System.out.println();

		if (mm < 0 || mm > 11)
			throw new IllegalArgumentException("Month " + mm + " bad, must be 0-11");
		Calendar calendar = new GregorianCalendar(yy, mm, 1);

		System.out.println("Su Mo Tu We Th Fr Sa");

		// Compute how much to leave before the first.
		// getDay() returns 0 for Sunday, which is just right.
		leadGap = calendar.get(Calendar.DAY_OF_WEEK)-1;

		int daysInMonth = dom[mm];
		if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1)
			++daysInMonth;

		// Blank out the labels before 1st day of month
		for (int i = 0; i < leadGap; i++) {
			System.out.print("   ");
		}

		// Fill in numbers for the day of month.
		for (int i = 1; i <= daysInMonth; i++) {

			// This "if" statement is simpler than fiddling with NumberFormat
			if (i<=9)
				System.out.print(' ');
			System.out.print(i);

			if ((leadGap + i) % 7 == 0)		// wrap if end of line.
				System.out.println();
			else
				System.out.print(' ');
		}
		System.out.println();
	}

	/**
	 * isLeap() returns true if the given year is a Leap Year.
	 *
	 * "a year is a leap year if it is divisible by 4
	 * but not by 100, except that years divisible by 400
	 * *are* leap years." 
	 *	-- Kernighan & Ritchie, _The C Programming Language_, p 37.
	 */
	public boolean isLeap(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		return false;
	}

	/** For testing, a main program */
	public static void main(String av[]) {

		CalendarPage cp = new CalendarPage();
		cp.print(1995, 2-1);

		// and the current month.
		cp.print(2000, 6-1);

	}
}
