package lang;

/**
 * Can you change the .length of an array?
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class ChangeArrayLength {
	public static void main(String[] argv) {
		// BEGIN main
		int[] a = new int[4];
		System.out.println(a.length);
		a.length = 5;	// EXPECT COMPILE ERROR
		// END main
	}
}
