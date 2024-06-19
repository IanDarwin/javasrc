package lang;

// tag::main[]
/**
 * Illustrate "nest mates" access control. Compile this on
 * both Java 1.8 and Java 11, and compared the .class files.
 */
public class NestMates {

	private void privateProcess() {
		System.out.println("Hello from a private method.");
	}

	public class Nested {
		public void innerProcess() {
			privateProcess();
		}
	}

	Nested nested = new Nested();

	public static void main(String[] args) {
		new NestMates().nested.innerProcess();
	}
}
// end::main[]
