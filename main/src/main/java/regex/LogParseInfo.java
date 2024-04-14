package regex;

import java.nio.file.*;
import java.io.IOException;

/**
 * Common fields and info for Apache Log Parsing demo.
 */
public interface LogParseInfo {
	/** The number of fields that must be found. */
	public static final int MIN_FIELDS = 8;

	/** The sample log entry to be parsed. */
	public static final String LOG_ENTRY_LINE = 
		"123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";

	public static void makeSample(String fileName) throws IOException {
		Files.writeString(Path.of(fileName), LOG_ENTRY_LINE);
	}
}

