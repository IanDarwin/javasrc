package lang;

/** Show that if your class has no constructors, your superclass'
 * constructors still get called.
 */
public class InheritConstructor extends SomeOtherClass {
	public static void main(String[] c) {
		new InheritConstructor().run();
	}
	public void run() {
		System.out.println("In InheritConstructor::run");
	}
}

// This is the class that we extend.
class SomeOtherClass {
	SomeOtherClass() {
		System.out.println("In SomeOtherClass::<init>");
	}
}
