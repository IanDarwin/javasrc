package lang;

/** Demonstrate inner-inner class. A named inner class
 * is used to show that it can access non-local variables
 * in the enclosing object.
 */
public class InnerClass3 {
	static String msg = "Hello";

	public static void main(String[] av) {
		class Inner {
			public void doTheWork() {
				// print member of enclosing class
				System.out.println(msg);
			}
		}
		Inner p = new Inner();
		p.doTheWork();
	}

}
