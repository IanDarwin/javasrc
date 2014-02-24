// BEGIN main
package io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Demonstrate use of Serialization. Typical Subclass main will be:
 *	public static void main(String[] s) throws Exception {
 *		new SerialDemoXXX().save();		// in parent class; calls write
 *		new SerialDemoXXX().dump();
 *	}
 */
public abstract class SerialDemoAbstractBase {

	/** The save method in an application */
	public void save() throws IOException {
		List<MyData> l = new ArrayList<>();
		// Gather the data
		l.add(new MyData("Ian Darwin", "secret_java_cook"));
		l.add(new MyData("Abby Brant", "dujordian"));
		write(l);
	}

	/** Does the actual serialization */
	public abstract void write(Object theGraph) throws IOException;

	/** Reads the file and displays it. */
	public abstract void dump() throws IOException, ClassNotFoundException;
}
// END main
