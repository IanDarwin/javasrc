import java.io.*;

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
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class TeePrintStream extends PrintStream {
	protected PrintStream parent;
	protected String fileName;

	/** A simple test case. */
	public static void main(String[] args) throws IOException {
		TeePrintStream ts = new TeePrintStream(System.err, "err.log");
		System.setErr(ts);
		System.err.println("An imitation error message");
		ts.close();
	}

	/** Construct a TeePrintStream given an existing PrintStream,
	 * an opened OutputStream, and a boolean to control auto-flush.
	 * This is the main constructor, to which others delegate via "this".
	 */
	public TeePrintStream(PrintStream orig, OutputStream os, boolean flush)
	throws IOException {
		super(os, true);
		fileName = "(opened Stream)";
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
		this(new FileOutputStream(fn), flush);
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
