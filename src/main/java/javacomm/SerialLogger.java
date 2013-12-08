package javacomm;

import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * Read from multiple Serial ports, notifying when data arrives on any.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class SerialLogger {

	public static void main(String[] argv)
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {

		new SerialLogger();
	}

	/* Constructor */
	public SerialLogger()
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list, processing only serial ports.
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			String name = cpi.getName();
			System.out.print("Port " + name + " ");
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("is a Serial Port: " + cpi);

				SerialPort thePort;
				try {
					thePort = (SerialPort)cpi.open("Logger", 1000);
				} catch (PortInUseException ev) {
					System.err.println("Port in use: " + name);
					continue;
				}

				// Tell the Comm API that we want serial events.
				thePort.notifyOnDataAvailable(true);
				try {
					thePort.addEventListener(new Logger(cpi.getName(), thePort));
				} catch (TooManyListenersException ev) {
					// "CantHappen" error
					System.err.println("Too many listeners(!) " + ev);
					System.exit(0);
				}
			}
		}
	}

	/** Handle one port. */
	public class Logger implements SerialPortEventListener { 
		String portName;
		SerialPort thePort;
		BufferedReader ifile;
		public Logger(String name, SerialPort port) throws IOException {
			portName = name;
			thePort = port;
			// Make a reader for the input file.
			ifile = new BufferedReader(
				new InputStreamReader(thePort.getInputStream()));
		}
		public void serialEvent(SerialPortEvent ev) {
			String line;
			try {
				line = ifile.readLine();
				if (line == null) {
					System.out.println("EOF on serial port.");
					System.exit(0);
				}
				System.out.println(portName + ": " + line);
			} catch (IOException ex) {
				System.err.println("IO Error " + ex);
			}
		}
	}
}
