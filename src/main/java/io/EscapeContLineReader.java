import java.io.*;

/** Subclass LineNumberReader to allow continued lines. */
public class LineReader extends LineNumberReader {
	/** Line number of first line in current (possibly continued) line */
	protected int firstLineNumber = 0;

	/** Read one (possibly continued) line, stripping out the \ that
	 * mark the end of all but the last.
	 */
	public String readLine() throws IOException {
		// Read the first line, save its contents in the StringBuffer
		// and its line number in firstLineNumber.
		StringBuffer sb = new StringBuffer(super.readLine());
		firstLineNumber = getLineNumber();

		// Now read as many continued lines as there are.
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\\') {
			sb.deleteCharAt(sb.length() - 1);
			sb.append(super.readLine());
		}
		return sb.toString();
	}

	/** Override getLineNumber to return the # of the beginning of 
	 * the continued line.
	 */
	public int getLineNumber() {
		return firstLineNumber;
	}

	/** Construct a LineReader with the default input-buffer size. */
	public LineReader(Reader in)  {
		super(in);
	}

	/** Construct a LineReader using the given input-buffer size. */
	public LineReader(Reader in, int sz)  {
		super(in, sz);
	}
}
