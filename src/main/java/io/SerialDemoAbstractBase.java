import java.io.*;
import java.util.*;

/** Demonstrate use of Serialization. */
public abstract class SerialDemoAbstractBase {

	protected static final String FILENAME = "serial.dat";

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

