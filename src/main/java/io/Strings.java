package io;

import java.io.*;

/** Strings -- extract printable strings from binary file
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */

public class Strings {

	protected int minLength = 4;

	/** Return true if the character is printable IN ASCII.
	 * Not using Character.isLetterOrDigit(); applies to all unicode ranges
	 */
	protected boolean isStringChar(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return true;
		if (ch >= 'A' && ch <= 'Z')
			return true;
		if (ch >= '0' && ch <= '9')
			return true;
		switch(ch) {
			case '/': case '-': case ':':
			case '.': case ',': case '_':
			case '$': case '%': case '\'':
			case '(': case ')': case '[': case ']': case '<': case '>':
				return true;
		}
		return false;
	}

    /** Process one file */
    protected void process(String fileName, InputStream inStream) {
        try {
			int i;
            char ch;

			// This line alone cuts the runtime by about 66% on large files.
			BufferedInputStream is = new BufferedInputStream(inStream);

			StringBuffer sb = new StringBuffer();

			// Read a byte, cast it to char, check if part of printable string.
            while ((i = is.read()) != -1) {
				ch = (char)i;
				if (isStringChar(ch) || (sb.length()>0 && ch==' '))
					// If so, build up string.
					sb.append(ch);
				else {
					// if not, see if anything to output.
					if (sb.length() == 0)
						continue;
					if (sb.length() >= minLength) {
						report(fileName, sb);
					}
					sb.setLength(0);
				}
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

	/** This simple main program looks after filenames and
	 * opening files and such like for you.
	 */
    public static void main(String[] av) {
        Strings o = new Strings();
        if (av.length == 0) {
            o.process("standard input", System.in);
		} else {
			for (int i=0; i<av.length; i++)
				try {
					o.process(av[i],
						new FileInputStream(av[i]));
				} catch (FileNotFoundException e) {
					System.err.println(e);
				}
        }
    }

	/** Output a match. Made a separate method for use by subclassers. */
	protected void report(String fName, StringBuffer theString) {
		System.out.println(fName + ": " + theString);
	}
}
