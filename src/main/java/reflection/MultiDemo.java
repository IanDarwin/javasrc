package introspection;



/**
 * Utterly trivial class used by "ClassLoaderMultiple"
 * @version $Id$
 */
public class MultiDemo {
	static {
		System.out.println("Demo loaded");
	}
	public MultiDemo() {
	}
	public static void test() {
		System.out.println("Demo.test invoked");
	}
}
