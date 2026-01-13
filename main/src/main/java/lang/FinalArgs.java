package lang;

/**
 * Experiment with "final" args to functions (new in 1.1)
 */
public class FinalArgs {
	public static void main(String argv[]) {
		new FinalArgs().run();
	}
	void run() {
		StringBuilder sb = new StringBuilder("Today's the day!");
		System.out.println("sb = " + sb);
		myFunc(sb);
		System.out.println("sb = " + sb);
	}
	void myFunc(final StringBuilder sb) {
		// sb = null;	// this will not compile
		// this will compile, and changes the object
		sb.setLength(0); sb.append("No, it's not!");
	}
}
