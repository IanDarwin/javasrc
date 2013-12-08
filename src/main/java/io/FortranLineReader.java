package io;

import java.io.*;

/** Subclass of LineNumberReader to read Fortran-style lines.
 * Fortran statements, as well as I can remember them, are like:
 * NNNNNXDDDDDDDDDDDDDDDDDD
 * C...........................
 * where NNNNN is a 1-5 digit statement number, or spaces
 *	X is a continuation character, which must be in column 6
 *	DDD is executable statement
 *	... is commentary.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class FortranLineReader extends ContLineReader {
	public static final char COMMENT_CHAR = 'C';

	/** Line number of first line in current (possibly continued) line */
	public int getLineNumber() {
		return firstLineNumber;
	}

	/** EOF flag, needed since we use super.readLine() several places */
	protected boolean hitEOF = false;
	/** The statement number portion */
	protected String statementNum;

	/** Read one (possibly continued) line, save its text (columns 6-71)
	 * in the StringBuffer, its statement number in statementNum,
	 * and its line number in firstLineNumber.
	 */
	public String readLine() throws IOException {
		// Read the first line. 
		String s = readPhysicalLine();
		if (s == null) {
			hitEOF = true;
			return null;
		}
		if (s.charAt(0) == COMMENT_CHAR) {
			statementNum = null;
			return s;
		}

		statementNum = s.substring(0,5);
		StringBuffer sb = new StringBuffer(s.substring(6));
		firstLineNumber = super.getLineNumber();

		// Now read as many continued lines as there are.
		while (s.charAt(5) != ' ') {
			s = readPhysicalLine();
			if (s == null) {
				hitEOF = true;
				return sb.toString();	// Gak! EOF within continued line
			}
			// add rest of line.
			sb.append(' ').append(s.substring(6));
		}
		return sb.toString();
	}

	/** Returns true if the current logical line contains a statement # */
	public boolean hasStatementNumber() {
		return	statementNum != null &&
			statementNum.trim().length() > 0;
	}

	/** Return the statement number of the current logical line. */
	public int getStatementNumber() {
		return Integer.parseInt(statementNum.trim());
	}

	/** Construct a FortranLineReader with the default input-buffer size. */
	public FortranLineReader(Reader in)  {
		super(in);
	}

	/** Construct a FortranLineReader using the given input-buffer size. */
	public FortranLineReader(Reader in, int sz)  {
		super(in, sz);
	}

}
