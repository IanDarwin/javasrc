import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/** CookieCutter - program to show, and let you winnow, your Cookie collection.
 */
public class CookieCutter {
	protected JFrame f;
	protected Vector cookies;
	protected MyTableModel tModel;
	protected JTable table;
	protected JButton editButton, delButton, saveButton, exitButton;
	protected CookieDialog cd;

	public static void main(String[] args) throws FileNotFoundException, IOException {

		new CookieCutter();
	}

	CookieCutter() throws FileNotFoundException, IOException {

		JPanel bottomPanel;

		cookies = new CookieIO().read("cookies.txt");
		// Collections.sort(cookies);

		f = new JFrame("CookieCutter");
		Container cp = f.getContentPane();

		tModel = new MyTableModel();
		table = new JTable(tModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		cp.add(new JScrollPane(table), BorderLayout.CENTER);

		bottomPanel = new JPanel();
		bottomPanel.add(editButton = new JButton("Edit"));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cd == null) {
					cd = new CookieDialog(f, "CookieCutter Edit");
				}

				// XXX int i = // GET THE ROW
				cd.setCookie((Cookie) cookies.elementAt(0));
				cd.setVisible(true);
				// cookies.set( ROW, cd.getCookie());
			}
		});
		bottomPanel.add(delButton = new JButton("Delete"));
		bottomPanel.add(saveButton = new JButton("Save"));
		bottomPanel.add(exitButton = new JButton("Exit"));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cp.add(bottomPanel, BorderLayout.SOUTH);

		// f.pack();
		f.setSize(500, 400);

		f.setVisible(true);
	}

	class MyTableModel extends AbstractTableModel {

		/** Returns the number of items in the list. */
		public int getRowCount()  {
			return cookies.size();
		}

		/** Return the width of the table */
		public int getColumnCount() {
			return 7;
		}

		/** Get the name of a given column */
		public String getColumnName(int i) {
			switch(i) {
			case 0:	return "URL";
			case 1:	return "relURL";
			case 2:	return "name";
			case 3:	return "value";
			case 4:	return "expiry";
			case 5:	return "fromJS";
			case 6:	return "fromSSL";
			default: return "??";
			}
		}

		/** Returns a data value for the cell at columnIndex and rowIndex.
		 * Maintains a one-element cache
		 */
		public Object getValueAt(int row, int col)  {
			Cookie c = (Cookie) cookies.elementAt(row);
			switch (col) {
			case 0: return c.url;
			case 1: return c.relURL;
			case 2: return c.name;
			case 3: return c.value;
			case 4: return Long.toString(c.expDate);
			case 5: return new Boolean(c.fromJavaScript);
			case 6: return new Boolean(c.overSSL);
			default: return null;
			}
		}

		/** Set a value in a cell. Uses same cache as getValue. */
		public void setValueAt(Object val, int row, int col)  {
			Cookie c = (Cookie) cookies.elementAt(row);
			switch (col) {
			case 0: c.url = val.toString();
			case 1: c.url = val.toString();
			default: c.url = val.toString();
			}
		}

		/** All cells are editable. */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
	}
}
