package structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Linked list class, written in Java.
 * This is <b>not</b> intended to be a usable List, and it is certainly
 * not going to be optimal in terms of performance; it is just
 * here to remind you how much work the existing List implementations do.
 * <br/>
 * TODO: scrap this and start again, subclassing AbstractSequentialList
 * @deprecated	Supplanted by LinkedList
 * @author	Ian Darwin
 */
@Deprecated
// BEGIN main
public class LinkList<T> implements List<T> {

	/* A TNode stores one node or item in a Linked List */
	private static class TNode<T> {
		TNode<T> next;
		T data;
		TNode(T o) {
			data = o;
			next = null;
		}
	}

	private boolean DIAGNOSTIC = true;
	
	/** The root or first TNode in the list. */
	protected TNode<T> first;
	/** The last TNode in the list */
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
    public void clear() {
		first = new TNode<T>(null);
		last = first;
	}

	/** Add one object to the end of the list. Update the "next"
	 * reference in the previous end, to refer to the new node.
	 * Update "last" to refer to the new node. 
	 */
	public boolean add(T o) {
		last.next = new TNode<T>(o);
		last = last.next;
		return true;
	}

    public void add(int where, T o) {
		TNode<T> t = first;
		for (int i=0; i<=where; i++) {
			t = t.next;
			if (t == null) {
				throw new IndexOutOfBoundsException(
					"'add(n,T) went off end of list");
			}
			if (DIAGNOSTIC) {
				System.out.printf("add(int,T): i = %d, t = %s%n", i, t);
			}
		}
		TNode<T> t2 = t;
		t.next = new TNode<T>(o);
		t.next = t2;
	}

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

    public boolean isEmpty() {
		return first == last;
	}

    public T get(int where) {
		TNode<T> t = first;
		int i=0; 
		// If we get to the end of list before 'where', error out
		while (i<=where) {
			i++;
			if ((t = t.next) == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		return t.data;
	}

    public T set(int i, T o) {
		return null;
	}
    
    public boolean contains(Object o) {
    		TNode<T> t = first;
		while ((t = t.next) != null) {
			if (t.data.equals(o)) {
				return true;
			}
		}
		return false;
	}
    public boolean addAll(Collection<? extends T> c) {
    	c.forEach(o -> add((T) o));
		return false;
	}

    public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("listIterator");
	}

    public Iterator<T> iterator() {
		return new Iterator<T>() {
			TNode<T> t = first.next;
			public boolean hasNext() {
				return t != last;
			}
			public T next() {
				if (t == last)
					throw new IndexOutOfBoundsException();
				return (T) (t = t.next);
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		};
	}
	// END main

	// THE FOLLOWING METHODS ARE NOT YET IMPLEMENTED!

    public Object[] toArray() {
		return null;
	}

    public T[] toArray(Object[] data) {
		return null;
	}

    public boolean remove(Object o) {
		return false;
	}

    public T remove(int i) {
		return null;
	}

    public boolean containsAll(Collection c) {
		return false;
	}
    public boolean addAll(int i, Collection c) {
		return false;
	}

    public boolean removeAll(Collection c) {
		return false;
	}

    public boolean retainAll(Collection c) {
		return false;
	}

    public int indexOf(Object o) {
		return 0;
	}

    public int lastIndexOf(Object o) {
		return 0;
	}

    public ListIterator<T> listIterator(int where) {
		return null;
	}

    public List<T> subList(int sub1, int sub2) {
		return null;
	}
}
