package regex;

/**
 * Common fields for Apache Log demo.
 */
public interface LogExample {
	/** The number of fields that must be found. */
	public static final int NUM_FIELDS = 9;

	/** The sample log entry to be parsed. */
	public static final String logEntryLine = 
		"123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";

}
