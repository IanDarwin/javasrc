package numbers;

/**
 * Convert among binary, int, octal, hex, etc.
 * 
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class IntegerBinOctHexEtc {
	public static void main(String[] argv) {
		// tag::main[]
		String input = "101010";
		int i = 42;
		for (int radix : new int[] { 2, 8, 10, 16, 36 }) {
			System.out.print(input + " in base " + radix + " is "
					+ Integer.valueOf(input, radix) + "; ");
			System.out.println(i + " formatted in base " + radix + " is "
					+ Integer.toString(i, radix));
		}
		// end::main[]
	}
}
