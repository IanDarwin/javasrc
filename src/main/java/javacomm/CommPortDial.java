import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * A quick demo of using the Java Communications Package to open a serial port.
 *
 * Java Communications is a "standard extention" and must be downloaded
 * and installed separately from the JDK before you can even compile this 
 * program.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortProg {
	SerialPort ttya;

	public static void main(String ap[]) {
		CommPortProg cp;
		try {
			cp = new CommPortProg();
			cp.report();
			cp.traffic();
		} catch(Exception e) {
			System.err.println("You blew it! Here's why:\n");
			e.printStackTrace();
		}
	}

	private void report() throws NoSuchPortException,PortInUseException {
		// get list of ports available on this particular computer.
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Print the list. A GUI program would put these in a chooser!
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			System.out.println("Port " + cpi.getName());
		}
		
		// Open a port. For now, hard-code name (ugh).
		String portName = "COM1";	// or "Serial 1" on UNIX
		CommPortIdentifier port =
			CommPortIdentifier.getPortIdentifier(portName);
		// This form of openPort takes an Application Name and a timeout.
		ttya = (SerialPort) port.openPort("JavaJoe", 1000);
	}

	private void traffic() 
		throws IOException,UnsupportedCommOperationException {

		String resp;		// the modem response.

		// set up the serial port
		ttya.setSerialPortParams(38400, SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		// Get the input and output streams
		DataInputStream is = new DataInputStream(ttya.getInputStream());
		PrintStream os = new PrintStream(ttya.getOutputStream());

		// Now you can do whatever reading and writing makes sense
		// based on whatever device is plugged in to the serial port.
		// This section is left as an exercise for the reader...

		// For now, a demo of resetting a Hayes-style modem.

		// Send the reset command
		os.println("ATZ");

		// Read the echo of it.
		resp = is.readLine();
		System.out.println("Modem echoed " + resp);

		// an extra blank line - is it a CRLF issue?
		resp = is.readLine();
		System.out.println("Modem replied " + resp);

		// This will get an OK prompt if there's a modem there:
		resp = is.readLine();
		System.out.println("Modem replied " + resp);

		is.close();
		os.close();
	}
}
