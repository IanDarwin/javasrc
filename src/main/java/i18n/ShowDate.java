import java.text.*;
import java.util.*;

public class Patt {
	public static void main(String[] args) {
		DateFormat df = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CANADA_FRENCH);
		System.out.println(df.format(new Date()));
	}
}
