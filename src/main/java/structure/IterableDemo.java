package structure;

import java.util.*;

/**
 * Simple demo of how Iterable works.
 */
public class IterableDemo {

	/** Demo implements Iterable, meaning it must provide an Iterator,
	 * and, that it can be used in a foreach loop.
	 */
	static class Demo implements Iterable<String> {

		String[] data = { "One", "Two", "Three"};

		/** This is the Iterator that makes it all happen */
		class DemoIterator implements Iterator<String> {
			int i = 0;
			public boolean hasNext() {
				return i < 3;
			}
			public String next() {
				return data[i++];
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		}
		
		/** Method by which the Demo class makes its iterator available */
		public Iterator<String> iterator() {
			return new DemoIterator();
		}
	}
		
	public static void main(String[] args) {
		Demo demo = new Demo();
		for (String s : demo) {
			System.out.println(s);
		}
	}
}

