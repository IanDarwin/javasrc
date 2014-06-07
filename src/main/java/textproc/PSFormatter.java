package textproc;

import java.io.*;

/**
 * Text to Adobe PostScript
 */
// BEGIN main
public class PSFormatter {
	/** The current input source */
	protected BufferedReader br;
	/** The current page number */
	protected int pageNum;
	/** The current X and Y on the page */
	protected int curX, curY;
	/** The current line number on page */
	protected int lineNum;
	/** The current tab setting */
	protected int tabPos = 0;
	public static final int INCH = 72;	// PS constant: 72 pts/inch

	// Page parameters
	/** The left margin indent */
	protected int leftMargin = 50;
	/** The top of page indent */
	protected int topMargin = 750;
	/** The bottom of page indent */
	protected int botMargin = 50;

	// FORMATTING PARAMETERS
	protected int points = 12;
	protected int leading = 14;

	public static void main(String[] av) throws IOException {
		if (av.length == 0) 
			new PSFormatter(
				new InputStreamReader(System.in)).process();
		else for (int i = 0; i < av.length; i++) {
			new PSFormatter(av[i]).process();
		}
	}

	public PSFormatter(String fileName) throws IOException {
		br = new BufferedReader(new FileReader(fileName));
	}

	public PSFormatter(Reader in) throws IOException {
		if (in instanceof BufferedReader)
			br = (BufferedReader)in;
		else
			br = new BufferedReader(in);
	}

	/** Main processing of the current input source. */
	protected void process() throws IOException {

		String line;

		prologue();			// emit PostScript prologue, once.

		startPage();		// emit top-of-page (ending previous)

		while ((line = br.readLine()) != null) {
			if (line.startsWith("\f") || line.trim().equals(".bp")) {
				startPage();
				continue;
			}
			doLine(line);
		}

		// finish last page, if not already done.
		if (lineNum != 0)
			System.out.println("showpage");
	}

	/** Handle start of page details. */
	protected void startPage() {
		if (pageNum++ > 0)
			System.out.println("showpage");
		lineNum = 0;
		moveTo(leftMargin, topMargin);
	}

	/** Process one line from the current input */
	protected void doLine(String line) {
		tabPos = 0;
		// count leading (not imbedded) tabs.
		for (int i=0; i<line.length(); i++) {
			if (line.charAt(i)=='\t')
				tabPos++;
			else
				break;
		}
		String l = line.trim(); // removes spaces AND tabs
		if (l.length() == 0) {
			++lineNum;
			return;
		}
		moveTo(leftMargin + (tabPos * INCH),
			topMargin-(lineNum++ * leading));
		System.out.println('(' + toPSString(l)+ ") show");

		// If we just hit the bottom, start a new page
		if (curY <= botMargin)
			startPage();
	}

	/** Overly simplistic conversion to PS, e.g., breaks on "foo\)bar" */
	protected String toPSString(String o) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<o.length(); i++) {
			char c = o.charAt(i);
			switch(c) {
				case '(':	sb.append("\\("); break;
				case ')':	sb.append("\\)"); break;
				default:	sb.append(c); break;
			}
		}
		return sb.toString();
	}

	protected void moveTo(int x, int y) {
		curX = x;
		curY = y;
		System.out.println(x + " " + y + " " + "moveto");
	}

	void prologue() {
		System.out.println("%!PS-Adobe");
		System.out.println("/Courier findfont " + points + " scalefont setfont ");
	}
}
// END main
