import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * A quick demo of using the Java Communications Package to open a serial port.
 * This program tries to do I/O in each direction using a separate Thread.
 *
 * Java Communications is a "standard extention" and must be downloaded
 * and installed separately from the JDK before you can even compile this 
 * program.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortIO {
	SerialPort ttya;

	public static void main(String ap[]) {
		CommPortIO cp;
		try {
			cp = new CommPortIO();
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
		ttya = (SerialPort) port.open("JavaJoe", 1000);
	}

	private void traffic() 
		throws IOException,UnsupportedCommOperationException {

		String resp;		// the modem response.

		// set up the serial port
		ttya.setSerialPortParams(9600, SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		// Get the input and output streams
		DataInputStream is = new DataInputStream(ttya.getInputStream());
		PrintStream os = new PrintStream(ttya.getOutputStream());

		new DataThread(is, System.out).start();
		new DataThread(new DataInputStream(System.in), os).start();

	}

	class DataThread extends Thread {
		DataInputStream inStream;
		PrintStream pStream;
		DataThread(DataInputStream is, PrintStream os) {
			inStream = is;
			pStream = os;
		}
		public void run() {
			byte ch = 0;
			try {
				while ((ch = (byte)inStream.read()) != -1)
					pStream.print(ch);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
