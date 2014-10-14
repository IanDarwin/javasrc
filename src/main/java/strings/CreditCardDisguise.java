package strings;

/** Disguise a credit card showing only the last 4 digits */
public class CreditCardDisguise {
	
	final static String xxxs = "XXXXXXXXXXXXXXXXXXXX";

	public static String disguise(String cardNum) {
		int n = cardNum.length();
		if (n < 12) {
			throw new IllegalArgumentException("Too short (minimum 12): " + cardNum);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(xxxs.substring(0, n - 4));
		sb.append(cardNum.substring(n - 4));
		return sb.toString();
	}
}
