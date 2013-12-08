package numbers;

/**
 * Find the first integer number that cannot be Palindromified.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class PalindromeFirst {
	public static void main(String[] argv) {
		int numErrors = 0;
		Palindrome.verbose = false;
		for (int i=0; i<Long.MAX_VALUE; i++) {
			try {
				Palindrome.findPalindrome(i);
			} catch (RuntimeException ex) {
				System.out.println("Caught exception on " + i + ": " + ex);
				numErrors = numErrors + 1;
				if (numErrors > 200)
					return;
			}
		}
	}
}
