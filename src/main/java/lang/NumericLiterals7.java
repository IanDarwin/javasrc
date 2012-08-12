package lang;

import java.text.*;

/** Demonstrate new numeric literal forms in Java 7
 */
public class Num {
	/** boolean literals end with a 'b' */
	static final int bond = 0b00111;
	
	/** Numeric literals may include '_' as a group separator,
	 * presumable chosen to be neutral between the existing groups
	 * separators '.' and ','.
	 */
	static final int million = 1_000_000;
	
	static final double billion = 1_000_000_000d;
	
	static final int bad_ly_formatted = 1_1_1_1_1;
	
	public static void main(String[] args) {
		NumberFormat nf = new SimpleNumberFormat("000");
		
		System.out.println("007 = " + bond);
		System.out.println("Million = " + million);
	}
}
