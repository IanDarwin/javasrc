import java.io.*;

/** CheckSum - remove leading spaces
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */

public class CheckSum {
    public static void main(String[] av) {
        CheckSum c = new CheckSum();
		int sum = 0;
        if (av.length == 0)
            sum = c.process(new BufferedReader(
					new InputStreamReader(System.in)));
        else for (int i=0; i<av.length; i++)
			try {
				sum += c.process(new BufferedReader(new FileReader(av[i])));
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}
		System.out.println(sum);
    }


    /** CheckSum one file, given an open BufferedReader.
	 */
    public int process(BufferedReader is) {
		//+
		int sum = 0;
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
				int i;
				for (i=0; i<inputLine.length(); i++) {
					sum += inputLine.charAt(i);
				}
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
		}
		return sum;
    }
}
