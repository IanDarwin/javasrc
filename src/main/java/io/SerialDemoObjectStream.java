package io;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** Demonstrate use of standard Object Serialization. */
// BEGIN main
public class SerialDemoObjectStream extends SerialDemoAbstractBase {
	protected static final String FILENAME = "serial.dat";

	public static void main(String[] s) throws Exception {
		new SerialDemoObjectStream().save();	// in parent class; calls write
		new SerialDemoObjectStream().dump();	// here
	}

	/** Does the actual serialization */
	public void write(Object theGraph) throws IOException {
		// Save the data to disk.
		ObjectOutputStream os = new ObjectOutputStream(
			new BufferedOutputStream(
				new FileOutputStream(FILENAME)));
		os.writeObject(theGraph);
		os.close();
	}

	public void dump() throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(
			new FileInputStream(FILENAME));
		System.out.println(is.readObject());
		is.close();
	}
}
// END main
