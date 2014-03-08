package gc;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
	public static void main(String[] args) throws Exception {
		String helloString = args.length > 0 ? args[0] : "Hello";
		WeakReference<String> wr = new WeakReference<>(helloString);
		for (int i = 0; i < 10; i++) {
			System.gc();
			Thread.sleep(i * 1000);
			if (wr.get() == null) {
				System.out.println("RECLAIMED");
			} else {
				System.out.println("Still here: " + wr.get());
			}
		}
	}
}
