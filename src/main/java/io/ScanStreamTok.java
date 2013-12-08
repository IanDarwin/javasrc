package io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

/**
 * ScanStreamTok - show scanning a file with StringTokenizer.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class ScanStreamTok {
	protected  StreamTokenizer tf;

	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			new ScanStreamTok(
				new InputStreamReader(System.in)).process();
		else 
			for (int i=0; i<av.length; i++)
				new ScanStreamTok(av[i]).process();
	}

	/** Construct a file scanner by name */
	public ScanStreamTok(String fileName) throws IOException {
		this(new FileReader(fileName));
	}

	/** Construct a file scanner by existing Reader */
	public ScanStreamTok(Reader rdr) throws IOException {
		tf = new StreamTokenizer(rdr);
		tf.eolIsSignificant(true);
	}

	protected void process() throws IOException {
		int i;

		while ((i = tf.nextToken()) != StreamTokenizer.TT_EOF) {
			switch(i) {
			case StreamTokenizer.TT_EOF:
				System.out.println("End of file");
				break;
			case StreamTokenizer.TT_EOL:
				System.out.println("End of line");
				break;
			case StreamTokenizer.TT_NUMBER:
				System.out.println("Number " + tf.nval);
				break;
			case StreamTokenizer.TT_WORD:
				System.out.println("Word, length " + tf.sval.length() + "->" + tf.sval);
				break;
			default:
				System.out.println("Character " + (char)i);
			}
		}
	}
}
