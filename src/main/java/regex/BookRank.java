import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.darwinsys.io.FileIO;
import com.darwinsys.util.FileProperties;

/** Graph of a book's sales rank on a given bookshop site.
 * @author Ian F. Darwin, http://www.darwinsys.com/, Java Cookbook author,
 *	originally translated fairly literally from Perl into Java.
 * @author Patrick Killelea <p@patrick.net>: original Perl version,
 *	from the 2nd edition of his book "Web Performance Tuning".
 * @version $Id$
 */
public class BookRank {
	public final static String DATA_FILE = "book.sales";
	public final static String GRAPH_FILE = "book.png";

	/** Grab the sales rank off the web page and log it. */
	public static void main(String[] args) throws Exception {

		Properties p = new FileProperties(
			args.length == 0 ? "bookrank.properties" : args[1]);
		String title = p.getProperty("title", "NO TITLE IN PROPERTIES");
		// The url must have the "isbn=" at the very end, or otherwise
		// be amenable to being string-catted to, like the default.
		String url = p.getProperty("url", "http://test.ing/test.cgi?isbn=");
		// The 10-digit ISBN for the book.
		String isbn  = p.getProperty("isbn", "0000000000");
		// The RE pattern (MUST have ONE capture group for the number)
		String pattern = p.getProperty("pattern", "Rank: (\\d+)");

		// Looking for something like this in the input:
		//	 <b>QuickBookShop.web Sales Rank: </b>
		//	 26,252
		//	 </font><br>

		Pattern r = Pattern.compile(pattern);

		// Open the URL and get a Reader from it.
		BufferedReader is = new BufferedReader(new InputStreamReader(
			new URL(url + isbn).openStream()));
		// Read the URL looking for the rank information, as
		// a single long string, so can match RE across multi-lines.
		String input = FileIO.readerToString(is);
		// System.out.println(input);

		// If found, append to sales data file.
		Matcher m = r.matcher(input);
		if (m.find()) {
			PrintWriter pw = new PrintWriter(
				new FileWriter(DATA_FILE, true));
			String date = // `date +'%m %d %H %M %S %Y'`;
				new SimpleDateFormat("MM dd hh mm ss yyyy ").
				format(new Date());
			// Paren 1 is the digits (and maybe ','s) that matched; remove comma
			Matcher noComma = Pattern.compile(",").matcher(m.group(1));
			pw.println(date + noComma.replaceAll(""));
			pw.close();
		} else {
			System.err.println("WARNING: pattern `" + pattern +
				"' did not match in `" + url + isbn + "'!");
		}

		// Whether current data found or not, draw the graph, using 
		// external plotting program against all historical data.
		// Could use gnuplot, R, any other math/graph program.
		// Better yet: use one of the Java plotting APIs.

		String gnuplot_cmd = 
			"set term png\n" + 
			"set output \"" + GRAPH_FILE + "\"\n" +
			"set xdata time\n" +
			"set ylabel \"Book sales rank\"\n" +
			"set bmargin 3\n" +
			"set logscale y\n" +
			"set yrange [1:60000] reverse\n" +
			"set timefmt \"%m %d %H %M %S %Y\"\n" +
			"plot \"" + DATA_FILE + 
				"\" using 1:7 title \"" + title + "\" with lines\n" 
		;

		Process proc = Runtime.getRuntime().exec("/usr/local/bin/gnuplot");
		PrintWriter gp = new PrintWriter(proc.getOutputStream());
		gp.print(gnuplot_cmd);
		gp.close();
	}
}
