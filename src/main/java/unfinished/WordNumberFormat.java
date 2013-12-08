package unfinished;

import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * Word Number Class. Convert english words like
 * "one hundred thousand 12" to long.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class WordNumberFormat extends Format {

	private static final long serialVersionUID = 0L;

	class map {
		String name;
		long value;
		map(String s, long v) {
			name = s; value = v;
		}
	}
	map[] numerals = {
		new map("one", 1),
		new map("two", 2),
		new map("three", 3),
		new map("four", 4),
		new map("five", 5),
		new map("six", 6),
		new map("seven", 7),
		new map("eight", 8),
		new map("nine", 9),
	};

	/** Format a given double as a Word Numeral; just truncate to a
	 * long, and call format(long).
	 */
	public String format(double n) {
		return format((long)n);
	}

	/** Format a given long as a Word Numeral. Just call the
	 * three-argument form.
	 */
	public String format(long n) {
		return NumberFormat.getInstance().format(n);
	}

	/* Format the given Number as a Word Numeral, returning the
	 * Stringbuffer (updated), and updating the FieldPosition.
	 * This method is the REAL FORMATTING ENGINE.
	 * Method signature is overkill, but required as a subclass of Format.
	 */
	public StringBuffer format(Object on, StringBuffer sb, FieldPosition fp) {
		if (!(on instanceof Number))
			throw new IllegalArgumentException(on + " must be a Number object");
		if (fp.getField() != NumberFormat.INTEGER_FIELD)
			throw new IllegalArgumentException(fp + " must be FieldPosition(NumberFormat.INTEGER_FIELD");
		int n = ((Number)on).intValue();

		return null;
	}

	/** Parse a generic object, returning an Object */
	public Object parseObject(String what, ParsePosition where) {
		throw new IllegalArgumentException("Parsing not implemented");
		// TODO PARSING HERE
		// return new Long(0);
	}
}
