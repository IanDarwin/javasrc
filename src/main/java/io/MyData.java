import java.io.Serializable;

/** Simple data class used in Serialization demos. */
public class MyData implements Serializable {
	String userName;
	String passwordCypher;
	transient String passwordClear;
	public MyData(String name, String clear) {
		userName = name;
		// Save the clear text p/w in the object, it won't get serialized
		passwordClear = clear;
		// So we must save the encryption! Encryption not shown here.
		passwordCypher = new DES().encrypt(passwordClear);
	}

	/** This just generates a String; Strings are serializable */
	class DES {
		// Obviously just a placeholder.
		public String encrypt(String s) {
			return s;
		}
	}
}
