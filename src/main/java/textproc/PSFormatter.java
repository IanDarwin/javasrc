import java.io.*;

/** Text to PS */
public class tops {
	protected BufferedReader br;
	protected int pageNum;
	protected int curX, curY;
	protected int lineNum;
	protected int tabPos = 0;

	public static void main(String[] av) throws IOException {

		new tops(av[0]).process();
	}

	public tops(String fileName) throws IOException {
		br = new BufferedReader(new FileReader(fileName));
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
			tabPos = 0;
			for (int i=0; i<line.length() &&
					line.charAt(i)=='\t'; i++)
				tabPos++;
			doLine(line);
		}
	}

	protected void startPage() {
		if (pageNum++ > 0)
			println("showpage");
		pageNum = lineNum = 0;
		moveTo(0, 700);
	}

	protected void doLine(String line) {
		String l = line.trim().substring(tabPos==0?0:tabPos-1);
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
		println("/Helvetica-Bold findfont 24 scalefont setfont ");
	}
}
