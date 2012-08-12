package lang;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/** Demonstrate new numeric literal forms in Java 7
 * 
 * P L E A S E   R E A D   B E F O R E   C O M P L A I N I N G
 * This class absolutely requires Java SE 7+, so just add an exclusion rule
 * (Build Path -> Exclude) if you are living with a legacy version of Java SE.
 */
public class NumericLiterals7 {
	
	/** binary literals begin with 0b, analogous to 0x */
	static final int bond = 0b00111;
	
	/** Numeric literals may include '_' as a group separator,
	 * presumably chosen to be neutral between the existing groups
	 * separators '.' and ','.
	 */
	static final int million = 1_000_000;
	
	static final double billion = 1_000_000_000d;
	
	static final int bad_ly_formatted = 1_1_1_1_1;
	
	/** And a fun example from Oracle (every line but one corrected), from
	 * http://docs.oracle.com/javase/7/docs/technotes/guides/language/binary-literals.html
	 */
	public static final short[] HAPPY_FACE = {
		   (short)0b0000011111100000,
		   (short)0b0000100000010000,
		   (short)0b0001000000001000,
		   (short)0b0010000000000100,
		   (short)0b0100000000000010,
		   (short)0b1000011001100001,
		   (short)0b1000011001100001,
		   (short)0b1000000000000001,
		   (short)0b1000000000000001,
		   (short)0b1001000000001001,
		   (short)0b1000100000010001,
		   (short)0b0100011111100010,
		   (short)0b0010000000000100,
		   (short)0b0001000000001000,
		   (short)0b0000100000010000,
		   (short)0b0000011111100000,
		};
	
	public static void main(String[] args) {
		NumberFormat mi5 = new DecimalFormat("000");
		
		System.out.println("Bond = " + mi5.format(bond));
		System.out.println("Million = " + million);
		System.out.println("Billion = " + billion);
		System.out.println("Badly formatted int = " + bad_ly_formatted);
	}
}
