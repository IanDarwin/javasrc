package functional;

import java.util.concurrent.Callable;

public class LambasWithOverloading {

	void process(Runnable r) {
		dumpOut(r);
		r.run();
	}
	
	<T> T process(Callable<T> c) throws Exception {
		dumpOut(c);
		return c.call();
	}

	private void dumpOut(Object o) {
		System.out.print(o.getClass().getSimpleName());
		if (o instanceof Callable) {
			System.out.print(" (a Callable)");
		}
		if (o instanceof Runnable) {
			System.out.print(" (a Runnable)");
		}
		System.out.println();
	}

	void tryLambdas() throws Exception {
		String retVal = process(() -> "Hello");		// Type of this Lambda is Callable<String>
		
		System.out.println(retVal);
		
		process(() -> System.out.println("Goodbye")); // Void method, No value, so Runnable
	}

	public static void main(String[] args) throws Exception {
		new LambasWithOverloading().tryLambdas();
	}
}
