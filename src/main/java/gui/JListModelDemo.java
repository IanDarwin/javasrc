import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/** Demonstrate Swing "JList" ListModel
 */
public class JListModelDemo extends JListDemo {

	JListModelDemo(String s) {
		super(s);
		ListModel lm = new StaticListModel();
		list.setModel(lm);
	}

	public static void main(String[] s) {
		JListModelDemo l = new JListModelDemo("ListModel");
		l.pack();
		l.setVisible(true);
	}

	class StaticListModel implements ListModel {
		private final Object[] data = {
			"Hello",
			new Object(),
			new java.util.Date(),
			this,
			};

		public Object getElementAt(int index) {
			return data[index];
		}

		public int getSize() {
			return data.length;
		}

		public void addListDataListener(ListDataListener ldl) {
			// since the list never changes, we don't need this :-)
		}

		public void removeListDataListener(ListDataListener ldl) {
			// since the list never changes, we don't need this :-)
		}
	}
}
