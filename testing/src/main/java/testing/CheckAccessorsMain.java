package testing;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class CheckAccessorsMain {
	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException(
				String.format("Usage: %s directory [...]",
					CheckAccessorsMain.class.getName()));
		}
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		System.setProperty(CheckAccessorsDirectory.DIRECTORY_KEY, args[0]);
		Result result = junit.run(CheckAccessorsDirectory.class);
			System.out.printf(
				"CheckAccessors Results:\n" +
				"Failures %d. Ignored %d. %d tests run in %d mSec.",
				result.getFailureCount(),
				result.getIgnoreCount(),
				result.getRunCount(),
				result.getRunTime());
	}
}
