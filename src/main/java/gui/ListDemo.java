import java.awt.*;

/** Demonstrate old AWT ScrollingList.
 * Note: You should normally use a Swing JList instead.
 */
public class ListDemo extends Frame {
	List list = null;

	ListDemo(String s) {
		super(s);
		setLayout(new FlowLayout());
		list = new List(10, false);
		list.addItem("Hello");
		list.addItem("Goodbye");
		add(list);
	}

	public boolean action(Event e, Object o) {
		System.out.println(e.target + "=" + (String)o);
		return true;
	}

	public static void main(String[] s) {
		ListDemo l = new ListDemo("Up and down the list");
		l.pack();
		l.setVisible(true);
	}
}
