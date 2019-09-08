
package structure;

import java.util.*;

/**
 * Simple demo of how Iterable works.
 */
// tag::main[]
public class IterableDemo {

	/** Demo implements Iterable, meaning it must provide an Iterator,
	 * and, that it can be used in a foreach loop.
	 */
	static class Demo<T> implements Iterable<T> {

		// Simple demo: use array instead of inventing new data structure
		String[] data = { "One", "Two", "Three"};

		/** This is the Iterator that makes it all happen */
		class DemoIterator implements Iterator<T> {
			int i = 0;
			public boolean hasNext() {
				return i < 3;
			}
			public T next() {
				return (T)data[i++];
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		}
		
		/** Method by which the Demo class makes its iterator available */
		public Iterator<T> iterator() {
			return new DemoIterator();
		}
	}
		
	public static void main(String[] args) {
		Demo<String> demo = new Demo<>();
		for (String s : demo) {
			System.out.println(s);
		}
	}
}
// end::main[]

