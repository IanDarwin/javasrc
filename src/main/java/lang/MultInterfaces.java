package lang;

interface I1 {
	abstract void test(int i);
}
interface I2 {
	abstract void test(String s);
}

/**
 * To show what happens to the possible conflicts if you 
 * implement multiple interfaces: there are no conflicts.
 * If multiple interfaces have the exact same method, you
 * merely have to implement it.
 * If multiple interfaces have similar methods, you must
 * implement them all.
 * There's still no conflict.
 */
public class MultInterfaces implements I1, I2 {
	public void test(int i) {
		System.out.println("In MultInterfaces.I1.test");
	}
	public void test(String s) {
		System.out.println("In MultInterfaces.I2.test");
	}
	public static void main(String[] a) {
		MultInterfaces t = new MultInterfaces();
		t.test(42);
		t.test("Hello");
	}
}

