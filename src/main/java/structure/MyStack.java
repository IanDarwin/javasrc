/** A surprisingly lax Stack implementation. */
public class MyStack<T> {
	public final int MAX = 10;
	private T[] data = new T[MAX];

	public void push(T obj) {
		data[ix++] = obj;
	}

	public boolean hasNext() {
		return ix >= 0;
	}

	public boolean hasRoom() {
		return ix < (MAX - 1);
	}

	public T pop() {
		if (hasNext()) {
			return data[ix--];
		}
		throw new ArrayIndexOutofBoundsException(-1);
	}
}
