import java.awt.Point;
import java.io.*;
import java.util.*;

/** GraphReader is a helper class for Grapher, that reads various formats.
 * (At present, only a list of x,y points from a text file).
 */
public class GraphReader {

	/** Read the data file named. Each line has an x and a y coordinate. */
	public static List read(String fileName) {
		LineNumberReader is = null;
		List data = new ArrayList();
		try {
			is = new LineNumberReader(new FileReader(fileName));

			String txt;
			// Read the file a line at a time, parse it, save the data.
			while ((txt = is.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(txt);
				try {
					Point p = new Point();
					p.setLocation(Float.parseFloat(st.nextToken()),
						Float.parseFloat(st.nextToken()));
					data.add(p);
				} catch(NumberFormatException nfe) {
					System.err.println("Invalid number on line " +
						is.getLineNumber());
				} // XXX catch out of range exception
			}
		} catch (FileNotFoundException e) {
			System.err.println("File " + fileName + " unreadable: " + e);
		} catch (IOException e) {
			System.err.println("I/O error on line " + is.getLineNumber());
		}
		if (data.size() < 2) {
			throw new IllegalArgumentException(
				"GraphReader.read: " + fileName + ": Not enough data points!");
		}
		return data;
	}
}
