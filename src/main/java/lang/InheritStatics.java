// Show that you do inherit static fields.

/** This is the class that we extend. */
class SomeOtherClass {
	SomeOtherClass() {
		System.out.println("In SomeOtherClass::<init>");
	}
	static int foo = 24;
	static int bar() {
		return 42;
	}
}

/** This is the subclass */
public class InheritStatics extends SomeOtherClass {
	public static void main(String[] c) {
		new InheritStatics().run();
	}
	public void run() {
		System.out.println("In InheritStatics::run");
		System.out.println("Foo is " + foo);
		System.out.println("Bar is " + bar());
	}
}
