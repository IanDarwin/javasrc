package io;

import java.io.*;

// BEGIN main
/**
 * Subclass of LineNumberReader, parent of others, to allow reading of
 * continued lines using the readLine() method. The other Reader methods
 * (readInt()) etc.) must not be used. Must subclass to provide the actual
 * implementation of readLine().
 */
public abstract class ContLineReader extends LineNumberReader {
	/** Line number of first line in current (possibly continued) line */
	protected int firstLineNumber = 0;
	/** True if handling continuations, false if not; false == "PRE" mode */
	protected boolean doContinue = true;

	/** Set the continuation mode */
	public void setContinuationMode(boolean b) {
		doContinue = b;
	}

	/** Get the continuation mode */
	public boolean getContinuationMode() {
		return doContinue;
	}

	/** Read one (possibly continued) line, stripping out the \ that
	 * marks the end of each line but the last in a sequence.
	 */
	public abstract String readLine() throws IOException;

	/** Read one real line. Provided as a convenience for the
	 * subclasses, so they don't embarrass themselves trying to
	 * call "super.readLine()" which isn't very practical...
	 */
	public String readPhysicalLine() throws IOException {
		return super.readLine();
	}

	// Can NOT override getLineNumber in this class to return the # 
	// of the beginning of the continued line, since the subclasses
	// all call super.getLineNumber...
	
	/** Construct a ContLineReader with the default input-buffer size. */
	public ContLineReader(Reader in)  {
		super(in);
	}

	/** Construct a ContLineReader using the given input-buffer size. */
	public ContLineReader(Reader in, int sz)  {
		super(in, sz);
	}

	// Methods that do NOT work - redirect straight to parent

	/** Read a single character, returned as an int. */
	public int read() throws IOException {
		return super.read();
	}

	/** Read characters into a portion of an array. */
	public int read(char[] cbuf, int off, int len) throws IOException {
		return super.read(cbuf, off, len);
	}

	public boolean markSupported() {
		return false;
	}
}
// END main
