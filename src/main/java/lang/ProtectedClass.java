package lang;

/** Can you have such a thing as a protected class?
 */
package foo;

import java.util.Date;

protected class ProtectedClass {		// EXPECT COMPILE ERROR
	public static void main(String[] a) {
		System.out.println("It seems you CAN have a protected class");
	}
}
