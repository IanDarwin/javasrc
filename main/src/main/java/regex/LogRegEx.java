package regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse an Apache log file with Regular Expressions
 */
// tag::main[]
public class LogRegEx {

	public static final int MIN_FIELDS = 8;

	// Sample line: 123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] "GET /java/javaResources.html HTTP/1.0" 200 10450 "-" "Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Navigator)"

	/** Multi-line regex, but text block keeps leading tabs, so undent. */
	final static String LOG_ENTRY_PATTERN = """
^([\\w\\d.-]+)\\s+      # 1 - IP
(\\S+)\\s+              # 2 - User, from identd (always "-")
(\\S+)\\s+              # 3 - User, from https (often "-")
\\[([\\w:/]+\\s[+-]\\d{4})\\]\\s+ # 4 - Date, time, space, timezone-offset
([a-zA-Z.]+\\s+)?       # 5 - domainname, in some formats
"(.+?)"\\s+             # 6 - Entire request line
(\\d{3})\\s+            # 7 - Status code (200, 404, etc)
(\\d+)\\s*              # 8 - Byte count
("[^"]+"\\s*)?          # 9 - Referrer, or "-"
("([^"]+)")?            # 10 - Browser advertising clause, free-form
""";					

	public static void main(String argv[]) throws IOException {

		Pattern p = Pattern.compile(LOG_ENTRY_PATTERN, Pattern.COMMENTS);
		System.out.println("RE Pattern:");
		System.out.println(p);

		for (String fileName : argv) {
		Files.lines(Path.of(fileName)).forEach(logEntryLine -> {

			System.out.println("Input line:" + logEntryLine);
			Matcher matcher = p.matcher(logEntryLine);
			if (!matcher.find()) {
				System.err.println("Failed to match (Bad log entry or problem with regex)");
				return;
			}
			if (matcher.groupCount() < MIN_FIELDS) {
				System.err.println("Matched, but has too few fields):");
				return;
			}
			System.out.println("IP Address: " + matcher.group(1));
			System.out.println("UserName: " + matcher.group(3));
			System.out.println("Date/Time: " + matcher.group(4));
			System.out.println("Request: " + matcher.group(5));
			System.out.println("Response: " + matcher.group(7));
			System.out.println("Byte Count: " + matcher.group(8));
			if (!matcher.group(9).equals("-"))
				System.out.println("Referer: " + matcher.group(9));
			System.out.println("User-Agent: " + matcher.group(10));
		});
	}
	}
}
// end::main[]
