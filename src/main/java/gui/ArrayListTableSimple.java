package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.darwinsys.swingui.ArrayListTableModel;
import com.darwinsys.swingui.ArrayListTablePanel;
import com.darwinsys.util.ArrayListTableDatum;

/** Simple demo of the com.darwinsys.swingui.ArrayListTable{Model,Panel}.
 * The "data" is an ArrayList made from the System Properties
 */
public class ArrayListTableSimple extends JFrame {

	private static final long serialVersionUID = 7090960394360929963L;

	/** inner class for TableModel */
	static class Model extends ArrayListTableModel {

		private static final long serialVersionUID = -464608588722768902L;

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
				case 0: return current.getName();
				case 1: return current.getValue();
				default: 
					System.out.println("ERROR getValueAt(" + row + "," + col + "); invalid");
					return null;
			}
		}

		/** Set a data value for the cell at row, col */
		public void setValueAt(Object val, int row, int col)  {

			ArrayListTableDatum current = (ArrayListTableDatum)getCached(row);
			switch(col) {
				case 0: current.setName(val.toString()); break;
				case 1: current.setValue(val.toString()); break;
				default: throw new IllegalStateException("Invalid column number " + col);
			}
		}
	}

	/** Construct the main program's GUI */
	public ArrayListTableSimple() {
		super("ArrayListTableSimple");

		// Create empty ArrayList
		List<ArrayListTableDatum> data = new ArrayList<ArrayListTableDatum>();

		// Get the System Properties
		Properties p = System.getProperties();

		// Get an Iterator for the sorted set of keys in p
		for (String key : p.keySet()) {

			// Copy them into the ArrayList as ArrayListTableDatum entries.
			String val = p.getProperty(key);
			data.add(new ArrayListTableDatum(key, val));
		}

		// Make the Model and View
		Model m = new Model(data);
		@SuppressWarnings("unchecked")		
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
