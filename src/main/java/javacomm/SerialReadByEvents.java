import java.awt.*;
import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * Read from a Serial port, notifying when data arrives.
 * Simulation of part of an event-logging service.
 * @version $Id$
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class SerialReadByEvents extends CommPortOpen 
	implements SerialPortEventListener {

	public static void main(String argv[])
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {

		new SerialReadByEvents(null).converse();
	}

	/* Constructor */
	public SerialReadByEvents(Frame f)
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		
		super(f);
	}

	protected BufferedReader ifile;

	/** 
	 * Hold the conversation. 
	 */
	protected void converse() throws IOException {

		if (!(thePort instanceof SerialPort)) {
			System.err.println("But I wanted a SERIAL port!");
			System.exit(1);
		}
		// Tell the Comm API that we want serial events.
		((SerialPort)thePort).notifyOnDataAvailable(true);
		try {
			((SerialPort)thePort).addEventListener(this);
		} catch (TooManyListenersException ev) {
			// "CantHappen" error
			System.err.println("Too many listeners(!) " + ev);
			System.exit(0);
		}
	
		// Make a reader for the input file.
		ifile = new BufferedReader(new InputStreamReader(is));

		//
	}
	public void serialEvent(SerialPortEvent ev) {
		String line;
		try {
			line = ifile.readLine();
			if (line == null) {
				System.out.println("EOF on serial port.");
				System.exit(0);
			}
			os.println(line);
		} catch (IOException ex) {
			System.err.println("IO Error " + ex);
		}
	}
}
