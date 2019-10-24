package lang;

import java.util.*;
import java.awt.Button;

/**
 * ShowToString -- demo program to show default toString methods.
 * Some classes provide a toString() method, while others inherit
 * the default toString() from Object. In all cases, you can print
 * an object by calling its toString(), which is very useful in debugging!
 */
public class ShowToString {

	/** main method */
	public static void main(String[] argv) {

		System.out.println("An Object\t" + new Object());
		System.out.println("A Date  \t" + new Date());
		System.out.println("A GregorianCalendar\t" + new GregorianCalendar());
		System.out.println("An Exception\t" + new Exception("Hi!"));
		Button b = new Button("Push ME!");	// An AWT pushbutton
		b.setBounds(40, 50, 120, 60);	// explained in the AWT chapter!
		System.out.println("A Button\t" + b);
		System.out.println("A ShowToString object!\t" + new ShowToString());
	}
}
