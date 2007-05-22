package gui;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/** Demonstrate Swing "JList" ListModel
 * @author Ian Darwin
 * @author Tweaked by Jonathan Fuerth of SQLPower.ca
 */
public class JListModelDemo extends JListDemo {

	JListModelDemo(String s) {
		super(s);
		ListModel lm = new StaticListModel();
		list.setModel(lm);
		list.setCellRenderer(new MyCellRenderer());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] s) {
		JListModelDemo l = new JListModelDemo("ListModel");
		l.pack();
		l.setVisible(true);
	}

	class MyCellRenderer extends JLabel implements ListCellRenderer {

		/* Get the Renderer for a given List Cell.
		 * This is the only method defined by ListCellRenderer.
		 * If the object is already a component, keep it, else
		 * toString it and wrap it in a JLabel.
		 * Reconfigure the Component each time we're called
		 * to accord for whether it's selected or not.
		 */
		public Component getListCellRendererComponent
			(
			 JList list,
			 Object value,            // value to display
			 int index,               // cell index
			 boolean isSelected,      // is the cell selected
			 boolean cellHasFocus)    // the list and the cell have the focus
		{
			Component c = null;
			if (value == null) {
				c = new JLabel("(null)");
			} else if (value instanceof Component) {
				c = (Component) value;
			} else {
				c = new JLabel(value.toString());
			}
			
			if ( isSelected ) {
				c.setBackground(list.getSelectionBackground());
				c.setForeground(list.getSelectionForeground());
			} else {
				c.setBackground(list.getBackground());
				c.setForeground(list.getForeground());
			}

			if ( c instanceof JComponent ) {
				((JComponent)c).setOpaque(true);
			}

			return c;
		}
	}

	class StaticListModel implements ListModel {
		private final Object[] data = {
			"Hello",
			new Object(),
			new java.util.Date(),
			new JLabel("Hello world!"),
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

