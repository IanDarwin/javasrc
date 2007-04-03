package gui;

import java.util.Arrays;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class JTableSysProps {
	public static void main(String[] args) {
		JFrame jf = new JFrame("Tables");
		jf.add(new JScrollPane(new JTable(new SystemPropertiesTableModel())));
		jf.setSize(500, 400);
		jf.setVisible(true);
	}

	@SuppressWarnings("serial")
	private static class SystemPropertiesTableModel extends AbstractTableModel {
        private Properties props = System.getProperties();
        private String[] keys;

        SystemPropertiesTableModel() {
            keys = props.keySet().toArray(new String[0]);
            Arrays.sort(keys);
        }

        public int getRowCount() {
            return keys.length;
        }

        public int getColumnCount() {
            return 2;
        }

        public Object getValueAt(int row, int column) {
            switch(column) {
            case 0: return keys[row];
            case 1: return props.get(keys[row]);
            default: throw new IllegalArgumentException("Column count");
            }
        }

        @Override
        public String getColumnName(int column) {
            switch(column) {
            case 0: return "Property";
            case 1: return "Value";
            default: throw new IllegalArgumentException("Column count");
            }
        }
    }
}
