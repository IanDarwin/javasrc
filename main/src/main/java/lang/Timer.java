package jndi;

/**
* Measure the time taken for a given operation.
*/
public abstract class Timer {

	long startTime, endTime;

	void start() {
		startTime = System.currentTimeMillis();
	}
	void end(String mesg) {
		endTime = System.currentTimeMillis();
		System.out.printf("%s took %6.3f seconds%n",
			mesg, (endTime - startTime) / 1000D);
	}

	void main() throws Exception {

		start(); end("Calibration run");

		preTimingSetup();

		start();

		doFirstThing();
		
		end("Work #1 done");

		start();
		
		doSecondThing();

		end("Work #2 done");
	}

	public void preTimingSetup() {
		// empty
	}

	// do the work here - required
	public abstract void doFirstThing();

	public void doSecondThing() {
		// Optional to override - 
		// for when you have two things to compare.
	}
}

