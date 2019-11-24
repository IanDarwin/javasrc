package strings;

/** Convert among Unicode, ASCII and byte/int. */
public class UnicodeCast {
	public static void main(String[] args) {

		// Simple arithmetic on chars - VERY BAD for internationalization!
		System.out.println("'a' + 1 = " + (char)('a' + 1));

		// Truncate characters by casting to byte (16-bit to 8-bit casting)
		char yen = '\u00a5';		// Japanese Yen
		char aeAcute = '\u01FC';	// Roman AE with acute accent
		System.out.println("Yen as byte: " + (byte)yen);
		System.out.println("AE' as byte: " + (byte)aeAcute);
		System.out.println("Yen as byte to char: " + (char)(byte)yen);
		System.out.println("AE' as byte to char: " + (char)(byte)aeAcute);

		// Convert ints to chars
		int iYen = 0xa5;
		int iaeAcute = 0x01fc;
		System.out.println("Yen from int = " + (char)iYen);
		System.out.println("AE' from int = " + (char)iaeAcute);

	}
}
