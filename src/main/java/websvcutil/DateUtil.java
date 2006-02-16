package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date-to-string and back, in "standard" yyyy-MM-dd'T'HH:mm format
 * @author ian
 */
public class DateUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateFormat df = getDateFormatInstance();
		Calendar c = Calendar.getInstance();
		System.out.println(df.format(c.getTime()));
		try {
			Date date = df.parse("2112-04-24T12:30");
			System.out.println(df.format(date));
		} catch (ParseException e) {
			System.out.println("df parse threw exception " + e);
		}
	}

	private static DateFormat df;
	
	public static DateFormat getDateFormatInstance() {
		if (df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		}
		return df;
	}
}
