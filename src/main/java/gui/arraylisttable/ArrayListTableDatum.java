package gui.arraylisttable;

/** Class to hold a name and a value from a Properties; the
 * ArrayList contains one of these per Properties entry.
 * Needs to be a non-inner class only to allow the Panel
 * to constructs instances of it.
 */
public class ArrayListTableDatum {
	String name;
	String value;
	/** Constructor used below */
	public ArrayListTableDatum(String n, String v) {
		name = n; value = v;
	}
	/** public no-arg constructor, req'd for Add operation */
	public ArrayListTableDatum() { }
}
