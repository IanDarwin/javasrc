package lang;

/** Demonstrate simple inner class. A named inner class
 * is used to show that it can access non-local variables
 * in the enclosing object.
 */
public class InnerClass1 {
	String msg = "Hello";

	void doWork() {
		Inner i = new Inner();
		i.doTheWork();
		msg = "Goodbye";
		i.doTheWork();
	}

	public static void main(String[] av) {
		InnerClass1 p = new InnerClass1();
		p.doWork();
	}

	class Inner {
		public void doTheWork() {
			// print member of enclosing class
			System.out.println(msg);
		}
	}
}
