import java.io.*;

/** Subclass of LineNumberReader to allow continued lines. */
public class BSContLineReader extends java.io.LineNumberReader {
	/** Line number of first line in current (possibly continued) line */
	protected int firstLineNumber = 0;
	/** EOF flag, needed since we use super.readLine() several places */
	protected boolean hitEOF = false;

	/** Read one (possibly continued) line, stripping out the \ that
	 * mark the end of all but the last.
	 */
	public String readLine() throws IOException {
		// Read the first line, save its contents in the StringBuffer
		// and its line number in firstLineNumber.
		String s = super.readLine();
		if (s == null)
			hitEOF = true;
		if (hitEOF)
			return null;
		StringBuffer sb = new StringBuffer(s);
		firstLineNumber = super.getLineNumber();

		// Now read as many continued lines as there are.
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\\') {
			sb.setLength(sb.length() - 1);		// JDK Any - kill '\\'
			// sb.deleteCharAt(sb.length() - 1);// Java 2  - kill '\\'
			String nextPart = super.readLine();
			if (nextPart == null) {
				hitEOF = true;
				return sb.toString();	// Gak! EOF within continued line
			}
			sb.append(' ').append(nextPart);	// and add line.
		}
		return sb.toString();
	}

	/** Override getLineNumber to return the # of the beginning of 
	 * the continued line.
	 */
	public int getLineNumber() {
		return firstLineNumber;
	}

	/** Construct a BSContLineReader with the default input-buffer size. */
	public BSContLineReader(Reader in)  {
		super(in);
	}

	/** Construct a BSContLineReader using the given input-buffer size. */
	public BSContLineReader(Reader in, int sz)  {
		super(in, sz);
	}
}
