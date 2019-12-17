package regex;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Parse an Apache log file with Regular Expressions
 */
// tag::main[]
public class LogRegEx {

	final static String LOG_ENTRY_PATTERN = 
			"^([\\w\\d.-]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] " +
			"([a-zA-Z.]+ )?" +
			"\"(.+?)\" (\\d{3}) (\\d+)( \"([^\"]+)\")?( \"([^\"]+)\")?";

	public static void main(String argv[]) throws IOException {

		Pattern p = Pattern.compile(LOG_ENTRY_PATTERN);
		System.out.println("RE Pattern: " + p);

		String fileName = argv[0];
		Files.lines(Path.of(fileName)).forEach(logEntryLine -> {

			System.out.println("Input line:" + logEntryLine);
			Matcher matcher = p.matcher(logEntryLine);
			if (!matcher.matches() || 
				LogParseInfo.MIN_FIELDS > matcher.groupCount()) {
				System.err.println("Bad log entry (or problem with regex):");
				System.err.println(logEntryLine);
				return;
			}
			System.out.println("IP Address: " + matcher.group(1));
			System.out.println("UserName: " + matcher.group(3));
			System.out.println("Date/Time: " + matcher.group(4));
			System.out.println("Request: " + matcher.group(5));
			System.out.println("Response: " + matcher.group(6));
			System.out.println("Bytes Sent: " + matcher.group(7));
			if (!matcher.group(8).equals("-"))
				System.out.println("Referer: " + matcher.group(8));
			System.out.println("User-Agent: " + matcher.group(9));
		});
	}
}
// end::main[]
