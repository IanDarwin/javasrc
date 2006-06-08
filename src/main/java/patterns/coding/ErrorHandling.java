package patterns.coding;

/**
 * Error handling generally belongs in user-interface code, not
 * in lower-level code. UI code often has to keep going in the face of
 * errors from lower level code. So, it's generally better for UI
 * code to catch errors inside a loop, rather than at the top
 * level; the antiPattern shown here terminates on the first error.
 */
public class ErrorHandling {

	/**
	 * Just an example of a lower-level code that, if you call it enough,
	 * will throw multiple Exceptions.
	 */
	void exampleOfLibraryCode(int i) throws Exception {
		if (i % 5 == 0) {	// i.e., detect an error
			throw new RuntimeException("Interesting data");
		}
	}	
	
	/**
	 * This is a bad example of the above, because it will fail silently if
	 * called from a GUI or from a Servlet - let the calling code display
	 * errors to the user, it's the only place that knows how!
	 */
	void badExampleOfLibraryCode(int i) throws Exception {
		if (i % 5 == 0) {	// i.e., detect an error
			System.out.println("Interesting data");
		}
	}

	/** How not to do it. */
	void antiPattern() {
		try {
			for (int i = 0; i < 10; i++) {
				exampleOfLibraryCode(i);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	/** A better way. */
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
		new ErrorHandling().pattern();
	}
}
