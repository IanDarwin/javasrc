package testing;

/**
 * Some tools (including JLint) will report a false "o may be null" here due to
 * not understanding that instanceof returns false if the left operator is null
 * Sample JLint comment:
 * ./testing/FalsePositiveNull.java:19: Value of referenced variable 'o' may be NULL.
 */
public class FalsePositiveNull {
	public static void main(String[] args) {
		runWith(null);
		runWith(new Object());
	}

	private static void runWith(Object o) {
		if (!(o instanceof Object)) {
			return;
		}
		// It is categorically impossible for o to be null here.
		System.out.println(o.toString());
	}
}
