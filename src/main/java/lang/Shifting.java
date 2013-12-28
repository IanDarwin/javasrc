package lang;

/**
 * Show some effects of shifting on ints and longs
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Shifting {
	public static void main(String[] argv) {
		// BEGIN main
		System.out.println(" 2<<4 = " + (2<<4));
		System.out.println(" 2<<8 = " + (2<<8));
		System.out.println("2<<16 = " + (2<<16));
		System.out.println("2<<24 = " + (2<<24));
		System.out.println("2<<31 = " + (2<<31));
		System.out.println("2<<32 = " + (2<<32));

		// Now try those last two as longs
		System.out.println("2<<31 = " + ((long)2<<31));
		System.out.println("2<<32 = " + ((long)2<<32));
		// END main
	}
}
