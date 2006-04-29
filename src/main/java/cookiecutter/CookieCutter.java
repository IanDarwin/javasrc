package cookiecutter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/** CookieCutter - program to show, and let you winnow, your Cookie collection.
 */
public class CookieCutter {
	/** Main data structure: a Vector of Cookie objects. */
	protected List<Cookie> cookies;
	protected JFrame f;
	protected MyTableModel tModel;
	protected JTable table;
	protected JButton editButton, delButton, saveButton, exitButton;
	protected CookieDialog cd;
	protected CookieAccessor accessor;
	protected final static String DEFAULT_FILENAME = "cookies.txt";
	protected String fileName = DEFAULT_FILENAME;

	public static void main(String[] args) throws 
		FileNotFoundException, IOException {

		new CookieCutter(args);
	}

	CookieCutter(String[] argv) throws FileNotFoundException, IOException {

		JPanel bottomPanel;
		fileName = argv.length == 1 ? argv[0] : DEFAULT_FILENAME;

		accessor = new CookieAccessor();
		cookies = accessor.read(fileName);
		Collections.sort(cookies);

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
				synchronized(cookies) {
					if (cd == null) {
						cd = new CookieDialog(f, "CookieCutter Edit");
					}
	
					int row = table.getSelectedRow();
					System.out.println("Selected row is " + row);
					cd.setCookie((Cookie) cookies.get(row));
					cd.setVisible(true);
					cookies.set(row, cd.getCookie());
				}
			}
		});

		bottomPanel.add(delButton = new JButton("Delete"));
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(cookies) {
				int row = table.getSelectedRow();
				cookies.remove(row);
				System.out.println("Removing row " + row);
				}
				table.repaint();
			}
		});
		bottomPanel.add(saveButton = new JButton("Save"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(f,
					"Please ensure that Netscape/Mozilla/Firefox is not running",
					"Caution",
					JOptionPane.QUESTION_MESSAGE);
				try {
					synchronized(cookies) {
						accessor.write(fileName, cookies);
					}
					JOptionPane.showMessageDialog(f,
							"Save complete",
							"Done", 
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(f,
						"Save did not complete!\n" + ex.toString(),
						"I/O Error", 
						JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});

		bottomPanel.add(exitButton = new JButton("Exit"));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(cookies) {
					System.exit(0);
				}
			}
		});
		cp.add(bottomPanel, BorderLayout.SOUTH);

		// f.pack();
		f.setSize(500, 400);

		f.setVisible(true);
	}

	class MyTableModel extends AbstractTableModel {
	    private static final long serialVersionUID = 3256721762652532789L;

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
			case 1:	return "Path";
			case 2:	return "Name";
			case 3:	return "Value";
			case 4:	return "Expiry";
			case 5:	return "Client?";
			case 6:	return "Secure?";
			default: return "??";
			}
		}

		/** Returns a data value for the cell at columnIndex and rowIndex.
		 * MUST BE IN SAME ORDER as setValueAt();
		 */
		public Object getValueAt(int row, int col)  {
			Cookie c = (Cookie) cookies.get(row);
			switch (col) {
			case 0: return c.getDomain();
			case 1: return c.getPath();
			case 2: return c.getName();
			case 3: return c.getValue();
			case 4: return Long.toString(c.getExpiry());
			case 5: return Boolean.valueOf(c.fromJavaScript);
			case 6: return Boolean.valueOf(c.getSecure());
			default: return null;
			}
		}

		/** Set a value in a cell. MUSE BE IN SAME ORDER AS getValueAt. */
		public void setValueAt(Object val, int row, int col)  {
			Cookie c = (Cookie) cookies.get(row);
			switch (col) {
			case 0: c.setDomain(val.toString()); break;
			case 1: c.setPath(val.toString()); break;
			case 2: c.name = val.toString(); break;
			case 3: c.setValue(val.toString()); break;
			case 4: c.setExpiry(Integer.parseInt(val.toString())); break;
			case 5: 
			case 6:
				System.out.println("setValueAt" + val.getClass() + "," + val);
				break;
			default: c.url = val.toString();
				break;
			}
		}

		/** All cells are editable. */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
	}
}
