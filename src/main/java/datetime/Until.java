import java.text.*;
import java.util.*;
/**
 * Until - sleep until a time in the future.
 */
public class Until {
	public static void main(String[] argv) throws ParseException {
		Date until = DateFormat.getDateInstance().parse(argv[0]);
		System.out.println(until);
	}
}
