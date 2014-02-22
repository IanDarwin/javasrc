package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/** TeePrintStream tees all PrintStream operations into a file, rather
 * like the UNIX tee(1) command. It is a PrintStream subclass. The
 * expected usage would be something like the following:
 * <PRE>
 *	...
 *	TeePrintStream ts = new TeePrintStream(System.err, "err.log");
 *	System.setErr(ts);
 *	// ...lots of code that occasionally writes to System.err...
 *	ts.close();
 *	...
 * <PRE>
 * <P>I only override Constructors, the write(), check() and close() methods,
 * since any of the print() or println() methods must go through these.
 * Thanks to Svante Karlsson for help formulating this.
 * <br/>
 * Note: there is another way of doing this, using a FilterStream;
 * see the example at http://www.javaspecialists.eu/archive/Issue003.html
 * (written a year after the initial import of my version).
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TeePrintStream extends PrintStream {
	/** The original/direct print stream */
	protected PrintStream parent;

	/** The filename we are tee-ing too, if known;
	 * intended for use in future error reporting.
	 */
	protected String fileName;

	/** The name for when the input filename is not known */
	private static final String UNKNOWN_NAME = "(opened Stream)";

	/** Construct a TeePrintStream given an existing PrintStream,
	 * an opened OutputStream, and a boolean to control auto-flush.
	 * This is the main constructor, to which others delegate via "this".
	 */
	public TeePrintStream(PrintStream orig, OutputStream os, boolean flush)
	throws IOException {
		super(os, true);
		fileName = UNKNOWN_NAME;
		parent = orig;
	}

	/** Construct a TeePrintStream given an existing PrintStream and
	 * an opened OutputStream.
	 */
	public TeePrintStream(PrintStream orig, OutputStream os)
	throws IOException {
		this(orig, os, true);
	}

	/* Construct a TeePrintStream given an existing Stream and a filename.
	 */
	public TeePrintStream(PrintStream os, String fn) throws IOException {
		this(os, fn, true);
	}

	/* Construct a TeePrintStream given an existing Stream, a filename,
	 * and a boolean to control the flush operation.
	 */
	public TeePrintStream(PrintStream orig, String fn, boolean flush)
	throws IOException {
		this(orig, new FileOutputStream(fn), flush);
		fileName = fn;
	}

	/** Return true if either stream has an error. */
	public boolean checkError() {
		return parent.checkError() || super.checkError();
	}

	/** override write(). This is the actual "tee" operation. */
	public void write(int x) {
		parent.write(x);	// "write once;
		super.write(x);		// write somewhere else."
	}

	/** override write(). This is the actual "tee" operation. */
	public void write(byte[] x, int o, int l) {
		parent.write(x, o, l);	// "write once;
		super.write(x, o, l);	// write somewhere else."
	}

	/** Close both streams. */
	public void close() {
		parent.close();
		super.close();
	}

	/** Flush both streams. */
	public void flush() {
		parent.flush();
		super.flush();
	}
}
// END main
