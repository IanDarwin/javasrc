package datetimeold;

import java.text.*;
import java.util.*;

/** Show some date uses */
public class DateParse1 {
	public static void main(String[] args) {

		SimpleDateFormat formatter
			= new SimpleDateFormat("yyyy-MM-dd");
		String input = args.length == 0 ? "1818-11-11" : args[0];
		System.out.print(input + " parses as ");
		Date t;
		try {
			t = formatter.parse(input);
			System.out.println(t);
		} catch (ParseException e) {
			System.out.println("unparseable using " + formatter);
		}
	}
}
