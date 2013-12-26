package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/** detab- replace blanks by tabs and blanks.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class DeTab {
	Tabs ts;
	
	public static void main(String[] argv) throws IOException {
		DeTab dt = new DeTab(8);
		dt.detab(new BufferedReader(new InputStreamReader(System.in)),
				new PrintWriter(System.out));
	}

	public DeTab(int n) {
		ts = new Tabs(n);
	}
	public DeTab() {
		ts = new Tabs();
	}

	/** detab one file (replace tabs with spaces)
	 * @param is - the file to be processed
	 * @param out - the updated file
	 */
	public void detab(BufferedReader is, PrintWriter out) throws IOException {
		String line;
		while ((line = is.readLine()) != null) {
			out.println(detabLine(line));
		}
	}
	
	/** detab one line (replace tabs with spaces)
	 * @param line - the line to be processed
	 * @return the updated line
	 */
	public String detabLine(String line) {
		char c;
		int col;
		StringBuffer sb = new StringBuffer();
		col = 0;
		for (int i = 0; i < line.length(); i++) {
			// Either ordinary character or tab.
			if ((c = line.charAt(i)) != '\t') {
				sb.append(c); // Ordinary
				++col;
				continue;
			}
			do { // Tab, expand it, must put >=1 space
				sb.append(' ');
			} while (!ts.isTabStop(++col));
		}
		return sb.toString();
	}
}

// END main
