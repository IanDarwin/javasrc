package numbers;

/**
 * Convert an integer number to words.
 * @author Original by Learning Tree Course 516 Team, http://learningtree.com/
 * @author Cleaned up and extended by Ian Darwin, http://darwinsys.com/
 */
public class NumberToWordsConverter {
	
	/** The maximum that this has been extended to. */
	static final int MAX = 1000;
	private static final String[] lowNums = {
		"zero", "one", "two", "three",
		"four", "five", "six", "seven",
		"eight", "nine", "ten",
		"eleven", "twelve", "thirteen", "fourteen",
		"fifteen", "sixteen", "seventeen", "eighteen",
		"nineteen"
	};
	private static final String[] tens = {
		null, null,
		"twenty", "thirty", "forty", "fifty",
		"sixty", "seventy", "eighty", "ninety"
	};

	/**
	 * Given an integer in the range -20 to 20 will return a String with
	 * that number converted to words. For example, an input of 15 results in 
	 * an output of "fifteen". An input of -4 returns "minus four".
	 * If the integer is not in the range -MAX to MAX it throws an 
	 * IllegalArgumentException.
	 * 
	 * @param num A number to be converted to words.
	 * @return a String, the number as words.
	 */	
	public static String convert(int num) {
		if (Math.abs(num) > MAX) {
			throw new IllegalArgumentException();
		}
		if (num < 0) {
			return "minus " + convert(-num);
		}
		if (num >= 100) {
			int hun = num / 100;
			return convert(hun) + " hundred" +
				(num % 100 > 0 ?
					" " + convert(num % 100) :
					"");
		}
		if (num >= 20) {
			int ten = num / 10;
			return tens[ten] + 
				(num % 10 > 0 ?
					" " +convert(num % 10) :
					"");
		}
		return lowNums[num];
	}
}
