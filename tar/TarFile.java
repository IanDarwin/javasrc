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
	protected Vector list;

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
		list = new Vector();
		read = false;
	}

	/** Construct (open) a Tar file by File */
	public TarFile(java.io.File name) throws IOException {
		this(name.getCanonicalPath());
	}

	/** The main datastream. */
	protected RandomAccessFile is;

	/** Read the Tar archive in its entirety.
	 * This is semi-lazy evaluation, in that we don't read the file
	 * until we need to.
	 * A future revision may use even lazier evaluation: in getEntry,
	 * scan the list and, if not found, continue reading!
	 * For now, just read the whole file.
	 */
	protected void readFile() throws IOException, TarException {
	 	is = new RandomAccessFile(fileName, "r");
		TarEntry hdr;
		do {
			hdr = new TarEntry(is);
			if (hdr.getSize() < 0) {
				System.out.println("Size < 0");
				break;
			}
			System.out.println(hdr.toString());
			list.addElement(hdr);
			// Get the size of the entry
			int nbytes = hdr.getSize(), diff;
			// Round it up to blocksize.
			if ((diff = (nbytes % RECORDSIZE)) != 0) {
				nbytes -= diff; nbytes += RECORDSIZE;
			}
			// And skip over the data portion.
			System.out.println("Currently at " + is.getFilePointer());
			System.out.println("Skipping " + nbytes + " bytes");
			is.skipBytes(nbytes);
		} while (true);
		read = true;
		return;
	}

	/* Close the Tar file. */
	public void close() {
		try {
			is.close();
		} catch (IOException e) {
			// nothing to do
		}
	}

	/* Returns an enumeration of the Tar file entries. */
	public Enumeration entries() throws IOException, TarException {
		if (!read)
			readFile();
		return list.elements();
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
