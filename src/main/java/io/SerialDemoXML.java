import java.beans.*;
import java.io.*;

/** Show the XML serialization added to "java.beans.*" in JDK1.4.
 * Subclass "SerialDemo" to get most of the infrastructure
 */
public class SerialDemoXML extends SerialDemo {
	public static void main(String[] args) throws IOException {
		new SerialDemoXML().save();
		new SerialDemoXML().dump();
	}

	/** Save the data to disk. */
	public void write(Object theGraph) throws IOException {
		XMLEncoder os = new XMLEncoder(
			new BufferedOutputStream(
				new FileOutputStream(FILENAME)));
		os.writeObject(theGraph);
		os.close();
	}

	/** Display the data */
	public void dump() throws IOException {
		XMLDecoder is = new XMLDecoder(
			new BufferedInputStream(
				new FileInputStream(FILENAME)));
		System.out.println(is.writeObject());
		is.close();
	}
}
