package javacomm;

import java.io.IOException;
import java.util.TooManyListenersException;

import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.swing.JFrame;

/**
 * Read from a Serial port, notifying when data arrives.
 * Simulation of part of an event-logging service.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class SerialReadByEvents extends CommPortOpen 
	implements SerialPortEventListener {

	public static void main(String[] argv) throws Exception {

		new SerialReadByEvents(null).converse();
	}

	/* Constructor */
	public SerialReadByEvents(JFrame f) throws Exception {
		
		super(f);
	}

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
	
		
	}
	public void serialEvent(SerialPortEvent ev) {
		switch (ev.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			String line;
			try {
				line = is.readLine();
				if (line == null) {
					System.out.println("EOF on serial port.");
					System.exit(0);
				}
				os.println(line);
			} catch (IOException ex) {
				System.err.println("IO Error " + ex);
			}
			break;
		default:
			System.out.println("Event type " + ev.getEventType());
		}
	}
}
