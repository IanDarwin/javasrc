import java.util.Date;

/**
 * Linked list class
 * @deprecated	Supplanted by java.util.LinkList
 * @author	Ian Darwin, ian@darwinsys.com
 */
public class LinkList {
	public static void main(String argv[]) {
		System.out.println("Hello, World of Java");
		LinkList l = new LinkList();
		l.add(new Date());
		l.add("Hello");
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
