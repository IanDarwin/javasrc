package functional;

import java.util.Arrays;
import java.util.Spliterator;

/**
 * Exploration of Spliterator interface.
 * A Spliterator is like an Iterator but with capabilities for parallel
 * operations (e.g., splitting into forkjoinable segments).
 * @author Ian Darwin
 *
 */
public class SpliteratorDemo {
	static int[] data;
	public static void main(String[] args) {
		Spliterator<int[]> spl;
		spl = Arrays.asList(data).spliterator();
		spl.characteristics();
	}

}
