package io;

/**
 * Read one byte from Standard Input - hardly useful on its own.
 * @author	Ian F. Darwin, https://darwinsys.com/
 */
public class ReadStdin {
	/** Simple test case */
	public static void main(String[] ap) {
		// tag::main[]
		int b = 0;
		try {
			b = System.in.read();
			System.out.println("Read this data: " + (char)b);
		} catch (Exception e) {
			System.out.println("Caught " + e);
		}
		// end::main[]
	}
}
