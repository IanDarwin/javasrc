package numbers;

import java.math.BigInteger;

/** Compute the Palindrome of a number by adding the number composed of
 * its digits in reverse order, until a Palindrome occurs.
 * e.g., 42->66 (42+24); 1951->5995 (1951+1591=3542; 3542+2453=5995).
 * <P>TODO: Do we need to handle negative numbers?
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class PalindromeBig {

	public static boolean verbose = true;

	public static void main(String[] argv) {
		for (int i=0; i<argv.length; i++)
			try {
				BigInteger l = new BigInteger(argv[i]);
				if (l.compareTo(BigInteger.ZERO) < 0) {
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
	public static BigInteger findPalindrome(BigInteger num) {
		if (num.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalStateException("negative");
		if (isPalindrome(num))
			return num;
		if (verbose)
			System.out.println("Trying " + num);
		return findPalindrome(num.add(reverseNumber(num)));
	}

	/** A ridiculously large number  */
	protected static final int MAX_DIGITS = 255;

	/** Check if a number is palindromic. */
	public static boolean isPalindrome(BigInteger num) {
		String digits = num.toString();
		int numDigits = digits.length();
		if (numDigits >= MAX_DIGITS) {
			throw new IllegalStateException("too big");
		}
		// Consider any single digit to be as palindromic as can be
		if (numDigits == 1)
			return true;
		for (int i=0; i<numDigits/2; i++) {
			// System.out.println(
			// 	digits.charAt(i) + " ? " + digits.charAt(numDigits - i - 1));
			if (digits.charAt(i) != digits.charAt(numDigits - i - 1))
				return false;
		}
		return true;
	}

	static BigInteger reverseNumber(BigInteger num) {
		String digits = num.toString();
			int numDigits = digits.length();
		char[] sb = new char[numDigits];
		for (int i=0; i<digits.length(); i++) {
			sb[i] = digits.charAt(numDigits - i - 1);
		}
		// Debug.println("rev",
		// 	"reverseNumber(" + digits + ") -> " + "\"" + sb + "\"");
		return new BigInteger(new String(sb));
	}
}
