import java.io.*;
import java.util.*;

class MyData implements Serializable {
	String userName;
	String passwordCypher;
	transient String passwordClear;
	public MyData(String name, String clear) {
		userName = name;
		passwordClear = clear;
		// passwordCypher = encrypt();
	}
}

public class Serialize {
	protected static final String FILENAME = "serial.dat";

	public static void main(String s[]) throws IOException {
		ArrayList v = new ArrayList();
		// Gather the data
		MyData u1 = new MyData("Ian Darwin", "secret_java_cook");
		v.add(new Date());
		v.add(u1);
		v.add(new MyData("Abby Brant", "dujordian"));
		// Save the data to disk.
		ObjectOutputStream os = new ObjectOutputStream(
			new FileOutputStream(FILENAME));
		os.writeObject(v);
		os.close();
	}
}
