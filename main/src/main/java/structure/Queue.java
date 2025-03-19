package structure;

import java.util.LinkedList;

/** Treat a LinkList as a Queue */
public class Queue<T> extends LinkedList<T> {
	private static final long serialVersionUID = 1L;

	public void q_add(T o) {
		addLast(o);
	}

	public T q_take() {
		return removeFirst();
	}

	public int q_len() {
		return size();
	}

	public boolean q_empty() {
		return size() == 0;
	}

	public T q_check() {
		return getFirst();
	}

	public void q_delete(T o) {
		remove(o);
	}
}
