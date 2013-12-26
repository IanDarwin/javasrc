package structure;

/** A lax Stack implementation.
 */
@SuppressWarnings("unchecked")
// BEGIN main
public class MyStack<T> {
	
	private int ix = 0;
	public static final int MAX = 10;
	private T[] data;
	
	public MyStack() {
		data = (T[])new Object[MAX];
	}

	public MyStack(int howBig) {
		if (howBig <= 0) {
			throw new IllegalArgumentException(howBig + " must be positive");
		}
		data = (T[])new Object[howBig];
	}

	public MyStack(T[] data) {
		super();
		this.data = data;
	}

	public void push(T obj) {
		data[ix++] = obj;
	}

	public boolean hasNext() {
		return ix > 0;
	}

	public boolean hasRoom() {
		return ix < data.length;
	}

	public T pop() {
		if (hasNext()) {
			return data[--ix];
		}
		throw new ArrayIndexOutOfBoundsException(-1);
	}

	public int getStackDepth() {
		return ix;
	}
}
// END main
