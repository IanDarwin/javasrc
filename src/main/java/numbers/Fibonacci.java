package numbers;

/**
 * Fibonacci numbers - print just the 'n'th Fibonacci number.
 */
public class Fibonacci {

	private final static boolean debug = false;

	public static void main(String[] args) {
		int n = args.length > 0 ? Integer.parseInt(args[0]) : 0;
		try {
			System.out.println(n + "->" + fibonacci(n));
		} catch (StackOverflowError e) {
			System.err.println(
				"Stack overflow; bump mem or reduce number: " + n);
		}
	}

	public static int fibonacci(int n) {
		if (debug) 
			System.out.println( n );
		if (n == 0) 
			return 0;
		if (n == 1)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
}
