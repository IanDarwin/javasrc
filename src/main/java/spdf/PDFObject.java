package spdf;

/** A PDFObject represents one node in the tree of a PDF file.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public abstract class PDFObject extends java.lang.Object {
	/** The containing PDF file */
	protected PDF master;

	PDFObject(PDF m) {
		master = m;
	}

	/** Write the object to the Output Writer */
	protected abstract void print();

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
}
