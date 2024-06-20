public class intprivate {

/**
 * Just to show that interface private methods cannot be called outside
 * the interface, even by members of the enclosing class.
 */
void main() {
	new A().doNothing();	// EXPECT COMPILE ERROR as member is private
}

interface x {
	private void doNothing() {
		System.out.println("Hello from a private method");
	}
}

class A implements x {
	// empty
}
}
