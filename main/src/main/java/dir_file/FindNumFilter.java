package dir_file;

/** class to handle a numeric filter for Find, such as:
 * <li>5, meaning exactly 5
 * <li>+5, meaning greater than 5
 * <li>-5, meaning less than 5
 */
class FindNumFilter {
	/** The value of this filter */
	int num;
	/** Constants for the comparison operators. */
	final static int LE = -2, LT = -1, EQ = 0, GT = +1, GE = +2;
	/** The current comparison operator */
	int mode = EQ;

	/** Constructor */
	FindNumFilter(String input) {
		switch(input.charAt(0)) {
			case '+': mode = GT; break;
			case '-': mode = LT; break;
			case '=': mode = EQ; break;
			// No syntax for LE or GE yet.
		}
		num = Math.abs(Integer.parseInt(input));
	}

	/** Construct a NumFilter when you know its mode and value */
	FindNumFilter(int mode, int value) {
		this.mode = mode;
		num = value;
	}
	boolean accept(int n) {
		switch(mode) {
			case GT: return n > num;
			case EQ: return n == num;
			case LT: return n < num;
			default:
				System.err.println("UNEX CASE " + mode );
				return false;
		}
	}
}
