package spdf;

import java.util.ArrayList;
import java.util.List;

/** Represent one Page of a PDF file. */
public class Page extends PDFDict {
	protected List<PDFObject> objects = new ArrayList<PDFObject>();

	public Page(PDF m) {
		super(m);
		dict.put("Type", "/Page");
		dict.put("Parent", "4 0 R");
		dict.put("Resources", "<< /Font << /F1 8 0 R >> /ProcSet 7 0 R >>");
		dict.put("MediaBox", "[0 0 612 792]");
		dict.put("Contents", "6 0 R");
		
	}

	public void add(PDFObject po) {
		objects.add(po);
	}

	/** Print all the objects on the page.
	 * For now, just print all the Text objects, as one Stream.
	 */
	protected void print() {
		// Print the Page object
		super.print();

		// Now do the Text objects as one PDF obj
		master.addXref();
		startObj();

		StringBuffer sb = new StringBuffer();
		sb.append("BT\n");
		sb.append("/F1 12 Tf\n");

		for (int i=0; i<objects.size(); i++) {
			PDFObject po = (PDFObject)objects.get(i);
			if (po instanceof Text)
				((Text)po).print(sb);
			else if (po instanceof MoveTo)
				((MoveTo)po).print(sb);
			// else if (po instanceof Font)
			//	...
			else
				System.err.println("PDFPage: ignoring " + po);
		}
		sb.append("ET\n");

		master.println("<< /Length " + sb.length() + " >>");
		master.println("stream");
		master.print(sb);
		master.println("endstream");
		endObj();
	}
}
