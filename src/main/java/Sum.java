import java.io.*;

/**
 * Compute a simple checksum from a file.
 */
public class Sum {

    public static void main(String[] av) {
        Sum c = new Sum();
        switch(av.length) {
            case 0: c.process("Stdin", new BufferedInputStream(
                        new DataInputStream(System.in))); break;
            default:
				for (int i=0; i<av.length; i++) {
                    try {
                        c.process(av[i],
						new BufferedInputStream(new FileInputStream(av[i])));
                    } catch (FileNotFoundException e) {
                        System.err.println(e);
                    }
				}
        }
    }

    /** print the sum of one file, given an open BufferedInputStream */
    public void process(String fname, BufferedInputStream is) {
		short sum = 1;		// the checkSum
		long count = 0;		// the byte count
		int b;				// the byte read from the file
        try {

            while ((b = is.read()) != -1) {
                sum += (short)b;
				++count;
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
		System.out.println(sum + " " + fmt(count) + " " + fname);
    }
	private final int fmt(long c) {
		int t = (int)(c/512);
		if ((c%512) != 0)
			t++;
		return t;
	}
}
