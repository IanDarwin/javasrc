package javacomm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.ParallelPort;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JFrame;

/**
 * Open a serial port using Java Communications.
 *
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class CommPortOpen {
	/** How long to wait for the open to finish up. */
	public static final int TIMEOUTSECONDS = 30;
	/** The baud rate to use. */
	public static final int BAUD = 19200;
	/** The parent JFrame, for the chooser. */
	protected JFrame parent;
	/** The input stream */
	protected BufferedReader is;
	/** The output stream */
	protected PrintStream os;
	/** The chosen Port Identifier */
	CommPortIdentifier thePortID;
	/** The chosen Port itself */
	CommPort thePort;

	public static void main(String[] argv)
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {

		new CommPortOpen(null).converse();

		System.exit(0);
	}

	/* Constructor */
	public CommPortOpen(JFrame f)
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		
		// Use the PortChooser from before. Pop up the JDialog.
		PortChooser chooser = new PortChooser(null);

		String portName = null;
		do {
			chooser.setVisible(true);
			
			// Dialog done. Get the port name.
			portName = chooser.getSelectedName();

			if (portName == null)
				System.out.println("No port selected. Try again.\n");
		} while (portName == null);

		// Get the CommPortIdentifier.
		thePortID = chooser.getSelectedIdentifier();

		// Now actually open the port.
		// This form of openPort takes an Application Name and a timeout.
		// 
		System.out.println("Trying to open " + thePortID.getName() + "...");

		switch (thePortID.getPortType()) {
		case CommPortIdentifier.PORT_SERIAL:
			thePort = thePortID.open("DarwinSys DataComm",
				TIMEOUTSECONDS * 1000);
			SerialPort myPort = (SerialPort) thePort;

			// set up the serial port
			myPort.setSerialPortParams(BAUD, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			break;

		case CommPortIdentifier.PORT_PARALLEL:
			thePort = thePortID.open("DarwinSys Printing",
				TIMEOUTSECONDS * 1000);
			ParallelPort pPort = (ParallelPort)thePort;

			// Tell API to pick "best available mode" - can fail!
			// myPort.setMode(ParallelPort.LPT_MODE_ANY);

			// Print what the mode is
			int mode = pPort.getMode();
			switch (mode) {
				case ParallelPort.LPT_MODE_ECP:
					System.out.println("Mode is: ECP");
					break;
				case ParallelPort.LPT_MODE_EPP:
					System.out.println("Mode is: EPP");
					break;
				case ParallelPort.LPT_MODE_NIBBLE:
					System.out.println("Mode is: Nibble Mode.");
					break;
				case ParallelPort.LPT_MODE_PS2:
					System.out.println("Mode is: Byte mode.");
					break;
				case ParallelPort.LPT_MODE_SPP:
					System.out.println("Mode is: Compatibility mode.");
					break;
				// ParallelPort.LPT_MODE_ANY is a "set only" mode;
				// tells the API to pick "best mode"; will report the
				// actual mode it selected.
				default:
					throw new IllegalStateException("Parallel mode " + 
						mode + " invalid.");
			}
			break;
		default:	// Neither parallel nor serial??
			throw new IllegalStateException("Unknown port type " + thePortID);
		}

		// Get the input and output streams
		// Printers can be write-only
		try {
			is = new BufferedReader(new InputStreamReader(thePort.getInputStream()));
		} catch (IOException e) {
			System.err.println("Can't open input stream: write-only");
			is = null;
		}
		os = new PrintStream(thePort.getOutputStream(), true);
	}

	/** This method will be overridden by non-trivial subclasses
	 * to hold a conversation. 
	 */
	protected void converse() throws IOException {

		System.out.println("Ready to read and write port.");

		// Input/Output code not written -- must subclass.

		// Finally, clean up.
		if (is != null)
			is.close();
		os.close();
	}
}
