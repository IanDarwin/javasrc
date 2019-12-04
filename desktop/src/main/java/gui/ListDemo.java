package gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/** Demonstrate old AWT ScrollingList.
 * Note: You should normally use a Swing JList instead.
 */
public class ListDemo extends Frame {
	List list = null;

	ListDemo(String s) {
		super(s);
		setLayout(new FlowLayout());
		list = new List(10, false);
		list.add("Hello");
		list.add("Goodbye");
		list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Selected: " + list.getSelectedItem());
			}
		});

		add(list);
	}

	public static void main(String[] s) {
		ListDemo l = new ListDemo("Up and down the list");
		l.pack();
		l.setVisible(true);
	}
}
