package datetimeold;

import java.text.*;
import java.util.*;

/** Show some date uses */
public class DateDemo {
    public static void main(String[] args) {
		Date dNow = new Date();

		/* Simple, Java 1.0 date printing */
		System.out.println("It is now " + dNow.toString());

		// Use a SimpleDateFormat to print the date our way.
		SimpleDateFormat formatter
			= new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println("It is " + formatter.format(dNow));
	}
}
