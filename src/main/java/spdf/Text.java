package spdf;

/** Represent one Text object in a PDF file. */
public class Text extends PDFObject {
	protected int x, y;
	protected String text;

	public Text(PDF m, String s) {
		super(m);
		text = s;
	}

	public void print() {
		throw new IllegalStateException("print() called on a Text obj");
	}

	public void print(StringBuffer sb) {
		sb.append("0 -18 Td (");
		sb.append(text);	// TODO must substitute escaped characters
		sb.append(") Tj\n");
	}
}
