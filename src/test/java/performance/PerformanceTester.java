package performance;

public class PerformanceTester {
	private int amount;
	private static int NOBJECTS = 500000;

	public void addAmount(int val) {
		amount += val;
	}

	public int getAmount() {
		return amount;
	}

	/**
	 * Benchmark driver. Creates half a million of simple account
	 * objects, adds an amount to each one, and then computers the
	 * sum of all amounts in all objects.
	 * Reports elapsed time in milliseconds and number of objects
	 * processed per millisecond.
	 */
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		
		PerformanceTester[] ptarr = new PerformanceTester[NOBJECTS];

		for (int i=0; i<NOBJECTS; i++) {
			ptarr[i] = new PerformanceTester();
		}

		for (int i=0; i<NOBJECTS; i++) {
			ptarr[i].addAmount(i);
		}

		for (int i=0; i<NOBJECTS; i++) {
		}

		long endtime = System.currentTimeMillis();

		System.out.println("Elapsed time in milliseconds for " +
			NOBJECTS + " objects = " + (endtime - starttime));

		System.out.println("Objects per millisecond: " +
			(NOBJECTS / (endtime - starttime)));
	}
}
