package lang;

/** Show various forms of nested classes.
 * Not all nested classes are "inner classes".
 */
class A extends Object {
	public class B {	// member class
		public class BB {
			public void print() {
				System.out.println("Hello from BB");
			}
		}
		public void print() {
			BB bb = new BB();
			bb.print();
		}
	}
	public void print() {
		class C {	// local class
		}
		Object d = new Object() {
			// anonymous inner class
		};
		System.out.println("Here are an A, a B, a C, and d.");
		System.out.println(this + " " + new B() + " " +
						new C() + " " + d);
		new B().print();
	}
	public static void main(String[] av) {
		new A().print();
	}
}
