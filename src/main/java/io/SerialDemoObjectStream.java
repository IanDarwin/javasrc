import java.io.*;
import java.util.*;

/** Demonstrate use of standard Object Serialization. */
public class SerialDemoObjectStream extends SerialDemoAbstractBase {
	protected static final String FILENAME = "serial.dat";

	public static void main(String[] s) throws Exception {
		new SerialDemo().save();		// in parent class; calls write
		new SerialDemo().dump();
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

