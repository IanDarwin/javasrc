import java.util.regex.*;

/**
 * Parse an Apache log file with Regular Expressions
 */
public class LogRegExp implements LogExample {

	public static void main(String argv[]) {

		String logEntryPattern = "^([\\d.]+) (\\S+) ([^\\s]+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"([^\"]+)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

		Pattern p = Pattern.compile(logEntryPattern);
		Matcher matcher = p.matcher(logEntryLine);
		if (!matcher.matches() || 
			NUM_FIELDS != matcher.groupCount()) {
			System.err.println("Bad log entry (or problem with RE?):");
			System.err.println(logEntryLine);
		}
		System.out.println("IP Address: " + matcher.group(1));
		System.out.println("Request: " + matcher.group(5));
		if (!matcher.group(8).equals("-"))
			System.out.println("Referer: " + matcher.group(8));
		System.out.println("Browser: " + matcher.group(9));
	}
}
