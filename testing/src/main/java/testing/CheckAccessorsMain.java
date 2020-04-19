package testing;

public class CheckAccessorsMain {
	public static void main(String args) {
		if (args.length != 1) {
			throw new IllegalArgumentException(
				String.format("Usage: %s directory [...]",
					CheckAccessorsMain.class.getName()));
		}
		JunitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		System.setProperty(CheckAccessorsDirectory.DIRECTORY_KEY, argv[0]);
		Result result = junit.run(CheckAccessorsDirectory.class);
			System.out.printf(
				"CheckAccessors Results:\n" +
				"Errors %d. Failures %d. Ignored %d. %d testsrun in %d mSec." +
				result.getErrorCount(),
				result.getFailureCount(),
				result.getIgnoreCount(),
				result.getRunCount(),
				result.getRunTime());
	}
}
