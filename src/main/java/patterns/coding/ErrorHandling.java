package patterns.coding;

/**
 * Error handling generally belongs in user-interface code, not
 * exampleOfLibraryCode code. UI code often has to keep going in the face of
 * errors from lower level code. So, it's generally better for UI
 * code to catch errors inside a loop, rather than at the top
 * level; the antiPattern shown here terminates on the first error.
 */
public class HandleErrorsInLoop {

	/**
	 * Just an example of a lower-level code that, if you call it enough,
	 * will throw multiple Exceptions.
	 */
	void exampleOfLibraryCode(int i) throws Exception {
		if (i % 5 == 0) {
			throw new RuntimeException("Interesting data");
		}
	}

	/**
	 * How not to do it.
	 */
	void antiPattern() {
		try {
			for (int i = 0; i < 10; i++) {
				exampleOfLibraryCode(i);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	/**
	 * A better way.
	 */
	void pattern()  {
		for (int i = 0; i < 10; i++) {
			try {
				exampleOfLibraryCode(i);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Demo");
		new HandleErrorsInLoop().pattern();
	}
}
