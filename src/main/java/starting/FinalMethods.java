package starting;

/** Investigate inlining of final methods.
 * You need to investigate the resulting class file
 * when generated from various compilers and options.
 */
public final class FinalMethods {
	public static void main(String[] args) {
		new FinalMethods().work();
	}

	public void work() {
		methodA();
		methodB();
		methodC();
	}

	public void methodA() {
		System.out.println("In MethodA");
	}

	private final void methodB() {
		System.out.println("In MethodB");
	}

	public void methodC() {
		System.out.println("In MethodC");
	}
}
