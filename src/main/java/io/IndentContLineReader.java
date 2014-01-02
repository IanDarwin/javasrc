package io;

import java.io.*;

/** Subclass of ContLineReader for lines continued by indentation of
 * following line (like RFC822 mail, Usenet News, etc.).
 * Normally you would read header & body of the message(s) with code like:
 * <pre>
 * while ((headerLine = clr.readLine()) != null && headerLine.length() > 0) {
 *	processHeaderLine(headerLine);
 * }
 * clr.setContinuationMode(false);
 * while ((bodyLine = clr.readLine()) != null) {
 *	processBodyLine(bodyLine);
 * }
 * </pre>
 */
// BEGIN main
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
// END main

	/** Construct an IndentContLineReader with the default buffer size. */
	public IndentContLineReader(Reader in)  {
		super(in);
	}

	/** Construct an IndentContLineReader using the given buffer size. */
	public IndentContLineReader(Reader in, int sz)  {
		super(in, sz);
	}
}
