package graphics;

import java.awt.geom.Point2D;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** GraphReader is a helper class for Grapher, that reads various formats.
 * (At present, only a list of x,y points from a text file).
 */
public class GraphReader {

	/** Read data points from the named file. Each line has 
	 * an x and a y coordinate, resulting in a single Point2D.
	 * @throws IllegalArgumentException on bad or insufficient data
	 * @throws IOException if the file doesn't exist or is unreadable.
	 */
	public static List<Point2D> read(String fileName) throws IOException {
		return read(new FileReader(fileName), fileName);
	}
	
	/** Read data points from an opened Reader. Each line has 
	 * an x and a y coordinate, yielding a single Point2D.
	 * @throws IllegalArgumentException on bad or insufficient data
	 * @throws IOException if the file doesn't exist or is unreadable.
	 */
	public static List<Point2D> read(Reader reader, String fileName) throws IOException {
		LineNumberReader is = new LineNumberReader(reader);

		List<Point2D> data = new ArrayList<Point2D>();

		String txt;
	
		// Read the file a line at a time, parse it, save the data.
		while ((txt = is.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(txt);
			try {
				Point2D p = new Point2D.Double();
				p.setLocation(Double.parseDouble(st.nextToken()),
						Double.parseDouble(st.nextToken()));
				data.add(p);
			} catch(NumberFormatException nfe) {
				throw new IllegalArgumentException("Invalid number " + nfe + 
						" on line " + is.getLineNumber());
			}
		}

		if (data.size() < 2) {
			throw new IllegalArgumentException(
				"GraphReader.read: " + fileName + ": Not enough data points!");
		}
		return data;
	}
}
