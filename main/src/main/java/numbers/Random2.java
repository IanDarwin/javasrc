package numbers;

import java.util.Random;

/** Demonstrate the better way of getting random numbers,
 * using java.util.Random.next*().
 */
public class Random2 {
	public static void main(String[] argv) {
	// tag::main[]
	// java.util.Random methods are non-static; we need an instance
	Random r = new Random();
	for (int i=0; i<10; i++) {
		System.out.println("A double from java.util.Random is " + r.nextDouble());
	}
	for (int i=0; i<10; i++) {
		System.out.println("An integer from java.util.Random is " + r.nextInt());
	}
	// end::main[]
	}
}
