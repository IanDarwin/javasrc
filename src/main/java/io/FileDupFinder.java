package ca.tcp.fileindex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import ca.tcp.utils.TCPPasswordUtils;

import com.darwinsys.io.FileHandler;

/**
 * A File Indexer plugin for the Crawler.
 * @version $Id$
 */
public class FileDupFinder implements FileHandler {

	/** Map from a program's hash to its path */
	private Map seenFiles = new HashMap();
	private static final int BUFSIZE = 65536;
	byte[] data = new byte[BUFSIZE];
	private PrintWriter out;
	
	public void init() throws IOException {
		out = new PrintWriter(new File("/home/ian/fred"));
		System.out.println("Starting...");
	}
	
	/** Called to digest a file, report if the digest is in the Map, else add it to the Map.
	 * Note that it leaves the first file it sees with a given digest in the Map.
	 */
	public void visit(File f) throws IOException {
		String path = f.getAbsolutePath();

		String hash = getHash(f);
		
		// System.out.println(path + "-->" + hash); // left in so you can run this, compare with md5/md5sum...

		String hashedPath = null;
		if ((hashedPath = (String)seenFiles.get(hash)) != null) {
			out.println(f + " " + hashedPath);
			return;
		}
		seenFiles.put(hash, f.getCanonicalPath());
	}

	/**
	 * @param f
	 * @return
	 */
	private String getHash(File f) throws IOException {
		InputStream in = null;

		MessageDigest md;
		try {
			in = new FileInputStream(f);
			md = (MessageDigest) MessageDigest.getInstance("MD5");
			int n = 0;
			while ((n = in.read(data)) > 0) {
				md.update(data, 0, n);		// digest it
			}
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(e.toString());
		} finally {
			if (in != null)
				in.close();
		}		
		
		// Digest the credentials.
		byte[] digest = md.digest();

		// return as hex string.
		String result = TCPPasswordUtils.toHex(digest);

		return result;
	}

	public void destroy() throws IOException {
		out.close();
	}
}
