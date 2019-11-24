package email;

import java.io.*;

/**
 * Read a file return mail headers one at a time.
 */
public class ReadHeaders {
	protected BufferedReader is;

	/** Construct */
	public ReadHeaders(BufferedReader f) {
		is = f;
	}

	/** Main program showing how to use it */
    public static void main(String[] args) {
        switch(args.length) {
            case 0: 
				ReadHeaders r = new ReadHeaders(
					new BufferedReader(
                        new InputStreamReader(System.in))); 
				printit(r);
				break;
            default:
				for (String arg : args) {
                    try {
						ReadHeaders rr = new ReadHeaders(
							new BufferedReader(new FileReader(arg)));
						printit(rr);
                    } catch (FileNotFoundException e) {
                        System.err.println(e);
                    }
				}
				break;
        }
    }

	/** Simple demo showing how to use it */
	public static void printit(ReadHeaders r) {
		while (r.more())  {
			Header h = r.next();
			System.out.println(h.type + " = " + h.text);
		}
	}

	String inputLine;

    /** check to see if more headers available */
    public boolean more() {
		if (is == null)
			return false;
        try {

            inputLine = is.readLine();
			if (inputLine == null || inputLine.length() == 0) {
				is.close();
				is = null;
			}
        } catch (IOException e) {
            System.out.println("IOException: " + e);
			is = null;
			return false;
        }
		return true;
    }

	public Header next() {
		int pos = inputLine.indexOf(":");
		if (pos < 0)
			return new Header("??", inputLine);
		return new Header(
			inputLine.substring(0, pos),
			inputLine.substring(pos + 1));
	}
}

