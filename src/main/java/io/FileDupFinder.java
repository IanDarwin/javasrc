package ca.tcp.fileindex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	
	public void visit(File f) throws IOException {
		String path = f.getAbsolutePath();

		String hash = getHash(f);

		String hashedPath = null;
		if ((hashedPath = (String)seenFiles.get(hash)) != null) {
			System.out.println(f + " " + hashedPath);
			return;
		}
		seenFiles.put(hash, f.getCanonicalPath());
	}

	/**
	 * @param f
	 * @return
	 */
	private String getHash(File f) throws IOException {
		InputStream in = new FileInputStream(f);
		MessageDigest md;
		try {
			md = (MessageDigest) MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(e.toString());
		}
		
		int n = 0;
		while ((n = in.read(data)) > 0) {
			md.update(data, 0, n);		// digest it
		}
		// Digest the credentials.
		byte[] digest = md.digest();

		// return as hex string.
		String result = TCPPasswordUtils.toHex(digest);

		return result;
	}

}
