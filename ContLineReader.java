/** Subclass of LineNumberReader to allow continued lines. */
public class ContLineReader extends java.io.LineNumberReader {
	/** Line number of first line in current (possibly continued) line */
	protected int firstLineNumber = 0;
	/** EOF flag, needed since we use super.readLine() several places */
	protected boolean hitEOF = false;

	/** Construct a ContLineReader with the default input-buffer size. */
	public ContLineReader(java.io.Reader in)  {
		super(in);
	}

	/** Construct a BSContLineReader using the given input-buffer size. */
	public ContLineReader(java.io.Reader in, int sz)  {
		super(in, sz);
	}
}
