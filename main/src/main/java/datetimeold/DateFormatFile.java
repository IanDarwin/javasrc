package datetimeold;

import java.text.*;
import java.util.*;
import java.io.*;

/** Create SimpleDateFormats from a string read from a file */
public class DateFormatFile {
	/** Today's Date */
	Date dNow = new Date();

    public static void main(String[] args) throws IOException {
		DateFormatFile df = new DateFormatFile();
		BufferedReader is = 
			new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = is.readLine()) != null) {
			df.process(line);
		}
	}

	/** Use a SimpleDateFormat - based on arg - to print the date your way. */
	protected void process(String arg) {	
		SimpleDateFormat formatter = new SimpleDateFormat(arg);
		System.out.println("FORMAT: " + arg);
		System.out.println("RESULT: " + formatter.format(dNow));
		System.out.println();
	}
}
