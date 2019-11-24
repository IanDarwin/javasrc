package strings;

/** Disguise a credit card showing only the last 4 digits */
public class CreditCardDisguise {
	
	final static String xxxs = "XXXXXXXXXXXXXXXXXXXX";

	public static String disguise(String cardNum) {
		int n = cardNum.length();
		if (n < 12 || n > 20) {
			throw new IllegalArgumentException("Card Number length must be in (12,20}: " + cardNum);
		}
		return xxxs.substring(0, n - 4) + cardNum.substring(n - 4);
	}
}
