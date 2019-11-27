package oo;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalMethods {

	/** Explicit demos of some of the methods */
	public static void main(String[] args) {
		final Optional<String> e = Optional.empty();
		final Optional<String> f = Optional.of("Hello");

		show("f.get()", f.get());
		shouldThrow("e.get()", ()->e.get(), NoSuchElementException.class);
	}

	static void show(String mesg, String gotten) {
		System.out.println(mesg + "==>" + gotten);
	}

	static void shouldThrow(String mesg, Runnable method,
		Class<? extends Throwable> cl) {

		try {
			method.run();
			System.out.printf("Eek: %s did not throw anything!\n", mesg);
		} catch (Throwable t) {
			if (t.getClass() == cl) {
				System.out.println(mesg + " did throw " + cl.getSimpleName());
			} else {
				System.out.printf("Ugh! %s threw %s, not %s\n",
					mesg, t.getClass().getName(), cl.getSimpleName());
			}
		}
	}
}
