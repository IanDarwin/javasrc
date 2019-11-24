package reflection;

/**
 * Utterly trivial class used by "ClassLoaderMultiple"
 */
public class MultiDemo {
	static {
		System.out.println("MultiDemo loaded");
	}
	public MultiDemo() {
	}
	public static void test() {
		System.out.println("MultiDemo.test invoked");
	}
	@Override
	public String toString() {
		return "A MultiDemo object";
	}
}
