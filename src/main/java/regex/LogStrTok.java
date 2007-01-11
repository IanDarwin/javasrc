package regex;

import java.util.*;

/**
 * Parse an Apache log file with StringTokenizer
 */
public class LogStrTok implements LogExample {
	public static void main(String argv[]) {

		StringTokenizer matcher = new StringTokenizer(logEntryLine);

		System.out.println("tokens = " + matcher.countTokens());
		// StringTokenizer CAN NOT count if you are changing the delimiter!
		// if (matcher.countTokens() != NUM_FIELDS) {
		// 	System.err.println("Bad log entry (or bug in StringTokenizer?):");
		// 	System.err.println(logEntryLine);
		// }

		System.out.println("Hostname: " + matcher.nextToken());
		// StringTokenizer makes you ask for tokens in order to skip them:
		matcher.nextToken(); // eat the "-"
		matcher.nextToken(); // again
		System.out.println("Date/Time: " + matcher.nextToken("]"));
		//matcher.nextToken(" "); // again
		System.out.println("Request: " + matcher.nextToken("\""));
		matcher.nextToken(" "); // again
		System.out.println("Response: " + matcher.nextToken());
		System.out.println("ByteCount: " + matcher.nextToken());
		System.out.println("Referer: " + matcher.nextToken("\""));
		matcher.nextToken(" "); // again
		System.out.println("User-Agent: " + matcher.nextToken("\""));
	}
}
