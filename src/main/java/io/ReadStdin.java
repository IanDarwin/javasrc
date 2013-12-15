package io;

/**
 * Read one byte from Standard Input - hardly useful on its own.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReadStdin {
	/** Simple test case */
	public static void main(String[] ap) {
		// BEGIN main
		int b = 0;
		try {
			b = System.in.read();
		} catch (Exception e) {
			System.out.println("Caught " + e);
		}
		System.out.println("Read this data: " + (char)b);
		// END main
	}
}
