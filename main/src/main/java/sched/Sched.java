package sched;

import java.io.*;
import java.util.*;

/** Sched -- command-line scheduler printer.
*
* @author Ian F. Darwin, http://www.darwinsys.com/
*/

public class Sched {

	/** The Calendar for today, expected to be a GregorianCalendar. */
	protected Calendar gc;

	/** The current date */
	protected int year, month, day;

	/** Process one file
	 */
	public void process(String fileName, LineNumberReader is) {
		try {
			String inputLine;
			int nonMatches = 0;

			while ((inputLine = is.readLine()) != null) {
				Appt a = Appt.fromString(inputLine);
				// Repeat daily for 5 days.
				a.setRep(Appt.DAILY, 1, 5);
				if (a.matches(year, month, day))
					System.out.println(a);
				else ++nonMatches;
			}
			System.out.println(nonMatches +
				" entries not matched");
			is.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

	/** Construct a Scheduler. For now just get day, month, and year.
	 */
	public Sched() {
		gc = Calendar.getInstance();
		if (!(gc instanceof GregorianCalendar))
			System.err.println(
				"Warning: non-Gregorian Calendar"+gc);
		year = gc.get(Calendar.YEAR);
		month = 1 + gc.get(Calendar.MONTH);
		day = gc.get(Calendar.DAY_OF_MONTH);
	}

	public String toString() {
		return "Sched[" + year + "," + month + "," + day + "]";
	}


	/** This simple main program looks after filenames and
	 * opening files and such like for you.
	 */
	public static void main(String[] av) {
		Sched o = new Sched();
		System.out.println(o);
		if (av.length == 0) {
			o.process("standard input", new LineNumberReader(
				new InputStreamReader(System.in)));
		} else {
			for (int i=0; i<av.length; i++)
				try {
					o.process(av[i],
					new LineNumberReader(new FileReader(av[i])));
				} catch (FileNotFoundException e) {
					System.err.println(e);
				}
		}
	}
}
