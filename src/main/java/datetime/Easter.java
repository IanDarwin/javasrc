import java.util.Date;
import java.applet.*;

/** easter - compute the day on which Easter falls.
 * In the Christian religion, Easter is possibly the most important holiday
 * of the year, so getting its date <I>just so</I> is worthwhile.
 *
 * @author: Ian F. Darwin, ian@darwinsys.com,
 * based on a detailed algorithm in Knuth, vol 1, pg 155.
 * Written in C, Toronto, 1988. Java version 1996.
 *
 * @Version: $Version$
 *
 * @Note: It's not proven correct, although it gets the right answer for 
 * years around the present.
 */
public class Easter extends Applet {
	protected int year;

	/** Main program, when used as a standalone application */
	public static void main(String argv[]) {
		int year = -44;

		if (argv.length == 0) {
			// Note use of "toss off" Date object
			year = 1900 + new Date().getYear();
		} else for (int i=0; i<argv.length; i++) {
			try {
				year = Integer.parseInt(argv[i]);
				System.out.println(
					new Easter(year).FindHolyDay().toString());
			} catch(NumberFormatException e) {
				System.err.println("Year " + argv[0] + " invalid (" + e.getMessage() + ").");
				System.exit(1);
			} catch (EasterDateException e) {
				System.err.println("Error: Year " + year + 
					" invalid (" + e.getMessage() + ").");
			}
		}
	}

	/** Construct an EasterDate object for the given year Anno Dominei.
	 @exception EasterDateException If the year is before 1582 (since the
			algorithm only works on the Gregorian calendar).
			Also if the year is before 1970, since we currently use a
			Java.util.Date object to represent the day; should change
			this to a Calendar-based object.
	 */
	Easter(int year) throws EasterDateException {
		if (year <= 1582) {
			throw new EasterDateException("Algorithm invalid before 1582");
		}
		if (year <= 1970) {
			throw new EasterDateException("Java Date class invalid before 1970");
		}
		this.year = year;
	}

	public Date FindHolyDay() {
		int golden, century, x, z, d, epact, n;

		golden = (year % 19) + 1;	/* E1: metonic cycle */
		century = (year / 100) + 1;	/* E2: e.g. 1984 was in 20th C */
		x = (3*century/4) - 12;		/* E3: leap year correction */
		z = ((8*century+5) / 25) -5; 	/* E3: sync with moon's orbit */
		d = (5*year/4) - x - 10;
		epact = (11*golden + 20 + z - x) % 30; /* E5: epact */
		if ((epact == 25 && golden > 11) || epact == 24)
			epact++;
		n = 44 - epact;
		n += 30 * (n < 21?1:0);		/* E6: */
		n += 7 - ((d+n)%7);
		if (n>31)			/* E7: */
			return new Date(year-1900, 4-1, n-31);	/* April */
		else
			return new Date(year-1900, 3-1, n);	/* March */
	}
}

/** Exceptions in date input to Easter class. */
class EasterDateException extends Exception {
	EasterDateException() {
		super();
	}
	EasterDateException(String message) {
		super(message);
	}
}
