/** This class provides a dbm-compatible interface to the UNIX-style
 * database access methods described in db(3). Each unique record
 * in the database is a unique key/value pair, similar to a 
 * java.util.Hashtable but only stored on persistent medium, not
 * kept in-memory.
 *
 * @author This Java/C hookup by Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class DBM {
	public synchronized DBM(String file) {
		if (inuse)
			throw new IllegalArgumentException("UNIX limitation" + 
				"Only one DBM object allowed per Java Machine");
		inuse = true;
		int retCode = dbminit(file);
		if (retCode < 0)
			throw new IllegalArgumentException(
				"dbminit failed, code = " + retCode);
	}
			
	private native int dbminit(String file);

	private native int dbmclose();

	public native Object fetch(Object key);

	public native int store(Object key, Object content);

	public native int delete(Object key);

	public native Object firstkey();

	public native Object nextkey(Object key);
}
