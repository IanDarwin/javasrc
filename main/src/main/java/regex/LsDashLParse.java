package regex;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse Unix ls -l output, which supports variations like these:
 * (note that the columns do not necessarily line up, so you cannot
 * reliably use "column numbers" other than that the [-d]rwxrw-rw-
 * are at the beginning of the line).
 * drwxr-xr-x  2  123 wheel     1024 Dec  7 17:28 bin
 * drwxr-xr-x  2 root wheel     1024 Dec  7 17:28 bin
 * -r-xr-xr-x  1 root wheel    44848 Dec 18  2010 boot
 * -rw-r--r--  1 ian  users  8461181 Mar  2  2013 Metric/Breathing Underwater - Single/01 Breathing Underwater.m4a
 */
public class LsDashLParse {
	public static final String PATTERN =
	"[-d][rwxsSt-]{9}\\s+\\d+\\s+(\\d+|\\w+)\\s+(\\d+|\\w+)\\s+\\d+\\s+\\w+\\s+\\d+\\s+(\\d{4}|\\d\\d:\\d\\d)\\s(.*)";
	public static final Pattern patt = Pattern.compile(PATTERN);

	public static void main(String[] args) throws Exception {
		Matcher matcher = patt.matcher("");
                String line = null;
		BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));
                while ((line = is.readLine()) != null) {
                        matcher.reset(line);
                        if (matcher.find()) {
                                System.out.println("MATCH: " + line);
                        } else {
				System.out.println("FAIL:  " + line);
			}
                }
	}
}
