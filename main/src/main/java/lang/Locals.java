package lang;

/** Show locals not conflicting */
public class Locals {
	final int i = 42;

	void method1(/*int i*/) {
		// int i = 12; 	// won't compile with this in
		for (int i=0; i<10; i++)
			System.out.println(i);
		System.out.println(i);
	}
	public static void main(String[] a) {
		new Locals().method1();
	}
}
