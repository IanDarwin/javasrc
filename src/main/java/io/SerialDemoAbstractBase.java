import java.io.*;
import java.util.*;

/** Demonstrate use of Serialization. Typical Subclass main will be:
 *	public static void main(String[] s) throws Exception {
 *		new SerialDemoXXX().save();		// in parent class; calls write
 *		new SerialDemoXXX().dump();
 *	}
 */
public abstract class SerialDemoAbstractBase {

	/** The save method in an appliction */
	public void save() throws IOException {
		List v = new ArrayList();
		// Gather the data
		v.add(new Date());
		v.add(new MyData("Ian Darwin", "secret_java_cook"));
		v.add(new MyData("Abby Brant", "dujordian"));
		write(v);
	}

	/** Does the actual serialization */
	public abstract void write(Object theGraph) throws IOException;

	/** Reads the file and displays it. */
	public abstract void dump() throws IOException, ClassNotFoundException;
}
