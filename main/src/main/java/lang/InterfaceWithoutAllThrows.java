package lang;

import java.io.*;

/** This shows that a class implementing an interface need not
 * declare all the Throws that are declared in the interface.
 */
public class InterfaceWithoutAllThrows {
	interface bar {
		public void foo() throws IOException;
	}
	class baz implements bar {
		public void foo() {
			System.out.println("This is foo-lish");
		}
	}
	public static void main(String[] argv) {
		new InterfaceWithoutAllThrows().new baz().foo();
	}
}

