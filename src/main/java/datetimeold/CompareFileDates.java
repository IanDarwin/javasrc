package datetimeold;

import java.io.File;

public class CompareFileDates {
	public static void main(String[] args) {
		// Get the timestamp from file 1
		String f1 = args[0];
		long d1 = new File(f1).lastModified();

		// Get the timestamp from file 2
		String f2 = args[1];
		long d2 = new File(f2).lastModified();

		String relation;
		if (d1 == d2)
			relation = "the same age as";
		else if (d1 < d2)
			relation = "older than";
		else
			relation = "newer than";
		System.out.println(f1 + " is " + relation + ' ' + f2);
	}
}
