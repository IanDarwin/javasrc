/**
 * Can you call a static method of an abstract class?
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version XXX Id$
 */
public abstract class AbstractStatic {

	public static void main(String argv[]) {
		//+
		System.out.println("Hello. The answer is yes.");
		// The following is redundant, since we got in here!
		AbstractStatic.foo();
		//-
	}

	public static void foo() {
		System.out.println("Hello from foo. The answer is still yes.");
	}

	/* And if you think about it, of course non-abstract static
	 * methods of an abstract class can be called. Isn't this
	 * how most "factory methods" work?
	 */
}
