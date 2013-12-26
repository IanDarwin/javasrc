package numbers;

/** Compute the Palindrome of a number by adding the number composed of
 * its digits in reverse order, until a Palindrome occurs.
 * e.g., 42->66 (42+24); 1951->5995 (1951+1591=3542; 3542+2453=5995).
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Palindrome {

	public static boolean verbose = true;

	public static void main(String[] argv) {
		for (int i=0; i<argv.length; i++)
			try {
				long l = Long.parseLong(argv[i]);
				if (l < 0) {
					System.err.println(argv[i] + " -> TOO SMALL");
					continue;
				}
				System.out.println(argv[i] + "->" + findPalindrome(l));
			} catch (NumberFormatException e) {
				System.err.println(argv[i] + "-> INVALID");
			} catch (IllegalStateException e) {
				System.err.println(argv[i] + "-> " + e);
			} 
	}

	/** find a palindromic number given a starting point, by
	 * calling ourself until we get a number that is palindromic.
	 */
	static long findPalindrome(long num) {
		if (num < 0)
			throw new IllegalStateException("negative");
		if (isPalindrome(num))
			return num;
		if (verbose)
 			System.out.println("Trying " + num);
		return findPalindrome(num + reverseNumber(num));
	}

	/** The number of digits in Long.MAX_VALUE */
	protected static final int MAX_DIGITS = 19;

	// digits array is shared by isPalindrome and reverseNumber,
	// which cannot both be running at the same time.

	/* Statically allocated array to avoid new-ing each time. */
	static long[] digits = new long[MAX_DIGITS];

	/** Check if a number is palindromic. */
	static boolean isPalindrome(long num) {
		// Consider any single digit to be as palindromic as can be
		if (num >= 0 && num <= 9)
			return true;

		int nDigits = 0;
		while (num > 0) {
			digits[nDigits++] = num % 10;
			num /= 10;
		}
		for (int i=0; i<nDigits/2; i++)
			if (digits[i] != digits[nDigits - i - 1])
				return false;
		return true;
	}

	static long reverseNumber(long num) {
		int nDigits = 0;
		while (num > 0) {
			digits[nDigits++] = num % 10;
			num /= 10;
		}
		long ret = 0;
		for (int i=0; i<nDigits; i++) {
			ret *= 10;
			ret += digits[i];
		}
		return ret;
	}
}
// END main
