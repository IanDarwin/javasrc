package datetimeold;

public class CalUtils {

	final static int DOM[] = {
		31, 28, 31, 30, /* jan feb mar apr */
		31, 30, 31, 31, /* may jun jul aug */
		30, 31, 30, 31 /* sep oct nov dec */
	};

	public static int[] getDaysInMonths() {
		return DOM.clone();
	}
	
	public static int getDaysInMonth(int monthNum) {
		if (monthNum < 0) {
			throw new IllegalArgumentException("Month number must be non-negative");
		}
		if (monthNum >= 12) {
			throw new IndexOutOfBoundsException("There are 12 months in a year, so monthNum must be in 0..11");
		}
		return DOM[monthNum];
	}
}
