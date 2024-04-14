package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Simple hack to explore the notion that Java's serialization
 * mechanism does implement "Tolerant Reader" (missing fields null;
 * extra fields ignored) when the serialized data class
 * contains a serialVersionUID like it should (and like your
 * IDE has been warning you about for a decade).
 * This allows you to update client and service independantly if needed.
 *
 * Because Java does not have C-style #ifdef,
 * to run the experiment you have to modify the code;
 * comment out the appropriate subset of the COMMENTME lines.
 */
public class SerialTolerantReader {
	static class Data implements Serializable {
		private static final long serialVersionUID = 8390248329057298L;
		String name;
		String address;
		String city;		// COMMENTME
		public String toString() {
			return name + ", " + address
				+ ", " + city	// COMMENTME
				;
		}
	}

	private static boolean doWrite = false;

	public static void main(String[] args) throws Exception {
		Data d = new Data();
		d.name = "Ian";
		d.address = "123 Main St";
		d.city = "MyTown";		// COMMENTME

		doWrite = true;			// COMMENTME
		if (doWrite) {
			ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("x.ser"));
			ois.writeObject(d);
			ois.close();
		}
		System.out.println("Written OK");

		ObjectInputStream iis = new ObjectInputStream(new FileInputStream("x.ser"));
		Data e = (Data)iis.readObject();
		iis.close();
		System.out.println("Read back: " + e);
	}
}
