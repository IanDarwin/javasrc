import java.io.*;

/** entab- replace blanks by tabs and blanks.
 * Transmuted from K&R software tools book into C.
 * Transmuted again, years later, into Java.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class EnTab extends Tabs {

	public static void main(String argv[]) throws IOException {
		EnTab et = new EnTab(8);
		et.entab(new BufferedReader(new InputStreamReader(System.in)));
	}

	public EnTab(int n) {
		super(n);
	}

	public void entab(BufferedReader is) throws IOException {
		inFile = is;
		String line;
		int c, col = 0, newcol;
		do {
			newcol = col;
			while ((c = getchar()) == ' ') {
				Debug.println("space", "Got space at " + col);
				newcol++;
				if (tabpos(newcol)) {
					Debug.println("tab", "Got a Tab Stop " + newcol);
					putchar('\t');
					col = newcol;
				}
			}
			while (col < newcol) {
				Debug.println("pad", "Padding space at " + col);
				putchar(' ');
				col++;
			}
			Debug.println("out", "End of loop, c is " + c);
			if (c != EOF) {
				putchar(c);
				col = (c == '\n' ? 1 : col + 1);
			}
		} while (c != EOF);
		System.out.flush();
	}

	protected BufferedReader inFile;
	public static int EOF = -1;

	protected int getchar() throws IOException {
		int ch = inFile.read();
		Debug.println("in", "Read c as " + (char)ch);
		return ch;
	}
	protected void putchar(int ch) {
		System.out.print((char)ch);
	}
}



