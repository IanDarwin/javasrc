import java.io.*;

/** GetMark -- get marked lines.
 * In this version, the marks are hard-coded; ideally they would come
 * from a FileProperties object.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */

public class GetMark {
	//+
	/** the default starting mark. */
	public final String startMark = "//+";
	/** the default ending mark. */
	public final String endMark = "//-";
	/** True if we are currently inside marks. */
	protected boolean printing = false;
	//-

    public static void main(String av[]) {
        GetMark c = new GetMark();
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


    /** Get Marked parts of one file, given an open BufferedReader.
	 */
    public void process(BufferedReader is) {
		//+
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
				if (inputLine.trim().equals(startMark)) {
					printing = true;
				} else if (inputLine.trim().equals(endMark)) {
					printing = false;
				} else if (printing)
					System.out.println(inputLine);
            }
            is.close();
		//-
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
	//-
}
