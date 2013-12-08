package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.darwinsys.io.FileHandler;

/**
 * File Duplicate Finder, a sample  plugin for the Crawler.
 */
public class FileDupFinder implements FileHandler {

	/** Map from a program's hash to its path */
	private Map<String, String> seenFiles = new HashMap<String, String>();
	private static final int BUFSIZE = 65536;
	private byte[] data = new byte[BUFSIZE];
	private PrintWriter out;
	private boolean debug = false;
	private File theFile;
	
	public void init() throws IOException {
		out = new PrintWriter(new FileWriter("/home/ian/fred"));
		System.out.println("Starting...");
	}
	
	/** Called to digest a file, report if the digest is in the Map, else add it to the Map.
	 * Note that it leaves the first file it sees with a given digest in the Map.
	 */
	public void visit(File f) throws IOException {
		String path = f.getAbsolutePath();

		String hash = getHash(f);
		
		if (debug) {
			System.out.println(path + "-->" + hash); // left in so you can run this, compare with md5/md5sum...
		}
		
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
		theFile = f;
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
		return toHex(digest);
	}

	public void destroy() throws IOException {
		out.close();
	}
	
    private static final char byteToHex[] = {
		'0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};

	/** Convert an array of bytes to a hex string. */
	public static final String toHex(byte bytes[]) {
		assert bytes != null : "invalid input to toHex()";
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			// System.out.println("input: " + bytes[i] + " ");
			sb.append((char)byteToHex[(bytes[i] >> 4) & 0x0f]);
			sb.append((char)byteToHex[bytes[i] & 0x0f]);
		}
		return (sb.toString());
	}

	public File getFile() {
		return theFile;
	}
}
