package structure;

public class FixedLengthQueue<T> extends Queue<T> {
	private static final long serialVersionUID = 1L;
	final T[] data;
	int ix = 0, start = 0;

	@SuppressWarnings("unchecked")
	public FixedLengthQueue(int size) {
		data = (T[]) new Object[size];
	}
	
	// Do NOT call next() or prev() unless you are committed to storing in the next element
	private int next() {
		if (ix++ > data.length) {
			start++;
		}
		return ix%data.length;
	}
	private int prev() {
		if (--ix < 0) {
			ix = data.length;
		}
		return ix % data.length;
	}
	
	public boolean add(T e) {
		data[next()] = e;
		return true;
	}
	public T element() {
		return null;
	}
	public boolean offer(T e) {
		return add(e);
	}
	public T peek() {
		return (T)data[ix];
	}
	public T poll() {
		return (T)data[--ix];
	}
	public T remove() {
		return poll();
	}
}
