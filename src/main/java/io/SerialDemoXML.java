package io;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/** 
 * Show the XML serialization methods in "java.beans.*" 
 * Note that only properties with public get AND set methods get serialized.
 * Subclass "SerialDemoAbstratBase" to get most of demo infrastructure
 */
// BEGIN main
public class SerialDemoXML extends SerialDemoAbstractBase {

	public static final String FILENAME = "serial.xml";

	public static void main(String[] args) throws IOException {
		new SerialDemoXML().save();
		new SerialDemoXML().dump();
	}

	/** Save the data to disk. */
	public void write(Object theGraph) throws IOException {
		XMLEncoder os = new XMLEncoder(
				new FileOutputStream(FILENAME));
		os.writeObject(theGraph);
		os.close();
	}

	/** Display the data */
	public void dump() throws IOException {
		XMLDecoder inp = new XMLDecoder(
				new FileInputStream(FILENAME));
		System.out.println(inp.readObject());
		inp.close();
	}
}
// END main
