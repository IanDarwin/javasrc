import java.text.*;
import java.util.*;

/**
 * Roman Number class. Not localized, since Roman's a Dead Dead Language
 * and we don't display Roman Numbers differently in different Locales.
 * Filled with quick-n-dirty algorithms.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RomanNumberFormat extends Format {

	/** Characters used in "Arabic to Roman", that is, format() methods. */
	static char A2R[][] = {
			{ 0, 'M' },
			{ 0, 'C', 'D', 'M' },
			{ 0, 'X', 'L', 'C' },
			{ 0, 'I', 'V', 'X' },
	};

	/** Format a given double as a Roman Numeral; just truncate to a
	 * long, and call format(long).
	 */
	public String format(double n) {
		return format((long)n);
	}

	/** Format a given long as a Roman Numeral. Just call the
	 * three-argument form.
	 */
	public String format(long n) {
		if (n < 0 || n >= 4000)
			throw new IllegalArgumentException(n + " must be >= 0 && < 4000");
		StringBuffer sb = new StringBuffer();
		format(new Integer((int)n), sb, new FieldPosition(NumberFormat.INTEGER_FIELD));
		return sb.toString();
	}

	/* Format the given Number as a Roman Numeral, returning the
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

		// First, put the digits on a tiny stack. Must be 4 digits.
		for (int i=0; i<4; i++) {
			int d=n%10;
			push(d);
			// System.out.println("Pushed " + d);
			n=n/10;
		}

		// Now pop and convert.
		for (int i=0; i<4; i++) {
			int ch = pop();
			// System.out.println("Popped " + ch);
			if (ch==0)
				continue;
			else if (ch <= 3) {
				for(int k=1; k<=ch; k++)
					sb.append(A2R[i][1]); // I
			}
			else if (ch == 4) {
				sb.append(A2R[i][1]);	// I
				sb.append(A2R[i][2]);	// V
			}
			else if (ch == 5) {
				sb.append(A2R[i][2]);	// V
			}
			else if (ch <= 8) {
				sb.append(A2R[i][2]);	// V
				for (int k=6; k<=ch; k++)
					sb.append(A2R[i][1]);	// I
			}
			else { // 9
				sb.append(A2R[i][1]);
				sb.append(A2R[i][3]);
			}
		}
		// fp.setBeginIndex(0);
		// fp.setEndIndex(3);
		return sb;
	}

	/** Parse a generic object, returning an Object */
	public Object parseObject(String what, ParsePosition where) {
		throw new IllegalArgumentException("Parsing not implemented");
		// TODO PARSING HERE
		// if (!(what instanceof String)
		// 	throw new IllegalArgumentException(what + " must be String");
		// return new Long(0);
	}

	/* Implement a toy stack */
	protected int stack[] = new int[10];
	protected int depth = 0;

	/* Implement a toy stack */
	protected void push(int n) {
		stack[depth++] = n;
	}
	/* Implement a toy stack */
	protected int pop() {
		return stack[--depth];
	}
}
