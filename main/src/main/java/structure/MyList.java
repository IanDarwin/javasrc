package structure;

import java.util.function.Consumer;

/**
 * A very trivial SUBSET of the List interface, just to show how static and default methods
 * can be used to create and operate on instances of implementing classes.
 * Features include: No generic type, no error checking, missing most of the real List methods.
 */
public interface MyList {

	void add(Object o);

	Object get(int i);

	int size();

	/** Default implementation of forEach. */
	default void forEach(Consumer c) {
		for (int i = 0; i < size(); i++) {
			Object o = get(i);
			c.accept(o);
		}
	}

	/**
	 * Create a list out "of" a bunch of arguments.
	 * N.B. The real java.util.List interface doesn't use varargs but
	 * has a series of overloads with 1, 2, 3,... arguments; presumably
	 * because otherwise they'd get this error at compile time:
	 * non-static type variable T cannot be referenced from a static context
	 */
	static MyList of(Object... obj) {
		MyListImpl data = new MyListImpl();
		for (Object o : obj) {
			data.add(o);
		}
		return data;
	};
}
