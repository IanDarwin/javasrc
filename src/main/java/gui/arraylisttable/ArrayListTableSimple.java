package gui.arraylisttable;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.darwinsys.swingui.ArrayListTableModel;
import com.darwinsys.swingui.ArrayListTablePanel;

/** Simple demo of the com.darwinsys.swingui.ArrayListTable{Model,Panel}.
 * The "data" is an ArrayList made from the System Properties
 */
public class ArrayListTableSimple extends JFrame {

	private static final long serialVersionUID = -7452964295956088160L;

	/** inner class for TableModel */
	class Model extends ArrayListTableModel {

		private static final long serialVersionUID = 6213049956185726908L;

		/** This defines the order of the columns. Must agree with columnClasses */
		protected String[] myColumnNames = { "Name", "Value" };
	
		protected Class<?>[] myColumnClasses = { String.class, String.class };

		/** Return the width of the table */
		public int getColumnCount() { return 2; }

		/** Construct a Model given the ArrayList */
		public Model(List<ArrayListTableDatum> m) {
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
		ArrayList<ArrayListTableDatum> data = new ArrayList<ArrayListTableDatum>();

		// Get the System Properties
		Properties p = System.getProperties();

		// Get an Iterator for the sorted set of keys in p
		final Set keySet = new TreeMap(p).keySet();
		for (Object k : keySet) {
			String key = (String)k;
			// Copy them into the ArrayList
			String val = p.getProperty(key);
			data.add(new ArrayListTableDatum(key, val));
		}

		// Make the Model and View
		Model m = new Model(data);
		JPanel v = new ArrayListTablePanel<ArrayListTableDatum>(ArrayListTableDatum.class, data, m);

		// Make it show up in this JFrame
		setContentPane(new JScrollPane(v));
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ArrayListTableSimple().setVisible(true);
	}
}
