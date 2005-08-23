package performance;

/**
 * Benchmark driver. Runs your method "n" times.
 * Reports elapsed time in milliseconds and number of objects
 * processed per millisecond.
 * @param args ClassThatImplementsInterface [numberofTimes]

 */
public class TimeTimeable {

	private static int DEFAULT_NUMBER_OF_RUNS = 500000;
	private static int numInvocations = DEFAULT_NUMBER_OF_RUNS;
	
	public static void main(String[] args) throws Exception {
		Timeable t;
		if (args.length == 0) {
			throw new IllegalArgumentException("Usage: TimeTimeable [n] class...");
		}
		int i = 0;
		try {
			String num = args[i];
			setNumInvocations(Integer.parseInt(num));
			++i;
		} catch (NumberFormatException e) {
			// nothing to do
		}
		for (String className : args) {
			System.out.printf("Starting class: %s%n", className);
			t = (Timeable) Class.forName(className).newInstance();
			t.init(args);	
			measureTimes(t);
		}
	}

	public static void measureTimes(Timeable t) {
		long starttime = 0;
		long endtime = 0;
		
		starttime = System.currentTimeMillis();
		for (int i=0; i<numInvocations; i++) {
			t.methodUnderTest();
		}

		endtime = System.currentTimeMillis();

		System.out.println("Elapsed time in milliseconds for " +
			DEFAULT_NUMBER_OF_RUNS + " objects = " + (endtime - starttime));

		System.out.println("Objects per millisecond: " +
			(DEFAULT_NUMBER_OF_RUNS / (endtime - starttime)));
	}


	public static int getNumInvocations() {
		return numInvocations;
	}

	public static void setNumInvocations(int numInvocations) {
		TimeTimeable.numInvocations = numInvocations;
	}

}
