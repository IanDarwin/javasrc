package strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Undent - remove leading spaces
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Undent {
	/** the maximum number of spaces to remove. */
	protected int nSpaces;

	Undent(int n) {
		nSpaces = n;
	}

    public static void main(String[] av) {
        Undent c = new Undent(5);
        switch(av.length) {
            case 0: c.process(new BufferedReader(
                        new InputStreamReader(System.in))); break;
            default:
		for (int i=0; i<av.length; i++)
			try {
				c.process(new BufferedReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
        }
    }

    /** print one file, given an open BufferedReader */
    public void process(BufferedReader is) {
        try {
            String inputLine;

			// BEGIN main
            while ((inputLine = is.readLine()) != null) {
				int toRemove = 0;
				for (int i=0; i<nSpaces && i < inputLine.length() && 
				Character.isWhitespace(inputLine.charAt(i)); i++)
						++toRemove;
                System.out.println(inputLine.substring(toRemove));
            }
			// END main
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
