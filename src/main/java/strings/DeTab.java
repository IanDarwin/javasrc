import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/** detab- replace blanks by tabs and blanks.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class DeTab extends Tabs {

	public static void main(String[] argv) throws IOException {
		DeTab dt = new DeTab(8);
		dt.detab(new BufferedReader(new InputStreamReader(System.in)),
				new PrintWriter(System.out));
	}

	public DeTab(int n) {
		super(n);
	}

	public void detab(BufferedReader is, PrintWriter out) throws IOException {
		String line;
		char c;
		int col;
		while ((line = is.readLine()) != null) {
			col = 0;
			for (int i=0; i<line.length(); i++) {
				// Either ordinary character or tab.
				if ((c=line.charAt(i)) != '\t') {
					System.out.print(c); // Ordinary
					++col;
					continue;
				}
				do { // Tab, expand it, must put >=1 space
					out.print(' ');
				} while (!isTabStop(++col));
			}
			System.out.println();
		}
	}
}
