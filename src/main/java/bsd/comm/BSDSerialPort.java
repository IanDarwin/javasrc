package bsd.comm;

import java.io.*;
import javax.comm.*;

/** BSDSerialPort, a Java Communications API SerialPort object.
 * Written to the javadoc specification, with some input from Sun's
 * template "NullSerialPort" class. No Sun-licensed or GPL'd code was
 * incorporated into this software.
 */
public class BSDSerialPort extends javax.comm.SerialPort {

	/** True if the port is currently closed. Initially true! */
	protected boolean closed = true;

	/* Native method to open the named device special file */
	protected native int bsdttyopen(String fn);
	/* Native method to set the tty parameters */
	protected native int bsdstty(int b, int d, int s, int p);
	
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
    public BSDSerialPort(String sport) {
		System.out.println("BSDSerialPort.<init>");
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

	/** Construct a SerialPort object with no arguments. */
    public BSDSerialPort() {
		this("ttya");		// TODO named default
	}

	public void closePort() {
		closed = true;
		super.closePort();	// REQUIRED for housekeeping
	}

	protected int baudRate = 19200;
	protected int dataBits = DATABITS_8;
	protected int stopBits = STOPBITS_1;
	protected int parity   = PARITY_NONE;

    public int getBaudrate() {
		if (closed)
			throw new IllegalStateException("Port Closed");
		return baudRate;
	}

    public int getDataBits() {
		if (closed)
			throw new IllegalStateException("Port Closed");
		return dataBits;
	}

    public int getStopBits() {
		if (closed)
			throw new IllegalStateException("Port Closed");
		return stopBits;
	}

    public int getParity() {
		if (closed)
			throw new IllegalStateException("Port Closed");
		return parity;
	}

	/** Convenience routine: sets baud, databits, stopbits, and parity */
    public void setSerialPortParams(int b, int d, int s, int p)
		throws UnsupportedCommOperationException {
		if (!closed)
			throw new IllegalStateException("Port Closed");
		bsdstty(b, d, s, p);
	}
	/** Consolidated version of setSerialPortParams, for internal use only */
    protected void setSerialPortParams() {
		bsdstty(baudRate, dataBits, stopBits, parity);
	}

    public void sendBreak(int n) {
		if (closed)
			throw new IllegalStateException("Port Closed");
	}
    public void setFlowcontrolMode(int m) {
		if (closed)
			throw new IllegalStateException("Port Closed");
	}
    public int getFlowcontrolMode() {
		return 0;
	}
    public void setDTR(boolean doDTR) {
	}
    public boolean isDTR() {
		return false;
	}
    public void setRTS(boolean doRTS) {
	}
    public boolean isRTS() {
		return false;
	}
    public boolean isCTS() {
		return false;
	}
    public boolean isDSR() {
		return false;
	}
    public boolean isRI() {
		return false;
	}
    public boolean isCD() {
		return false;
	}

    public void notifyOnDataAvailable(boolean doIt) {
	}
    public void notifyOnOutputEmpty(boolean doIt) {
	}
    public void notifyOnCTS(boolean doIt) {
	}
    public void notifyOnDSR(boolean doIt) {
	}
    public void notifyOnRingIndicator(boolean doIt) {
	}
    public void notifyOnCarrierDetect(boolean doIt) {
	}
    public void notifyOnOverrunError(boolean doIt) {
	}
    public void notifyOnParityError(boolean doIt) {
	}
    public void notifyOnFramingError(boolean doIt) {
	}
    // public void notifyOnBreakInterrupt(boolean doIt) {
	// }

	public void addEventListener(javax.comm.SerialPortEventListener x) {
	}

	public void removeEventListener() {
	}

	public boolean isRcvTimeoutEnabled() {
		return false;
	}

	public int getRcvFramingByte() {
		return 0;
	}

	public boolean isRcvThresholdEnabled() {
		return false;
	}

	public void enableRcvTimeout(int to) {
	}

	public int getInputBufferSize() {
		return 0;
	}

	public int getOutputBufferSize() {
		return 0;
	}

	public void enableRcvFraming(int f) {
	}

	public void disableRcvThreshold() {
	}

	public boolean isRcvFramingEnabled() {
		return false;
	}

	public int getRcvThreshold() {
		return 0;
	}

	public void setInputBufferSize(int bufsiz) {
	}

	public void disableRcvTimeout() {
	}

	public int getRcvTimeout() {
		return 0;
	}

	public void disableRcvFraming() {
	}

	public void enableRcvThreshold(int thresh) {
	}

	public void setOutputBufferSize(int bufsiz) {
	}

	public void notifyOnBreakInterrupt(boolean doit) {
	}
}
