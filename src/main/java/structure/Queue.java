/** Treat a LinkList as a Queue */
public class Queue extends java.util.LinkedList {
	public void q_add(Object o) {
		addLast(o);
	}

	public Object q_take() {
		return removeFirst();
	}

	public int q_len() {
		return size();
	}

	public boolean q_empty() {
		return size() == 0;
	}

	public Object q_check() {
		return getFirst();
	}

	// public void q_delete(Object o) {
	// }
}
