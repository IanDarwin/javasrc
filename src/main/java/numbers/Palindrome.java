/** Compute the Palindrome of a number by adding the number composed of
 * its digits in reverse order, until a Palindrome occurs.
 * e.g., 42->66 (42+24); 1951->5995 (1951+1591=3542; 3542+2453=5995).
 * <P>TODO:
 * <BR>Handle negative numbers?
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$.
 */
public class Palindrome {
	public static void main(String argv[]) {
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
				System.err.println(argv[i] + "-> TOO BIG(went negative)");
			} 
	}

	static long findPalindrome(long num) {
		if (num < 0)
			throw new IllegalStateException("went negative");
		if (isPalindrome(num))
			return num;
		System.out.println("Trying " + num);
		return findPalindrome(num + reverseNumber(num));
	}

	static boolean isPalindrome(long num) {
		if (num >= 0 && num < 9)
			return true;
		String nn = Long.toString(num);
		int len = nn.length();
		for (int i=0; i<len/2; i++)
			if (nn.charAt(i) != nn.charAt(len - i - 1))
				return false;
		return true;
	}

	/** The number of digits in Long.MAX_VALUE */
	protected static final int MAX_DIGITS = 19;

	/* Statically allocated array to avoid new-ing each time. */
	static long[] digits = new long[MAX_DIGITS];

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
