package logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * An alternate Console formatter for java.util.logging;
 * one line instead of 2 per record.
 */
public class JULLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		// System.out.println("LogFormatter.format()");
		StringBuilder sb = new StringBuilder();
		sb.append(record.getLevel());
		sb.append(' ');
		sb.append(record.getMessage());
		sb.append('\n');
		return sb.toString();
	}

}
