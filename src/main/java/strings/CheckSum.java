package strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CheckSum - print a checksum of a file
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class CheckSum {
    public static void main(String[] args) {
        int sum = 0;
        if (args.length == 0) {
            sum = CheckSum.process(new BufferedReader(
            new InputStreamReader(System.in)));
        } else for (String arg : args) {
            try {
                sum += CheckSum.process(
					new BufferedReader(new FileReader(arg)));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found: " + arg, e);
            }
		}
        System.out.println(sum);
    }

    // BEGIN main
    /** CheckSum one text file, given an open BufferedReader.
     * Checksumm does not include line endings, so will give the
     * same value for given text on any platform. Do not use
     * on binary files!
     */
    public static int process(BufferedReader is) {
        int sum = 0;
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                int i;
                for (i=0; i<inputLine.length(); i++) {
                    sum += inputLine.charAt(i);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e);
        }
        return sum;
    }
// END main
}
