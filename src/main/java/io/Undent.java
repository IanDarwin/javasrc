import java.io.*;

/** Undent - remove leading spaces
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */

public class Undent {
	//+
	/** the default number of spaces to remove. */
	int nSpaces = 2;
	//-

    public static void main(String[] av) {
        Undent c = new Undent();
        if (av.length == 0)
            c.process(new BufferedReader(
				new InputStreamReader(System.in)));
		else for (int i=0; i<av.length; i++) {
			try {
				c.process(new BufferedReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
        }
    }


    /** undent one file, given an open BufferedReader.
	 * Undent by removing UP TO "nSpaces" leading spaces.
	 */
    public void process(BufferedReader is) {
		//+
		// GRRR THIS DOES NOT QUITE WORK - FIX -- Ian
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
				int i;
				for (i=0; i<nSpaces; i++) {
					if (!Character.isWhitespace(inputLine.charAt(i)))
						break;
				}
                System.out.println(inputLine.substring(i));
            }
            is.close();
		//-
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
	//-
}
