/**
 * Template for standalone, line-mode main program.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class BinaryDigits {
	public static void main(String argv[]) {
		//+
		String bin = "101010";
		System.out.println(bin + " as an integer is " + Integer.valueOf(bin, 2));
		int i = 42;
		System.out.println(i + " as binary digits (bits) is " + 
			Integer.toBinaryString(i));
		//-
	}
}
