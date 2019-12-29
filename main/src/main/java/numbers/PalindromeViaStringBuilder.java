package numbers;

/** While not strictly a numeric solution, Daniel Hirojosa points out
 * that you can do palindromes with less code using StringBuilder.reverse()
 * as in this sample code, which I've further shortened slightly.
 * @Author Daniel Hinojosa
 */
public class PalindromeViaStringBuilder {

	public static void main(String[] argv) {
		for (String num : argv) {
			try {
				long l = Long.parseLong(num);
				if (l < 0) {
					System.err.println(num + " -> TOO SMALL");
					continue;
				}
				System.out.println(num + "->" + findPalindrome(l));
			} catch (NumberFormatException e) {
				System.err.println(num + "-> INVALID");
			} catch (IllegalStateException e) {
				System.err.println(num + "-> " + e);
			}
		}
	}

	public static long findPalindrome(long num) {
		if (num < 0) throw new IllegalArgumentException("Negative numbers not supported");
		else if (isPalindrome(num)) return num;
		else {
			System.out.format("Trying %d%n", num);
			return findPalindrome(num + reverseNumber(num));
		}
	}

	static boolean isPalindrome(long num) {
		long result = reverseNumber(num);
		return num == result;
	}

	private static long reverseNumber(long num) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(num);
		return Long.parseLong(stringBuilder.reverse().toString());
	}
}
