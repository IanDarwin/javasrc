import java.io.*;
import java.util.*;

/*
 * Tape Archive Lister, patterned loosely after java.util.ZipFile.
 * Since, unlike Zip files, there is no central directory, you have to
 * read the entire file either to be sure of having a particular file's
 * entry, or to know how many entries there are in the archive.
 * Written by Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */

public class TarFile {
	/** True after we've done the expensive read. */
	protected boolean read = false;
	/** The list of entries found in the archive */
	protected ArrayList list;

	/** Size of header block on tape. */
	public static final int	RECORDSIZE = 512;

	/* Size of each block, in records */
	protected int		blocking;
	/* Size of each block, in bytes */
	protected int		blocksize;

	/** File containing archive */
	protected String	fileName;

	/** Represents one "link" found in the archive. */
	class link {
		link	next;
		int		dev;
		int		ino;
		short		linkcount;
		byte		name[];		// max length = NAMSIZ+1
	};

	/** Construct (open) a Tar file by name */
	public TarFile(String name) {
		fileName = name;
		list = new ArrayList();
		read = false;
	}

	/** Construct (open) a Tar file by File */
	public TarFile(java.io.File name) throws IOException {
		this(name.getCanonicalPath());
	}

	/* Close the Tar file. */
	public void close() {
		return;
	}

	/* Returns an enumeration of the Tar file entries. */
	public Enumeration entries() {
		return null;
	}

	/** Returns the Tar entry for the specified name, or null if not found. */
	public TarEntry getEntry(String name) {
		return null;
	}

	/** Returns an InputStream for reading the contents of the 
	 * specified entry from the archive.
	 * May cause the entire file to be read.
	 */
	public InputStream getInputStream(TarEntry entry) {
		return null;
	}

	/** Returns the path name of the Tar file. */
	public String getName() {
		return null;
	}

	/** Returns the number of entries in the Tar archive.
	 * May cause the entire file to be read.
	 */
	public int size() {
		return 0;
	}
}
