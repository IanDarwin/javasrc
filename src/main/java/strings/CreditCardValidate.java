package strings;

/**
 * Credit Card Validation
 */
public class CreditCardValidate {

	/** Returns a credit card string with spaces, dashes, etc., stripped out.
	 * Consider it a token of user-friendly in an age of really really stupid web sites.
	 * @param input The user-entered credit card string
	 * @return The cleaned up credit card string.
	 */
	public static String clean(final String input) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isDigit(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/** This "IBM" or "Luhn" algorithm has been described as:
	 * "the permutation s = (0)(1,2,4,8,7,5)(3,6)(9);
	 * if n is even, s(a1) + a2 + s(a3)... else a2 + s(a2) + a3..."
	 * (at http://www.academic.marist.edu/mwa/cccard.html) and
	 * "For a card with an even number of digits, double every odd numbered digit and subtract 9 if the product 
	 * is greater than 9. Add up all the even digits as well as the doubled-odd digits, and the result must 
	 * be a multiple of 10 or it's not a valid card. 
	 * If the card has an odd number of digits, perform the same addition doubling the 
	 * even numbered digits instead." (http://www.phrack.org/show.php?p=47&a=8, quoted at
	 * http://www.merriampark.com/anatomycc.htm).
	 * @param input The string form of the credit card number.
	 * @return True iff the value is a valid credit card number.
	 */
	public static boolean isValidCard(String input) {
		input = clean(input);
		final int len = input.length();
		int sum = 0;
		boolean doubleIt = false;

		for (int i = len - 1; i >= 0; i--) {
			int val = input.charAt(i) - '0';
			int addend = 0;
			if (doubleIt) {
				addend = val * 2;
				if (addend > 9) {
					addend -= 9;
				}
			} else {
				addend = val;
			}
			sum += addend;
			doubleIt = !doubleIt;
		}
		return sum % 10 == 0;
	}
}
