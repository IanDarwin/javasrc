package lang;

import java.util.List;

public class OverrideMethod {

	static class A {
		String info() { return "Class A"; }
		final String greeting() { return "Hello"; }
	}
	static class B extends A {
		String info() { return "Class B"; }
		// String greeting() { return "Bye Bye"; } // COMPILE ERROR
	}
	public static void main(String[] args) {
		List.of(
			new A().info(),
			new A().greeting(),
			new B().info(),
			new B().greeting() ).forEach(System.out::println);
	}
}
