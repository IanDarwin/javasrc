import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/** Demonstrate Swing "JList" ScrollingList.
 */
public class JListDemo extends JFrame {
	JList list = null;

	JListDemo(String s) {
		super(s);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		ArrayList data = new ArrayList();
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
