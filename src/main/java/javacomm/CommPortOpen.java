import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * Open a serial port using Java Communications.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortOpen {
	/** How long to wait for the open to finish up. */
	public static final int TIMEOUTSECONDS = 30;
	/** The baud rate to use. */
	public static final int BAUD = 9600;

	public static void main(String ap[])
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
		CommPortIdentifier port = chooser.getSelectedIdentifier();

		// Now actually open the port.
		// This form of openPort takes an Application Name and a timeout.
		// Program will bomb here if you picked a Parallel port!
		SerialPort thePort = (SerialPort) port.open("DarwinSys DataComm",
			TIMEOUTSECONDS * 1000);

		// set up the serial port
		thePort.setSerialPortParams(BAUD, SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		// Get the input and output streams
		DataInputStream is = new DataInputStream(thePort.getInputStream());
		PrintStream os = new PrintStream(thePort.getOutputStream());

		// Input/Output code not shown.
		System.out.println("Ready to read and write port " +
			portName + " using is and os...");

		try {
			Thread.sleep(TIMEOUTSECONDS * 2 * 1000);
		} catch (InterruptedException canthappen) {
		}

		// Finally, clean up.
		is.close();
		os.close();
		
		System.exit(0);
	}
}
