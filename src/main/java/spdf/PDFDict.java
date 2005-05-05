package spdf;

import java.util.*;

/** A PDFDict ias a PDFObject that is all, or mostly, a Dictionary.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public abstract class PDFDict extends PDFObject {
	/** The Dictionary is a HashTable. Put the keys without a 
	 * leading slash, since they always have it. Values can
	 * be /names, (strings), or whatever.
	 */
	protected Hashtable<String,String> dict;

	PDFDict(PDF m) {
		super(m);
		dict = new Hashtable<String,String>();
	}

	/** Write the object to the Output Writer. The default implementation
	 * of this method in PDFDict just calls startObj, printDict, and endObj.
	 */
	protected void print() {
		startObj();
		printDict();
		endObj();
	}

	protected void startObj() {
		// Record the starting position of this Obj in the xref table
		master.addXref();

		// Print out e.g., "42 0 obj"
		master.print(master.currObj++);
	 	master.print(" 0 obj");
		master.println();
	}

	protected void endObj() {
		master.println("endobj");
	}

	protected void printDict() {
		master.println("<<");
		Enumeration enumeration = dict.keys();
		while (enumeration.hasMoreElements()) {
			master.print("\t/");
			String key = (String)enumeration.nextElement();
			master.print(key);
			master.print(" ");
			master.print(dict.get(key));
			master.println();
		}
		master.println(">>");
	}
}
