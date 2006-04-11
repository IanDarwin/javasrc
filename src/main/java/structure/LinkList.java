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
public class LinkList implements List {

	/* A TNode stores one node or item in a Linked List */
	class TNode {
		TNode next;
		Object data;
		TNode(Object o) {
			data = o;
			next = null;
		}
	}

	/** The root or first TNode in the list. */
	protected TNode first;
	/** The last TNode in the list */
	protected TNode last;

	/** Construct a LinkList: initialize the first and last nodes */
	public LinkList() {
		super();
		clear();
	}

	/** Construct a LinkList given another Collection.
	 * This method is recommended by the general contract of List.
	 */
	public LinkList(Collection c) {
		this();
		addAll(c);
		throw new IllegalArgumentException("Can't construct(Collection) yet");
	}

	/** Set the List (back) to its initial state.
	 * Any references held will be discarded.
	 */
    public void clear() {
		first = new TNode(this);
		last = first;
	}

	/** Add one object to the end of the list. Update the "next"
	 * reference in the previous end, to refer to the new node.
	 * Update "last" to refer to the new node. 
	 */
	public boolean add(Object o) {
		last.next = new TNode(o);
		last = last.next;
		return true;
	}

    public void add(int where, Object o) {
		TNode t = first;
		for (int i=0; i<where; i++)
			t = t.next;
		TNode t2 = t;
		t.next = new TNode(o);
		t.next = t2;
	}

    public int size() {
		TNode t = first;
		int i;
		for (i=0; ; i++) {
			if (t == null)
				break;
			t = t.next;
		}
		return i - 1;	// subtract one for mandatory head node.
	}

    public boolean isEmpty() {
		return size() == 0;
	}

    public Object get(int where) {
		TNode t = first;
		for (int i=0; i<where; i++) {
			t = t.next;
		}
		return t.data;
	}

    public Object set(int i, Object o) {
		return null;
	}
    public boolean contains(Object o) {
		return false;
	}
    public Object[] toArray() {
		return null;
	}
    public Object[] toArray(Object[] data) {
		return null;
	}
    public boolean remove(Object o) {
		return false;
	}
    public Object remove(int i) {
		return null;
	}
    public boolean containsAll(Collection c) {
		return false;
	}
    public boolean addAll(Collection c) {
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

    public ListIterator listIterator() {
		throw new UnsupportedOperationException("listIterator");
	}

    public Iterator iterator() {
		return new Iterator() {
			TNode t = first;
			public boolean hasNext() {
				return t != last;
			}
			public Object next() {
				if (t == last)
					throw new ArrayIndexOutOfBoundsException();
				return t = t.next;
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		};
	}

    public ListIterator listIterator(int where) {
		return null;
	}

    public List subList(int sub1, int sub2) {
		return null;
	}
}
