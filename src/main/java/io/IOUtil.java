import java.io.*;

/**
 * Some simple file I-O primitives reimplemented in Java
 */
public class IOUtil {

	public static void main(String av[]) {
		IOUtil f = new IOUtil();
		try {
			f.copyDataFile("IOUtil.class", "IOUtil-class.bak");
			f.copyTextFile("IOUtil.java", "IOUtil-java.bak");
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** Copy a data file from one filename to another */
	public void copyDataFileAnotherWay(String inName, String outName) throws
			FileNotFoundException, IOException {
		BufferedInputStream is = 
			new BufferedInputStream(new FileInputStream(inName));
		BufferedOutputStream os = 
			new BufferedOutputStream(new FileOutputStream(outName));
		int b;				// the byte read from the file
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		os.close();
	}

	/** The size of blocking to use */
	protected static final int BLKSIZ = 8192;

	/** Copy a data file from one filename to another, alternate method.
	 * As the name suggests, use my own buffer instead of letting
	 * the BufferedReader allocate and use the buffer.
	 */
	public void copyDataFile(String inName, String outName) throws
			FileNotFoundException, IOException {
		InputStream is = new FileInputStream(inName);
		OutputStream os = new FileOutputStream(outName);
		int count = 0;		// the byte count
		byte b[] = new byte[BLKSIZ];	// the bytes read from the file
		while ((count = is.read(b)) != -1) {
			os.write(b, 0, count);
		}
		is.close();
		os.close();
	}

	public void copyTextFile(String inName, String outName) throws
			FileNotFoundException, IOException {
		copyDataFile(inName, outName);
	}
}
