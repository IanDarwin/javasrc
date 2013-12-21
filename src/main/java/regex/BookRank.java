package regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Graph of a book's sales rank on a given bookshop site.
 * @author Ian F. Darwin, http://www.darwinsys.com/, Java Cookbook author,
 *	originally translated fairly literally from Perl into Java,
 *	and rewritten several times since.
 * @author Patrick Killelea <p@patrick.net>: original Perl version,
 *	from the 2nd edition of his book "Web Performance Tuning".
 */
// BEGIN main
public class BookRank {
	public final static String DATA_FILE = "book.sales";
	public final static String GRAPH_FILE = "book.png";
	public final static String PLOTTER_PROG = "/usr/local/bin/gnuplot";

	final static String isbn = "0596007019"; 
	final static String title = "Java Cookbook";
	
	/** Grab the sales rank off the web page and log it. */
	public static void main(String[] args) throws Exception {

		Properties p = new Properties();
		p.load(new FileInputStream(
			args.length == 0 ? "bookrank.properties" : args[1]));
		String title = p.getProperty("title", "NO TITLE IN PROPERTIES");
		// The url must have the "isbn=" at the very end, or otherwise
		// be amenable to being string-catted to, like the default.
		String url = p.getProperty("url", "http://test.ing/test.cgi?isbn=");
		// The 10-digit ISBN for the book.
		String isbn  = p.getProperty("isbn", "0000000000");
		// The RE pattern (MUST have ONE capture group for the number)
		String pattern = p.getProperty("pattern", "Rank: (\\d+)");

		int rank = getBookRank(isbn);

		System.out.println("Rank is " + rank);

		// Now try to draw the graph, using external
		// plotting program against all historical data.
		// Could use gnuplot, R, any other math/graph program.
		// Better yet: use one of the Java plotting APIs.

		PrintWriter pw = new PrintWriter(
			new FileWriter(DATA_FILE, true));
		String date = new SimpleDateFormat("MM dd hh mm ss yyyy ").
			format(new Date());
		pw.println(date + " " + rank);
		pw.close();

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

		if (!new File(PLOTTER_PROG).exists()) {
			System.out.println("Plotting software not installed");
			return;
		}
		Process proc = Runtime.getRuntime().exec(PLOTTER_PROG);
		PrintWriter gp = new PrintWriter(proc.getOutputStream());
		gp.print(gnuplot_cmd);
		gp.close();
	}

	/**
	 * Look for something like this in the HTML input:
	 *	 <b>Sales Rank:</b> 
	 *	 #26,252
	 * 	 </font><br>
	 * @throws IOException 
	 * @throws IOException 
	 */
	public static int getBookRank(String isbn) throws IOException {

		// The RE pattern - digits and commas allowed
		final String pattern = "Rank:</b> #([\\d,]+)";
		final Pattern r = Pattern.compile(pattern);

		// The url -- must have the "isbn=" at the very end, or otherwise
		// be amenable to being appended to.
		final String url = "http://www.amazon.com/exec/obidos/ASIN/" + isbn;

		// Open the URL and get a Reader from it.
		final BufferedReader is = new BufferedReader(new InputStreamReader(
			new URL(url).openStream()));

		// Read the URL looking for the rank information, as
		// a single long string, so can match RE across multi-lines.
		final String input = readerToString(is);

		// If found, append to sales data file.
		Matcher m = r.matcher(input);
		if (m.find()) {
			// Paren 1 is the digits (and maybe ','s) that matched; remove comma
			return Integer.parseInt(m.group(1).replace(",",""));
		} else {
			throw new RuntimeException(
				"Pattern not matched in `" + url + "'!");
		}
	}

	private static String readerToString(BufferedReader is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = is.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}
// END main
