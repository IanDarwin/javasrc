package gui.arraylisttable;

import javax.swing.*;
import java.util.*;

import com.darwinsys.swingui.ArrayListTableModel;
import com.darwinsys.swingui.ArrayListTablePanel;

/** Simple demo of the com.darwinsys.swingui.ArrayListTable{Model,Panel}.
 * The "data" is an ArrayList made from the System Properties
 */
public class ArrayListTableSimple extends JFrame {

	/** inner class for TableModel */
	class Model extends ArrayListTableModel {

		/** This defines the order of the columns. Must agree with columnClasses */
		protected String[] myColumnNames = { "Name", "Value" };
	
		protected Class[] myColumnClasses = { String.class, String.class };

		/** Return the width of the table */
		public int getColumnCount() { return 2; }

		/** Construct a Model given the ArrayList */
		public Model(ArrayList m) {
			super(m);
			columnNames = myColumnNames;
			columnClasses = myColumnClasses;
		}

		/** Returns a data value for the cell at row, col  */
		public Object getValueAt(int row, int col)  {

			ArrayListTableDatum current = (ArrayListTableDatum)getCached(row);
			switch(col) {
				case 0: return current.name;
				case 1: return current.value;
				default: 
					System.out.println("ERROR getValueAt(" + row + "," + col + "); invalid");
					return null;
			}
		}

		/** Set a data value for the cell at row, col */
		public void setValueAt(Object val, int row, int col)  {

			ArrayListTableDatum current = (ArrayListTableDatum)getCached(row);
			switch(col) {
				case 0: current.name = (String)val;
				case 1: current.value = (String)val;
			}
		}
	}

	/** Construct the main program's GUI */
	public ArrayListTableSimple() {
		super("ArrayListTableSimple");

		// Create empty ArrayList
		ArrayList data = new ArrayList();

		// Get the System Properties
		Properties p = System.getProperties();

		// Get an Iterator for the sorted set of keys in p
		Iterator it = new TreeMap(p).keySet().iterator();

		// Copy them into the ArrayList
		while (it.hasNext()) {
			String key = (String)it.next();
			String val = p.getProperty(key);
			data.add(new ArrayListTableDatum(key, val));
		}

		// Make the Model and View
		Model m = new Model(data);
		JPanel v = new ArrayListTablePanel(ArrayListTableDatum.class, data, m);

		// Make it show up in this JFrame
		setContentPane(new JScrollPane(v));
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ArrayListTableSimple().setVisible(true);
	}
}
