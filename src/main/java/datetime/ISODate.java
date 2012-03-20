package datetime;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/** Demonstrate a couple of ways to format an ISO 8601 date */
public class ISODate {
	public static void main(String[] args) {
		Date d =  new Date();
		System.out.printf("%1$tY-%1$tm-%1$td%n", d);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(d));
		DateFormat fullIsoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
		System.out.println(fullIsoDateFormat.format(d));
	}
}
