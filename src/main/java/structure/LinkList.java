/**
 * Linked list class, written out in full using Java.
 * @deprecated	Supplanted by java.util.LinkedList
 * @author	Ian Darwin, ian@darwinsys.com
 */
public class LinkList {
	public static void main(String argv[]) {
		System.out.println("Here is a demo of writing a Linked List in Java");
		LinkList l = new LinkList();
		l.add(new Object());
		l.add("Hello");
		System.out.println("Here is a list of all the elements");
		l.print();
		if (l.lookup("Hello"))
			System.err.println("Lookup works");
		else
			System.err.println("Lookup does not work");
	}

	class TNode {
		TNode next;
		Object data;
		TNode(Object o) {
			data = o;
			next = null;
		}
	}
	protected TNode root;
	protected TNode last;

	LinkList() {
		root = new TNode(this);
		last = root;
	}

	void add(Object o) {
		last.next = new TNode(o);
		last = last.next;
	}

	public boolean lookup(Object o) {
		for (TNode p=root.next; p != null; p = p.next)
			if (p.data==o || p.data.equals(o))
				return true;
		return false;
	}

	void print() {
		for (TNode p=root.next; p != null; p = p.next)
			System.out.println("TNode" + p + " = " + p.data);
	}
}
