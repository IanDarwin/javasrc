package numbers;

/**
 * Convert among binary, int, octal, hex, etc.
 * 
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class IntegerBinOctHexEtc {
	public static void main(String[] argv) {
		// BEGIN main
		String input = "101010";
		for (int radix : new int[] { 2, 8, 10, 16, 36 }) {
			System.out.print(input + " in base " + radix + " is "
					+ Integer.valueOf(input, radix) + "; ");
			int i = 42;
			System.out.println(i + " formatted in base " + radix + " is "
					+ Integer.toString(i, radix));
		}
		// END main
	}
}
