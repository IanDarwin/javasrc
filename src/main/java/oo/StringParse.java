package oo;

import com.darwinsys.lang.MutableInteger;

/** Show use of MutableInteger to "pass back" a value in addition
 * to a function's return value.
 */
// BEGIN main
public class StringParse {
	/** This is the function that has a return value of true but
	 * also "passes back" the offset into the String where a
	 * value was found. Contrived example!
	 */
	public static boolean parse(String in, char lookFor, 
		MutableInteger whereFound) {

		int i = in.indexOf(lookFor);
		if (i == -1)
			return false;	// not found
		whereFound.setValue(i);	// say where found
		return true;		// say that it was found
	}

	public static void main(String[] args) {
		MutableInteger mi = new MutableInteger();
		String text = "Hello, World";
		char c = 'W';
		if (parse(text, c, mi)) {
			System.out.println("Character " + c + " found at offset " + 
				mi + " in " + text);
		} else {
			System.out.println("Not found");
		}
	}
}
// END main
