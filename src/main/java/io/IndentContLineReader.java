import java.io.*;

/** Subclass of ContLineReader for lines continued by indentation of
 * following line (like RFC822 mail, Usenet News, etc.).
 * Normally you would read header & body of the message(s) with code like:
 * <PRE>
 * while ((headerLine = clr.readLine()) != null && headerLine.length() > 0) {
 *	processHeaderLine(headerLine);
 * }
 * clr.setContinuationMode(false);
 * while ((bodyLine = clr.readLine()) != null) {
 *	processBodyLine(bodyLine);
 * }
 * </PRE>
 */
public class IndentContLineReader extends ContLineReader {
	/** Line number of first line in current (possibly continued) line */
	public int getLineNumber() {
		return firstLineNumber;
	}

	protected String prevLine;

	/** Read one (possibly continued) line, stripping out the '\'s that
	 * mark the end of all but the last.
	 */
	public String readLine() throws IOException {
		String s;

		// If we saved a previous line, start with it. Else,
		// read the first line of possible continuation. 
		// If non-null, put it into the StringBuffer and its line 
		// number in firstLineNumber.
		if (prevLine != null) {
			s = prevLine;
			prevLine = null;
		}
		else  {
			s = readPhysicalLine();
		}

		// save the line number of the first line.
		firstLineNumber = super.getLineNumber();

		// Now we have one line. If we are not in continuation
		// mode, or if a previous readPhysicalLine() returned null,
		// we are finished, so return it.
		if (!doContinue || s == null)
			return s;

		// Otherwise, start building a stringbuffer
		StringBuffer sb = new StringBuffer(s);

		// Read as many continued lines as there are, if any.
		while (true) {
			String nextPart = readPhysicalLine();
			if (nextPart == null) {
				// Egad! EOF within continued line.
				// Return what we have so far.
				return sb.toString();
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

	/** Construct an IndentContLineReader with the default buffer size. */
	public IndentContLineReader(Reader in)  {
		super(in);
	}

	/** Construct an IndentContLineReader using the given buffer size. */
	public IndentContLineReader(Reader in, int sz)  {
		super(in, sz);
	}

	protected static String sampleTxt = 
		"From: ian today now\n" +
		"Received: by foo.bar.com\n" +
		"	at 12:34:56 January 1, 2000\n" +
		"X-Silly-Headers: Too Many\n" +
		"This line should be line 5.\n" +
		"Test more indented line continues from line 6:\n" +
		"    space indented.\n" +
		"	tab indented;\n" +
		"\n" +
		"This is line 10\n" + 
		"the start of a hypothetical mail/news message, \n" +
		"that is, it follows a null line.\n" +
		"	Let us see how it fares if indented.\n" +
		" also space-indented.\n" +
		"\n" +
		"How about text ending without a newline?";

	public static void main(String argv[]) throws IOException {
		IndentContLineReader is = new IndentContLineReader(
			new StringReader(sampleTxt));
		String aLine;
		// Print Mail/News Header
		System.out.println("----- Message Header -----");
		while ((aLine = is.readLine()) != null && aLine.length() > 0) {
			System.out.println(is.getLineNumber() + ": " + aLine);
		}
		// Make "is" behave like normal BufferedReader
		is.setContinuationMode(false);
		System.out.println();
		// Print Message Body
		System.out.println("----- Message Body -----");
		while ((aLine = is.readLine()) != null) {
			System.out.println(is.getLineNumber() + ": " + aLine);
		}
		is.close();
	}
}
