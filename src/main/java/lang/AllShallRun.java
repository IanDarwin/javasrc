package lang;

/**
 * Can we put an arbitrary method-ref into a Runnable var, and run() it?
 */
public class AllShallRun {

	public static void main(String[] args) {
		Runnable mr = AllShallRun::method1;
		mr.run();
		System.out.println(mr.getClass());
	}
	public static void method1() {
		System.out.println("Hello");
	}
}
