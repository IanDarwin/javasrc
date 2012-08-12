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
	
	public static void main(String[] args) {
		NumberFormat mi5 = new DecimalFormat("000");
		
		System.out.println("Bond = " + mi5.format(bond));
		System.out.println("Million = " + million);
		System.out.println("Billion = " + billion);
		System.out.println("Badly formatted int = " + bad_ly_formatted);
	}
}
