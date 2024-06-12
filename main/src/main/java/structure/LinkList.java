package structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Linked list class, written in Java.
 * This is not intended to be a usable List, and it is probably
 * not going to be optimal in terms of performance; many operations
 * are linear walks of the list, hence O(n), painful but not intolerable
 * for small or medium-sized lists. This implementation is in part
 * here to remind you how much work the existing List implementations do.
 * <br/>
 * For a production-ready version, consider subclassing AbstractSequentialList.
 * Or, better, just use java.util.LinkedList
 * @deprecated	Supplanted by LinkedList
 * @author	Ian Darwin
 */
@Deprecated
// tag::main[]
public class LinkList<T> implements List<T> {

	/* A TNode stores one node or item in a Linked List */
	protected static class TNode<T> {
		private TNode<T> next;
		private T data;
		TNode(T o, TNode<T> next) {
			data = o;
			this.next = next;
		}
		@Override
		public String toString() {
			return "TNode: data='%s', next='%d'".formatted(data,
				next == null ? 0 : next.hashCode());
		}
	}

	private final boolean DIAGNOSTIC = false;
	
	/** The root or first TNode in the list; is a dummy pointer,
	 * so its data will always be null. Simpler this way.
	 */
	protected TNode<T> first;
	/** 
	 * For certain optimizations: A second ref to the last TNode in the list; 
	 * initially == first; always valid (never null), always has next == null.
	 */
	protected TNode<T> last;

	/** Construct a LinkList: initialize the first and last nodes */
	public LinkList() {
		clear();
	}

	/** Construct a LinkList given another Collection.
	 * This method is recommended by the general contract of List.
	 */
	public LinkList(Collection<T> c) {
		this();
		addAll(c);
	}

	/** Set the List (back) to its initial state.
	 * Any references held will be discarded.
	 */
	@Override
	public void clear() {
		first = new TNode<T>(null, null);
		last = first;
	}

	/** Add one object to the end of the list. Update the "next"
	 * reference in the previous end, to refer to the new node.
	 * Update "last" to refer to the new node. 
	 */
	@Override
	public boolean add(T o) {
		last.next = new TNode<T>(o, null);
		last = last.next;
		return true;
	}

	@Override
	public void add(int where, T o) {
		TNode<T> t = first;
		for (int i=0; i<=where; i++) {
			t = t.next;
			if (t == null) {
				throw new IndexOutOfBoundsException(
					"'add(n,T) went off end of list");
			}
			if (DIAGNOSTIC) {
				System.out.printf("in add(int,T): i = %d, t = %s%n", i, t);
			}
		}
		if (DIAGNOSTIC) {
			System.out.printf("in add(int,T): to insert before %s\n", t);
		}
		final TNode<T> nn = new TNode<>(o, t.next);
		t.next = nn;
		if (DIAGNOSTIC) {
			System.out.printf("add(%d,%s)\n", where, o);
			dump("add(int,T)");
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		c.forEach(o -> add((T) o));
		return true;
	}

	@Override
	public boolean addAll(int i, Collection<? extends T> c) {
		AtomicInteger j = new AtomicInteger(i);
		c.forEach(o -> { add(j.getAndIncrement(), o); });
		return true;
	}
	
	@Override
	public boolean contains(Object o) {
		TNode<T> t = first;
		while ((t = t.next) != null) {
			if (t.data.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public T get(int where) {
		TNode<T> t = first;
		int i=0; 
		// If we get to the end of list before 'where', error out
		while (i++<=where) {
			if (t.next == null) {
				throw new IndexOutOfBoundsException();
			}
			t = t.next;
		}
		return t.data;
	}
	
	@Override
	public boolean isEmpty() {
		return first == last;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			final int size = size();
			int n = 0;
			TNode<T> t = first;
			/**
			 * Two cases in which next == null:
			 * 1) The list is empty, we are at first
			 * 2) The list is not empty, we are at last.
			 */
			public boolean hasNext() {
				return n < size;
			}

			public T next() {
				if (t == first) {
					t = t.next;
				}
				TNode<T> result = t;
				t = t.next;
				++n;
				return result.data;
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		};
	}

	@Override
	public boolean remove(Object o) {
		TNode<T> p = first, prev = null;
		while (p != null) {
			if (p.data == o) {
				prev.next = p.next;
				return true;
			}
			prev = p; p = p.next;
		}
		return false;
	}

	@Override
	public T set(int i, T o) {
		TNode<T> tmp = find(i);
		tmp.data = o;
		return o;
	}

	@Override
	public int size() {
		TNode<T> t = first;
		int i;
		for (i=0; ; i++) {
			if (t == null)
				break;
			t = t.next;
		}
		return i - 1;	// subtract one for mandatory head node.
	}

	@SuppressWarnings("unchecked")
	public T[] toArray(Object[] data) {
		// First is an empty anchor, start at its next
		TNode<T> p = first.next;
		for (int i = 0; p != null && i < data.length; i++) {
			data[i] = p.data;
			p = p.next;
		}
		return (T[]) data;
	}

	public Object[] toArray() {
		Object[] data = new Object[size()];
		return toArray(data);
	}
	// end::main[]
	
	// NON-API private methods

	private void dump(String s) {
		if (!DIAGNOSTIC) {
			return;
		}
		System.err.println("Dump(" + s + ")");
		TNode<T> p = first;
		do {
			p = p.next;
			if (p == p.next) {
				System.err.println("SELF-POINTER AT " + p);
				return;
			}
			System.err.printf("cur=%d data=%s next=%d\n",
				p.hashCode(), p.data, 
				p.next != null ? p.next.hashCode() : 0);
		} while (p.next != null);
	}
	
	public TNode<T> find(int where) {
		TNode<T> t = first;
		int i=0; 
		// If we get to the end of list before 'where', error out
		while (i++<=where) {
			if (t.next == null) {
				return null;
			}
			t = t.next;
		}
		return t;
	}

	// THE FOLLOWING METHODS ARE NOT YET IMPLEMENTED!

	@Override
	public T remove(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("listIterator");
	}

	@Override
	public ListIterator<T> listIterator(int where) {
		throw new UnsupportedOperationException("listIterator");
	}

	@Override
	public List<T> subList(int sub1, int sub2) {
		throw new UnsupportedOperationException();
	}

	// FOLLOWING OPTIONAL METHODS WILL PROBABLY NOT GET IMPLEMENTED

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

}
