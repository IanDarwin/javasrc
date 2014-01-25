package structure;

/** A lax Stack implementation.
 */
@SuppressWarnings("unchecked")
// BEGIN main
public class MyStack<T> {
	
	private int ix = 0;
	public static final int INITIAL = 10;
	private T[] data;
	
	public MyStack() {
		data = (T[])new Object[INITIAL];
	}

	public MyStack(int howBig) {
		if (howBig <= 0) {
			throw new IllegalArgumentException(
			howBig + " must be positive, but was " + howBig);
		}
		data = (T[])new Object[howBig];
	}

	public void push(T obj) {
		// Could check capacity and expand, as in Array2.java
		data[ix++] = obj;
	}

	public boolean hasNext() {
		return ix > 0;
	}

	public boolean hasRoom() {
		return ix < data.length;
	}

	/** Return the given object; removing from array to avoid memory leak */
	public T pop() {
		--ix;
		T tmp = data[ix];
		data[ix] = null;
		return t;
	}

	public int getStackDepth() {
		return ix;
	}
}
// END main
