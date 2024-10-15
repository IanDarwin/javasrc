package sched;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.time.LocalDate;

/** Sched -- command-line scheduler printer.
*
* @author Ian F. Darwin, https://darwinsys.com/
*/

public class Sched {

	/** The date today, expected to be a GregorianCalendar. */
	protected LocalDate gc = LocalDate.now();

	/** The current date */
	protected int year = gc.getYear(), month = gc.getMonth(), day = gc.getDayOfMonth();

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
