package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/** Demonstrate Swing "JList" ScrollingList.
 */
public class JListDemo extends JFrame {
	JList list = null;

	JListDemo(String s) {
		super(s);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		List<String> data = new ArrayList<String>();
		data.add("Hi");
		data.add("Hello");
		data.add("Goodbye");
		data.add("Adieu");
		data.add("Adios");
		list = new JList(data.toArray());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
					return;
				System.out.println("Selected from " +
					evt.getFirstIndex() + " to " + evt.getLastIndex());
			}
		});
		cp.add(list, BorderLayout.CENTER);
	}

	public static void main(String[] s) {
		JListDemo l = new JListDemo("Greetings");
		l.pack();
		l.setVisible(true);
	}
}
