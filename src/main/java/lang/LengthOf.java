package lang;

/**
 * LengthOf - show length of things
 */
public class LengthOf {
	public static void main(String[] argv) {
		int    ints[] = new int[3];
		Object objs[] = new Object[7];

		String stra = "Hello World";
		String strb = new String();

		// Length of any array - use its length attribute
		System.out.println("Length of argv is " + argv.length);
		System.out.println("Length of ints is " + ints.length);
		System.out.println("Length of objs is " + objs.length);

		// Length of any string - call its length() method.
		System.out.println("Length of stra is " + stra.length());
		System.out.println("Length of strb is " + strb.length());
	}
}
