package javacomm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Enumeration;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

// BEGIN main
/**
 * Open a serial port using Java Communications.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class CommPortSimple {
	private static final String HELLO = "Hello?";
	/** How long to wait for the open to finish up. */
	public static final int TIMEOUTSECONDS = 30;
	/** The baud rate to use. */
	public static final int BAUD = 19200;
	/** The input stream */
	protected BufferedReader is;
	/** The output stream */
	protected PrintStream os;
	/** The chosen Port Identifier */
	CommPortIdentifier thePortID;
	/** The chosen Port itself */
	CommPort thePort;

	public static void main(String[] argv) throws Exception {
		
		if (argv.length != 1) {
			System.err.println("Usage: CommPortSimple deviceName");
			System.exit(1);
		}

		new CommPortSimple(argv[0]).holdConversation();

		System.exit(0);
	}

	/* Constructor */
	public CommPortSimple(String devName) throws Exception {

		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> pList = 
				CommPortIdentifier.getPortIdentifiers();

		// Walk the list, looking for the given name
		CommPortIdentifier cpi = null;
		boolean atLeastOneSerialPresent = false;
		while (pList.hasMoreElements()) {
			CommPortIdentifier c = pList.nextElement();
			if (c.getPortType() !=CommPortIdentifier.PORT_SERIAL) {
				System.err.println("Not a serial port: " + c.getName());
				continue;
			}
			if (devName.equals(c.getName())) {
				cpi = c;
				break; // found!
			}
			atLeastOneSerialPresent = true;
			System.out.println("Not matched: " + c.getName());
		}
		if (cpi == null) {
			System.err.println("Did not find serial port '" + devName + "'");
			if (atLeastOneSerialPresent)
				System.err.println("Try again with one of the listed names");
			else
				System.err.println("In fact, I didn't see ANY serial ports!");
			System.exit(1);
		}

		thePort = cpi.open("JavaCook DataComm",
				TIMEOUTSECONDS * 1000);
		SerialPort myPort = (SerialPort) thePort;

		// set up the serial port
		myPort.setSerialPortParams(BAUD, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		// Get the input and output streams
		is = new BufferedReader(new InputStreamReader(thePort.getInputStream()));
		os = new PrintStream(thePort.getOutputStream(), true);
	}

	/** Hold a conversation - in this case a *very* simple one.  */
	protected void holdConversation() throws IOException {

		System.out.println("Ready to read and write port.");

		os.println(HELLO);
		String response = is.readLine();
		
		System.out.printf("I said %s, and the other end replied %s%n", 
				HELLO, response);

		// Finally, clean up.
		if (is != null)
			is.close();
		if (os != null)
			os.close();
	}
}
// END main
