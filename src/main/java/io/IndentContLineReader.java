import java.io.*;

/** Subclass of ContLineReader for lines continued by indentation of
 * following line (like RFC822 mail, Usnetnet News, etc.).
 */
public class IndentContLineReader extends ContLineReader {

	protected String prevLine;

	/** Read one (possibly continued) line, stripping out the \ that
	 * mark the end of all but the last.
	 */
	public String readLine() throws IOException {
		String s;

		// If we saved a previous line, start with it. Else,
		// read the first line of possible continuation. 
		// If non-null, put it into the StringBuffer and its line number 
		// in firstLineNumber.
		if (prevLine != null) {
			s = prevLine;
			prevLine = null;
		}
		else 
			s = super.readLine();
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer(s);
		firstLineNumber = super.getLineNumber();

		// Now read as many continued lines as there are, if any.
		while (true) {
			String nextPart = super.readLine();
			if (nextPart == null) {
				return sb.toString();	// Gak! EOF within continued line
										// return what we have so far.
			}
			// If the next line begins with space, it's continuation
			if (nextPart.length() > 0 &&
					Character.isWhitespace(nextPart.charAt(0))) {
				sb.append(nextPart);	// and add line.
			} else {
				// else we just read too far, so put in "pushback" holder
				prevLine = nextPart;
				break;
			}
		}

		return sb.toString();		// return what's left
	}

	/** Override getLineNumber to return the # of the beginning of 
	 * the continued line.
	 */
	public int getLineNumber() {
		return firstLineNumber;
	}

	/** Construct a IndentContLineReader with the default input-buffer size. */
	public IndentContLineReader(Reader in)  {
		super(in);
	}

	/** Construct a IndentContLineReader using the given input-buffer size. */
	public IndentContLineReader(Reader in, int sz)  {
		super(in, sz);
	}
}
