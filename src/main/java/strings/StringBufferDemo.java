package strings;

/**
 * StringBufferDemo: construct the same String three different ways.
 */
public class StringBufferDemo {
	public static void main(String[] argv) {
		String s1 = "Hello" + ", " + "World";
		System.out.println(s1);

		// Build a StringBuffer, and append some things to it.
		StringBuffer sb2 = new StringBuffer();
		sb2.append("Hello");
		sb2.append(',');
		sb2.append(' ');
		sb2.append("World");

		// Get the StringBuffer's value as a String, and print it.
		String s2 = sb2.toString();
		System.out.println(s2);

		// Now do the above all over again, but in a more 
		// concise (and typical "real-world" Java) fashion.

		StringBuffer sb3 = new StringBuffer().append("Hello").
			append(',').append(' ').append("World");
		System.out.println(sb3.toString());

		// Exercise for the reader: do it all AGAIN but without
		// creating any temporary variables.
	}
}
