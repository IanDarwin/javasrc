import java.io.*;
import com.darwinsys.util.FileIO;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/** Graph of a book's sales rank on a given bookshop site.
 * @author Ian F. Darwin, ian@darwinsys.com, Java Cookbook author,
 *	translated fairly literally from Perl into Java.
 * @author Patrick Killelea <p@patrick.net>: original Perl version,
 *	from the 2nd edition of his book "Web Performance Tuning".
 * @version $Id$
 */

public class BookRank {
	public final static String ISBN = "0937175307";
	public final static String DATA_FILE = "lint.sales";
	public final static String GRAPH_FILE = "lint.png";
	public final static String TITLE = "Checking C Prog w/ Lint";
	public final static String QUERY =
			"http://www.quickbookshops.web/cgi-bin/search?isbn=";

	/** Grab the sales rank off the web page and log it. */
	public static void main(String[] args) throws Exception {

		// Looking for something like this in the input:
		//	 <b>QuickBookShop.web Sales Rank: </b>
		//	 26,252
		//	 </font><br>

		// Patrick Killelea's original RE formulation : match number with
		// comma included, just print minus ",". Loses if fall below 100,000.
		RE r = new RE(" Sales Rank: </b>\\s*(\\d*),*(\\d+)\\s");
		// Java: should use "[\d,]+" to extract the number and 
		// NumberFormat.getInstance().parse() to convert to int.

		// Open the URL and get a Reader from it.
		BufferedReader is = new BufferedReader(new InputStreamReader(
			new URL(QUERY + ISBN).openStream()));
		// Read the URL looking for the rank information, as
		// a single long string, so can match RE across multi-lines.
		String input = FileIO.readerToString(is);

		// If found, append to sales data file.
		if (r.match(input)) {
			PrintWriter FH = new PrintWriter(
				new FileWriter(DATA_FILE, true));
			String date = // `date +'%m %d %H %M %S %Y'`;
				new SimpleDateFormat("MM dd hh mm ss yyyy ").
				format(new Date());
			// Paren 1 is the optional thousands; paren 2 is low 3 digits.
			FH.println(date + r.getParen(1) + r.getParen(2));
			FH.close();
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
				"\" using 1:7 title \"" + TITLE + "\" with lines\n" 
		;

		Process p = Runtime.getRuntime().exec("/usr/local/bin/gnuplot");
		PrintWriter gp = new PrintWriter(p.getOutputStream());
		gp.print(gnuplot_cmd);
		gp.close();
	}
}
