package logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * An alternate Console formatter for java.util.logging;
 * one line instead of 2 per simple record (more
 * if a Throwable is included, of course).
 */
public class JULLogFormatter extends Formatter {
	
	private static boolean printStackTraces = true;	// TODO make this *dynamically* switchable

	public JULLogFormatter(){
		System.out.println("JULLogFormatter.JULLogFormatter()");
	}
	
	@Override
	public String format(LogRecord record) {
		System.out.println("LogFormatter.format()");
		StringBuilder sb = new StringBuilder();
		sb.append(record.getLevel());
		sb.append(' ');
		sb.append(record.getMessage());
		sb.append('\n');
		Throwable tough = record.getThrown();
		if (tough != null) {
			if (printStackTraces) {
				for (StackTraceElement ste : tough.getStackTrace()) {
					sb.append(ste).append("\n");
				}
			} else {
				sb.append(tough);
			}
		}
		return sb.toString();
	}

	public static boolean isPrintStackTraces() {
		return printStackTraces;
	}

	public static void setPrintStackTraces(boolean printStackTraces) {
		JULLogFormatter.printStackTraces = printStackTraces;
	}
}
