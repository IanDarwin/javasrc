package lang;

/**
 * This shows something interesting about addition of "byte" variables
 * (which also applies to all integer types).
 */
public class ByteAdd2 {
	public static void main(String[] argv) {
		System.out.println("ByteAdd Program");
		// bytes are signed integer, so can range from -127 to +127
		byte b1 = 10;
		b1 += 1;		// This works, following does not:
		b1 = b1 + 1;	// EXPECT COMPILE ERROR
	}
}
