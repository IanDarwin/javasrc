import java.io.*;

/** This class provides a dbm-compatible interface to the UNIX-style
 * database access methods described in dbm(3) (which is on some UNIXes
 * a front-end to db(3).
 * <P>Each unique record in the database is a unique key/value pair,
 * similar to a java.util.Hashtable but stored on persistent medium, not
 * kept in memory. Dbm was originally optimized for UNIX for fast
 * access to individual key/value pairs.
 *
 * @author This Java/C hookup by Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class DBM {
	/** Since you can only have one DBM database in use at a time due
	 * to implementation restrictions, we enforce this rule with a
	 * class-wide boolean.
	 */
	protected static boolean inuse = false;

	/** Save the filename for messages, etc. */
	protected String fileName;

	/** Construct a DBM given its filename */
	public DBM(String file) {
		synchronized(this) {
			if (inuse)
				throw new IllegalArgumentException(
					"Only one DBM object at a time per Java Machine");
			inuse = true;
		}
		fileName = file;
		int retCode = dbminit(fileName);
		if (retCode < 0)
			throw new IllegalArgumentException(
				"dbminit failed, code = " + retCode);
	}

	// Static code blocks are executed once, when class file is loaded.
	// This is here to ensure that the shared library gets loaded.
	static {
		System.loadLibrary("jdbm");
	}

	protected ByteArrayOutputStream bo;

	/** serialize an Object to byte array. */
	protected byte[] toByteArray(Object o) throws IOException {
		if (bo == null)
			bo = new ByteArrayOutputStream(1024);
		bo.reset();
		ObjectOutputStream os = new ObjectOutputStream(bo);
		os.writeObject(o);
		os.close();
		return bo.toByteArray();
	}


	/** un-serialize an Object from a byte array. */
	protected Object toObject(byte[] b) throws IOException {
		Object o;

		ByteArrayInputStream bi = new ByteArrayInputStream(b);
		ObjectInputStream os = new ObjectInputStream(bi);
		try {
			o = os.readObject();
		} catch (ClassNotFoundException ex) {
			// Convert ClassNotFoundException to I/O error
			throw new IOException(ex.getMessage());
		}
		os.close();
		return o;
	}

	protected native int dbminit(String file);

	protected native int dbmclose();

	/** Public wrapper for close method. */
	public void close() {
		this.dbmclose();
		inuse = false;
	}

	protected native byte[] dbmfetch(byte[] key);

	public Object fetch(Object key) throws IOException {
		byte[] datum = dbmfetch(toByteArray(key));
		return toObject(datum);
	}

	protected native int dbmstore(byte[] key, byte[] content);

	public void store(Object key, Object value) throws IOException {
		dbmstore(toByteArray(key), toByteArray(value));
	}

	protected native int delete(Object key);

	public native Object firstkey();

	public native Object nextkey(Object key);

	public String toString() {
		return "DBM@" + hashCode() + "[" + fileName + "]";
	}
}
