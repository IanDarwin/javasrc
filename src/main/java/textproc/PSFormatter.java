import java.io.*;

/** Text to PS */
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

	protected void process() throws IOException {

		String line;

		prologue();

		startPage();

		while ((line = br.readLine()) != null) {
			if (line.trim().equals("%page")) {
				startPage();
				continue;
			}
			doLine(line);
		}
	}

	/** Handle start of page details. */
	protected void startPage() {
		if (pageNum++ > 0)
			println("showpage");
		pageNum = lineNum = 0;
		moveTo(0, 700);
	}

	/** Process one line from the current input */
	protected void doLine(String line) {
		tabPos = 0;
		for (int i=0; i<line.length() &&
				line.charAt(i)=='\t'; i++)
			tabPos++;
		String l = line.trim(); // removes spaces AND tabs
		if (l.length() == 0) {
			++lineNum;
			return;
		}
		moveTo(tabPos * 72, 700-(lineNum++ * 50));
		println('(' + toPSString(l)+ ") show");
	}

	protected String toPSString(String o) {
		StringBuffer sb = new StringBuffer();
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

	protected void println(String s) {
		System.out.println(s);
	}

	protected void moveTo(int x, int y) {
		curX = x;
		curY = y;
		println(x + " " + y + " " + "moveto");
	}

	void prologue() {
		println("%!PS-Adobe");
		println("/Courier findfont 12 scalefont setfont ");
	}
}
