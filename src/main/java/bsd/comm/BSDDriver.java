package bsd.comm;

import javax.comm.*;
import java.io.*;

/** This class implements javax.comm.Driver so that the Java Communications
 * API can be used on BSD UNIX systems.
 */
public class BSDDriver implements CommDriver {

	/**
	 * initialize() will be called by the CommPortIdentifier's 
	 * static initializer and is responsible to:
	 * <OL><LI>Ensure that the correct hardware is present.
	 * <LI>Load the native libraries.
	 * <LI>Register the port names with CommPortIdentifier.addPortName(). 
	 * </OL>
	 */
	public void initialize() {
		System.out.println("BSDDriver.initialize()");
		System.loadLibrary("bsdcommio");

		// Now register port names with CommPortIdentifier.
		// XX This ought to be loaded from a Proprties file.
		CommPortIdentifier.addPortName("Serial 1",
				CommPortIdentifier.PORT_SERIAL, this);
		CommPortIdentifier.addPortName("Serial 2",
				CommPortIdentifier.PORT_SERIAL, this);
		CommPortIdentifier.addPortName("lpr",
				CommPortIdentifier.PORT_PARALLEL, this);
	}

	/**
	 * getCommPort() will be called by CommPortIdentifier from its openPort()
	 * method. portName is a string that was registered earlier (presumably
	 * by our intialize() method!) using CommPortIdentifier.addPortName().
	 * getCommPort() returns an object that extends a Port object, currently
	 * only SerialPort or ParallelPort are supported. 
	 */
	public CommPort getCommPort(String portName, int portType) {
		System.out.println("BSDDriver.getCommPort()");
		CommPort thePort = null;

		try {
			switch (portType) {
				case CommPortIdentifier.PORT_SERIAL:
					thePort = (CommPort) new BSDSerialPort(portName);
				break;
				case CommPortIdentifier.PORT_PARALLEL:
					thePort = (CommPort) new BSDParallelPort(portName);
			}
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.toString());
		}
		return thePort;
	}
}
