package javacomm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

/**
 * JMModel -- Communications I/O for JModem. No GUI stuff here.
 * @author	 Ian F. Darwin, http://www.darwinsys.com/
 */
public class JMModel {
	/** The View */
	JModem theGUI;

	/** The javax.com.CommPort object in use */
	private SerialPort thePort;

	/** The input and output streams */
	private InputStream serialInput;
	private OutputStream serialOutput;

	/** The size of the static read buffer. */
	protected static final int BUFSIZE = 1024;
	/** A buffer for the read listener; preallocated once. */
	static byte[] buf = new byte[BUFSIZE];
	/** A Thread for reading from the remote. */
	protected Thread serialReadThread;
	/** A file transfer program */
	protected TModem xferProg;
	/** The state for disconnected and connected */
	static int S_DISCONNECTED = 0, S_CONNECTED = 1;
	/** The state, either disconnected or connected */
	int state = S_DISCONNECTED;
	/** The substate settings */
	static int S_INTERACT = 0, S_XFER = 1;
	/** The online state, either interactive or in xfer. Used by the
	 * main reader thread to avoid reading data meant for the xfer program.
	 */
	int submode = S_INTERACT;

	// Constants to hide the Comm API from our GUI.
	public final static int PARITY_NONE = SerialPort.PARITY_NONE;
	public final static int PARITY_EVEN = SerialPort.PARITY_EVEN;
	public final static int PARITY_ODD	= SerialPort.PARITY_ODD;

	private int[] baudot = { 9600, 19200, 38400, 57600, 115200 };
	protected HashMap portsIDmap = new HashMap();

	/** Constructor */
	public JMModel(JModem gui) {
		theGUI = gui;
	}

	protected String DEFAULT_LOG_FILE = "jmodemlog.txt";

	private boolean done = false;

	/** Use normal java.io to save the JTextArea's session log
	 * into a file.
	 */	
	public void saveLogFile() {
		String fileName = DEFAULT_LOG_FILE;
		try {
			Writer w = new FileWriter(fileName);
			theGUI.theTextArea.write(w);
			w.write('\r'); w.write('\n');	// in case last line is a prompt.
			w.close();
		} catch (IOException e) {
			theGUI.err("Error saving log file:\n" + e.toString());
			return;
		}
		theGUI.note("Session log saved to " + fileName);
	}

	/** Load the list of Serial Ports into the chooser.
	 * XXX This code is far too chummy with the innards of class JModem.
	 */
	void populateComboBox() {
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list of ports, putting serial ports into ComboBox
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				theGUI.portsComboBox.addItem(cpi.getName());
				portsIDmap.put(cpi.getName(), cpi);
			}
		}
	}

	/** Connect to the chosen serial port, and set parameters. */
	void connect() {

		try {
			// Open the specified serial port
			CommPortIdentifier cpi = (CommPortIdentifier)portsIDmap.get(
			theGUI.portsComboBox.getSelectedItem());
			thePort = (SerialPort)cpi.open("JModem", 15*1000);

			// Set the serial port parameters.
			thePort.setSerialPortParams(
				baudot[theGUI.baudComboBox.getSelectedIndex()],		// baud
				theGUI.getDataBits() == 7 ?
				SerialPort.DATABITS_7 : SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,							// stop bits
				theGUI.getParity());							// parity

			thePort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN &
				SerialPort.FLOWCONTROL_RTSCTS_OUT);

		} catch (PortInUseException pue) {
			theGUI.err("Port in use: close other app, or use different port.");
			return;
		} catch (UnsupportedCommOperationException uoe) {
			theGUI.err("Unsupported options error: try different settings");
			return;
		}

		// Similar to "raw" mode: return when 1 or more chars available.
		try {
			thePort.enableReceiveThreshold(1);
			if (!thePort.isReceiveThresholdEnabled()) {
				theGUI.err("Could not set receive threshold");
				disconnect();
				return;
			}
			thePort.setInputBufferSize(buf.length);
		} catch (UnsupportedCommOperationException ev) {
			theGUI.err("Unable to set receive threshold in Comm API; port unusable.");
			disconnect();
			return;
		}

		// Get the streams
		try {
			serialInput = thePort.getInputStream();
		} catch (IOException e) {
			theGUI.err("Error getting input stream:\n" + e.toString());
			return;
		}
		try {
			serialOutput = thePort.getOutputStream();
		} catch (IOException e) {
			theGUI.err("Error getting output stream:\n" + e.toString());
			return;
		}

		// Now that we're all set, create a Thread to read data from the remote
		serialReadThread = new Thread(new Runnable() {
		int nbytes = buf.length;
			public void run() {
				do {
					if (done)
						return;
					try {
						// If the xfer program is running, stay out of its way.
							if (submode == S_XFER) {
								delay(1000);
							continue;
						}
						nbytes = serialInput.read(buf, 0, buf.length);
					} catch (IOException ev) {
						theGUI.err("Error reading from remote:\n" + ev.toString());
						return;
					}
					// XXX need an appendChar() method in MyTextArea
					String tmp = new String(buf, 0, nbytes);
					theGUI.theTextArea.append(tmp);
					theGUI.theTextArea.setCaretPosition(
							theGUI.theTextArea.getText().length());
				} while (serialInput != null);
			}
		});
		serialReadThread.start();

		// Finally, tell rest of program, and user, that we're online.
		state = S_CONNECTED;
		theGUI.connect();
	}

	/** Break our connection to the serial port. */
	void disconnect() {
		done = true;
		// Tell java.io we are done with the input and output
		try {			
			serialInput.close();
			serialOutput.close();
			serialOutput = null;
		} catch (IOException e) {
			theGUI.err("IO Exception closing port:\n" + e.toString());
		}
		// Tell javax.comm we are done with the port.
		thePort.removeEventListener();
		thePort.close();
		// Discard TModem object, if present.
		xferProg = null;
		// Tell rest of program we are no longer online.
		state = S_DISCONNECTED;
		theGUI.disconnect();
	}

	/** Convenience routine, due to useless InterruptedException */
	public void delay(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	/** Send one character to the remote */
	void sendChar(char ch) {
		if (state != S_CONNECTED)
			return;
		// System.err.println("--> " + ch);
		try {
			serialOutput.write(ch);
		} catch (IOException e) {
			theGUI.err("Output error on remote:\n" + e.toString() +
				"\nClosing connection.");
			disconnect();
		}
	}

	/** Send a String of characters to the remote. */
	private void sendString(String s) {
		if (state != S_CONNECTED)
			return;
		try {
			serialOutput.write(s.getBytes());
		} catch (IOException e) {
			theGUI.err("Output error on remote:\n" + e.toString() +
				"\nClosing connection.");
			disconnect();
		}
	}

	/** Do one complete file transfer, using TModem */
	public void xfer() {

		if (state != S_CONNECTED) {
			theGUI.err("Must be connected to do file transfers");
			return;
		}
		if (xferProg == null) {
			xferProg = new TModem(serialInput, serialOutput, 
				new PrintWriter(System.err));
		}
		String fileName = theGUI.getXferFileName();
		if (fileName.length() == 0) {
			theGUI.err("Filename must be given");
			return;
		}

		// Do the transfer!	If we are sending, send a "tmodem -r" to
		// the other side; if receiving, send "tmodem -s" to ask it
		// to send the file.
		try {
			if (theGUI.isSend()) {
				if (!new File(fileName).canRead()) {
						theGUI.err("Can't read file " + fileName + ".");
					return;
				}
				// Other end must "r"eceive what we send.
				sendString("tmodem -r " + fileName + "\r\n");
				delay(500);		// let command echo back to us
				submode = S_XFER;
				xferProg.send(fileName);
			} else {
				// Other end must send for us to receive.
				sendString("tmodem -s " + fileName + "\r\n");
				delay(500);		// let command echo back to us
				submode = S_XFER;
				xferProg.receive(fileName);
			}
		} catch (InterruptedException e) {
			theGUI.err("Timeout");
			return;
		} catch (IOException e) {
			theGUI.err("IO Exception in transfer:\n" + e);
			return;
		} catch (ProtocolBotchException ev) {
			theGUI.err("Protocol failure:\n" + ev);
			return;
		} finally {
			submode = S_INTERACT;
		}
		theGUI.note("File Transfer completed");
	}
}
