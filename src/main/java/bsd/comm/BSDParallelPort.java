public class BSDParallelPort extends javax.comm.ParallelCommPort {
	/** The name of the default port */
	public static final String DEFAULT_PARALLEL_PORT = "lp";

	/** The current port listener, or null */
	protected ParallelPortEventListener theListener = null;

	/** The LPT mode. */
	protected int mode = 0;

	/** True if the port is currently closed. Initially true! */
	protected boolean closed = true;

	/* Native method to open the named device special file */
	protected native int bsdlpopen(String fn);
	/* Native method to set the tty parameters */
	protected native int bsdsetmode(int mode);
	
	/** An InputStream object opened from the device */
	private InputStream instr = null;

	public InputStream getInputStream() {
		if (closed)
			throw new IllegalStateException("Port Closed");
		return instr;
	}

	/** An OutputStream object opened to the device */
	private OutputStream oustr = null;

	public OutputStream getOutputStream() {
		if (closed)
			throw new IllegalStateException("Port Closed");
		return oustr;
	}

	/** The filename of the device special file for this port */
	protected String filename;

	/** Construct a SerialPort object with a named port. */
    public BSDParallelPort(String sport) {
		System.out.println("BSDParallelPort.<init>");
		if (!closed)
			throw new IllegalStateException("Port opened already");
		filename = "/dev/ttya";	// TODO lookup in table.
		try {
			instr = new FileInputStream(filename);
			oustr = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.toString());
		}
		closed = false;
	}

	/** Construct a ParallelPort object with no arguments. */
    public BSDSerialPort() {
		this(DEFAULT_PARALLEL_PORT);
	}

	public void closePort() {
		closed = true;
		super.closePort();	// REQUIRED for housekeeping
	}

	/** Set the port listener */
	public void addEventListener(ParallelPortEventListener newListener)
	throws java.util.TooManyListenersException {
		if (theListener != null) {
			throw new java.util.TooManyListenersException("Listener in use");
		}
		theListener = newListener
	}

	public void removeEventListener() {
		theListener = null;
	}

	public void notifyOnError(boolean) {
	}

	public void notifyOnBuffer(boolean) {
	}

	public int getOutputBufferFree() {
		return 0;
	}

	public boolean isPaperOut() {
		return false;
	}

	public boolean isPrinterBusy() {
		return true;
	}

	public boolean isPrinterSelected() {
		return false;
	}

	public boolean isPrinterTimedOut() {
		return true;
	}

	public boolean isPrinterError() {
		return true;
	}

	public void restart() {
	}

	public void suspend() {
	}

	public int getMode() {
		return mode;
	}

	public int setMode(int nMode) throws UnsupportedCommOperationException {
		switch(nMode) {
			case LPT_MODE_ANY:
			case LPT_MODE_SPP:
			case LPT_MODE_PS2:
			case LPT_MODE_EPP:
			case LPT_MODE_ECP:
			case LPT_MODE_NIBBLE:
				mode = nMode;
				break;
			default:
				throw new UnsupportedCommOperationException();
		}
	}
}
