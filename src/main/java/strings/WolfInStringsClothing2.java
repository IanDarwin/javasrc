package strings;

import java.lang.reflect.*;

/**
 * If this class RUNS without exception, Java security is a myth.
 */
public class WolfInStringsClothing2 {

	public static void main(String[] args) throws Exception {
		String target = "Sheep may safely graze";
		System.out.println("Now it says: " + target);
		setCharsAt(target, 0, "Wolfs");
		System.out.println("Now it says: " + target);
	}

	/*
	 * A method that destroys the immutability of strings.
	 */
	public static void setCharsAt(String target, int index, String newChars) throws Exception {
		Class<String> c = String.class;
		for (Field f : c.getDeclaredFields()) {
			f.setAccessible(true);	// bye-bye "private"

			// ALthough String.class.getField("value") throws a
			// NoSuchField exception, this way works, alas.
			if ("value" == f.getName()) {
				char[] data = (char[])f.get(target);
				for (int i = 0; i < newChars.length(); i++) {
					data[i] = newChars.charAt(i);
				}
			}
		}
	}
}
