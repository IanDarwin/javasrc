public class MyStack<T> {
	private T[] data = new T[10];

	public void push(T obj) {
		data[ix++] = obj;
	}

	public T pop() {
		if (ix < 0) {
			throw new ArrayIndexOutofBoundsException(-1);
		}
		return data[ix--];
	}
}
