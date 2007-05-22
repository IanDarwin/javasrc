package lang;

public class WeirdMethod {
	/**
	 * Weird method, from Angel Evrov via Petar Tahchiev's Blog.
	 * Method seemingly should not compile as it does not return an int,
	 * but both Sun's JavaC and IBM's Jikes are happy with it.
	 * Without looking, I bet the JLS says it is recognized as non-returning.
	 */
	public static int testMethod() {
		for(;;) {
		}
	}
}
