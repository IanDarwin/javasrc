import java.io.*;
import java.util.*;

class MyData implements Serializable {
	String userName;
	String passwordCypher;
	transient String passwordClear;
	public MyData(String name, String clear) {
		userName = name;
		// Save the clear text p/w in the object, it won't get serialized
		passwordClear = clear;
		// So we must save the encryption! Encryption not shown here.
		passwordCypher = DES.encrypt(passwordClear);
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

class DES {
	public static String encrypt(String s) {
		return s;
	}
}
