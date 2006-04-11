package strings;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/** Bare-minimum String formatter (string aligner).
 * XXX When 1.5 is common, change from ints to enum for alignment.
 */
public class StringAlign extends Format {
	/* Constant for left justification. */
	public static final int JUST_LEFT = 'l';
	/* Constant for centering. */
	public static final int JUST_CENTRE = 'c';
	/* Centering Constant, for those who spell "centre" the American way. */
	public static final int JUST_CENTER = JUST_CENTRE;
	/** Constant for right-justified Strings. */
	public static final int JUST_RIGHT = 'r';

	/** Current justification */
	private int just;
	/** Current max length */
	private int maxChars;

    /** Construct a StringAlign formatter; length and alignment are
     * passed to the Constructor instead of each format() call as the
     * expected common use is in repetitive formatting e.g., page numbers.
     * @param nChars - the length of the output
     * @param just - one of JUST_LEFT, JUST_CENTRE or JUST_RIGHT
     */
	public StringAlign(int maxChars, int just) {
		switch(just) {
		case JUST_LEFT:
		case JUST_CENTRE:
		case JUST_RIGHT:
			this.just = just;
			break;
		default:
			throw new IllegalArgumentException("invalid justification arg.");
		}
		if (maxChars < 0) {
			throw new IllegalArgumentException("maxChars must be positive.");
		}
		this.maxChars = maxChars;
	}

	/** Format a String.
     * @param input _ the string to be aligned.
     * @parm where - the StringBuffer to append it to.
     * @param ignore - a FieldPosition (may be null, not used but
     * specified by the general contract of Format).
     */
	public StringBuffer format(
		Object obj, StringBuffer where, FieldPosition ignore)  {

		String s = (String)obj;
		String wanted = s.substring(0, Math.min(s.length(), maxChars));

		// Get the spaces in the right place.
		switch (just) {
			case JUST_RIGHT:
				pad(where, maxChars - wanted.length());
				where.append(wanted);
				break;
			case JUST_CENTRE:
				int toAdd = maxChars - wanted.length();
				pad(where, toAdd/2);
				where.append(wanted);
				pad(where, toAdd - toAdd/2);
				break;
			case JUST_LEFT:
				where.append(wanted);
				pad(where, maxChars - wanted.length());
				break;
			}
		return where;
	}

	protected final void pad(StringBuffer to, int howMany) {
		for (int i=0; i<howMany; i++)
			to.append(' ');
	}

	/** Convenience Routine */
	String format(String s) {
		return format(s, new StringBuffer(), null).toString();
	}

	/** ParseObject is required, but not useful here. */
	public Object parseObject (String source, ParsePosition pos)  {
		return source;
	}

}
