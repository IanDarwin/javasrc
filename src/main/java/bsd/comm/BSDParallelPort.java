package bsd.comm;

import javax.comm.*;

import java.io.*;

public class BSDParallelPort extends ParallelPort {

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

	/** Construct a Port object with a named port. */
    public BSDParallelPort(String sport) {
		System.out.println("BSDParallelPort.<init>");
		if (!closed)
			throw new IllegalStateException("Port opened already");
		filename = "/dev/lp";	// TODO lookup in table.
		try {
			instr = new FileInputStream(filename);
			oustr = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.toString());
		}
		closed = false;
	}

	/** Construct a ParallelPort object with no arguments. */
    public BSDParallelPort() {
		this(DEFAULT_PARALLEL_PORT);
	}

	public void close() {
		closed = true;
		super.close();	// REQUIRED for housekeeping
	}

	/** Set the port listener */
	public void addEventListener(ParallelPortEventListener newListener)
	throws java.util.TooManyListenersException {
		if (theListener != null) {
			throw new java.util.TooManyListenersException("Listener in use");
		}
		theListener = newListener;
	}

	public void removeEventListener() {
		theListener = null;
	}

	public void notifyOnError(boolean notify) {
	}

	public void notifyOnBuffer(boolean notify) {
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
		return mode;
	}


	/* (non-Javadoc)
	 * @see javax.comm.CommPort#enableReceiveThreshold(int)
	 */
	public void enableReceiveThreshold(int arg0) throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#disableReceiveThreshold()
	 */
	public void disableReceiveThreshold() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#isReceiveThresholdEnabled()
	 */
	public boolean isReceiveThresholdEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#getReceiveThreshold()
	 */
	public int getReceiveThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#enableReceiveTimeout(int)
	 */
	public void enableReceiveTimeout(int arg0) throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#disableReceiveTimeout()
	 */
	public void disableReceiveTimeout() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#isReceiveTimeoutEnabled()
	 */
	public boolean isReceiveTimeoutEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#getReceiveTimeout()
	 */
	public int getReceiveTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#enableReceiveFraming(int)
	 */
	public void enableReceiveFraming(int arg0) throws UnsupportedCommOperationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#disableReceiveFraming()
	 */
	public void disableReceiveFraming() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#isReceiveFramingEnabled()
	 */
	public boolean isReceiveFramingEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#getReceiveFramingByte()
	 */
	public int getReceiveFramingByte() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#setInputBufferSize(int)
	 */
	public void setInputBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#getInputBufferSize()
	 */
	public int getInputBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#setOutputBufferSize(int)
	 */
	public void setOutputBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.comm.CommPort#getOutputBufferSize()
	 */
	public int getOutputBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
