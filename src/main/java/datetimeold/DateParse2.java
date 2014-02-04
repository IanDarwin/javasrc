package datetimeold;

import java.text.*;
import java.util.*;

/** Show some date uses */
public class DateParse2 {
	public static void main(String[] args) {

		SimpleDateFormat formatter =
			new SimpleDateFormat("yyyy-MM-dd");
		String[] input = { 
			"BD: 1913-10-01 Vancouver, B.C.",
			"MD: 1948-03-01 Ottawa, ON",
			"DD: 1983-06-06 Toronto, ON" };
		for (String aLine : input) {
			String action;
			switch(aLine.charAt(0)) {
				case 'B': action = "Born"; break;
				case 'M': action = "Married"; break;
				case 'D': action = "Died"; break;
				// others...
				default: System.err.println("Invalid code in " + aLine);
				continue;
			}
			ParsePosition pp = new ParsePosition(aLine.indexOf(' '));
			Date d = formatter.parse(aLine, pp);
			if (d == null) {
				System.err.println("Invalid date in " + aLine);
				continue;
			}
			String location = aLine.substring(pp.getIndex());
			System.out.println(
				action + " on " + d + " in " + location);
		}
	}
}
