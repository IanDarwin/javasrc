package lang;

// Show that you do inherit static fields.

/** This is the class that we extend. */
class AnOtherClass {
	AnOtherClass() {
		System.out.println("In AnOtherClass::<init>");
	}
	static int foo = 24;
	static int bar() {
		return 42;
	}
}

/** This is the subclass */
public class InheritStatics extends AnOtherClass {
	public static void main(String[] c) {
		new InheritStatics().run();
	}
	public void run() {
		System.out.println("In InheritStatics::run");
		System.out.println("Foo is " + foo);
		System.out.println("Bar is " + bar());
	}
}
