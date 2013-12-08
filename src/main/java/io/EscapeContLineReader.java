package io;

import java.io.*;

/** Subclass of LineNumberReader to allow reading of lines continued
 * with an escape character.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class EscapeContLineReader extends ContLineReader {
	/** The default escape character. */
	public static final char ESCAPE = '\\';
	/** The actual escape character. */
	protected char escape = ESCAPE;
	/** EOF flag, needed since we use super.readLine() several places */
	protected boolean hitEOF = false;

	/** Line number of first line in current (possibly continued) line */
	public int getLineNumber() {
		return firstLineNumber;
	}

	/** Read one (possibly continued) line, stripping out the \ that
	 * marks the end of each line but the last in a sequence.
	 */
	public String readLine() throws IOException {
		// Read the first line, save its contents in the StringBuffer
		// and its line number in firstLineNumber.
		String s = readPhysicalLine();
		if (s == null)
			hitEOF = true;
		if (hitEOF)
			return null;
		StringBuffer sb = new StringBuffer(s);
		firstLineNumber = super.getLineNumber();

		// Now read as many continued lines as there are.
		while (sb.length() > 0 && 
			sb.charAt(sb.length() - 1) == escape) {
			sb.setLength(sb.length() - 1); 	// Kill the escape
			// sb.deleteCharAt(sb.length() - 1);// Java 2 - kill it
			String nextPart = readPhysicalLine();
			if (nextPart == null) {
				hitEOF = true;
				throw new IOException(
					"EOF within continued line at line " +
					firstLineNumber);
			}
			sb.append(' ').append(nextPart);	// and add line.
		}
		return sb.toString();
	}

	/** Construct a EscapeContLineReader with the default input-buffer size. */
	public EscapeContLineReader(Reader in)  {
		super(in);
	}

	/** Construct a EscapeContLineReader using the given input-buffer size. */
	public EscapeContLineReader(Reader in, int sz)  {
		super(in, sz);
	}

	/** Construct an EscapeContLineReader given a Reader and a
	 * non-default escape character.
	 */
	public EscapeContLineReader(Reader in, char esc)  {
		super(in);
		escape = esc;
	}
}
