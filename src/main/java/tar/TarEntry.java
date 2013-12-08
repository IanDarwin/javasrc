package tar;

import java.io.*;

/** One entry in an archive file.
 * @author Ian Darwin
 * @note
 * Tar format info taken from John Gilmore's public domain tar program,
 * @(#)tar.h 1.21 87/05/01	Public Domain, which said:
 * "Created 25 August 1985 by John Gilmore, ihnp4!hoptoad!gnu."
 * John is now gnu@toad.com, and by another path tar.h is GPL'd in GNU Tar.
 */
public class TarEntry {
	/** Where in the tar archive this entry's HEADER is found. */
	public long fileOffset = 0;

	/** The maximum size of a name */
	public static final int	NAMSIZ	= 100;
	public static final int	TUNMLEN	= 32;
	public static final int	TGNMLEN	= 32;

	// Next fourteen fields constitute one physical record.
	// Padded to TarFile.RECORDSIZE bytes on tape/disk.
	// Lazy Evaluation: just read fields in raw form, only format when asked.

	/** File name */
	byte[]	name = new byte[NAMSIZ];
	/** permissions, e.g., rwxr-xr-x? */
	byte[]	mode = new byte[8];
	/* user */
	byte[]	uid = new byte[8];
	/* group */
	byte[]	gid = new byte[8];
	/* size */
	byte[]	size = new byte[12];
	/* UNIX modification time */
	byte[]	mtime = new byte[12];
	/* checksum field */
	byte[]	chksum = new byte[8];
	byte	type;
	byte[]	linkName = new byte[NAMSIZ];
	byte[]	magic = new byte[8];
	byte[]	uname = new byte[TUNMLEN];
	byte[]	gname = new byte[TGNMLEN];
	byte[]	devmajor = new byte[8];
	byte[]	devminor = new byte[8];

	// End of the physical data fields.

	/* The magic field is filled with this if uname and gname are valid. */
	public static final byte TMAGIC[] = {
		// 'u', 's', 't', 'a', 'r', ' ', ' ', '\0'
		0, 0, 0, 0, 0, 0, 0x20, 0x20, 0
	}; /* 7 chars and a null */

	/* Type value for Normal file, Unix compatibility */
	public static final int	LF_OLDNORMAL ='\0';		
	/* Type value for Normal file */
	public static final int	LF_NORMAL = '0';
	/* Type value for Link to previously dumped file */
	public static final int LF_LINK = 	'1';
	/* Type value for Symbolic link */
	public static final int LF_SYMLINK = '2';
	/* Type value for Character special file */
	public static final int LF_CHR = '3';
	/* Type value for Block special file */
	public static final int LF_BLK = '4';
	/* Type value for Directory */
	public static final int LF_DIR	 = '5';
	/* Type value for FIFO special file */
	public static final int LF_FIFO	 = '6';
	/* Type value for Contiguous file */
	public static final int LF_CONTIG = '7';

	/* Constructor that reads the entry's header. */
	public TarEntry(RandomAccessFile is) throws IOException, TarException {

		fileOffset = is.getFilePointer();

		// read() returns -1 at EOF
		if (is.read(name) < 0)
			throw new EOFException();
		// Tar pads to block boundary with nulls.
		if (name[0] == '\0')
			throw new EOFException();
		// OK, read remaining fields.
		is.read(mode);
		is.read(uid);
		is.read(gid);
		is.read(size);
		is.read(mtime);
		is.read(chksum);
		type = is.readByte();
		is.read(linkName);
		is.read(magic);
		is.read(uname);
		is.read(gname);
		is.read(devmajor);
		is.read(devminor);

		// Since the tar header is < 512, we need to skip it.
		is.skipBytes((int)(TarFile.RECORDSIZE -
			(is.getFilePointer() % TarFile.RECORDSIZE)));

		// TODO if checksum() fails,
		//	throw new TarException("Failed to find next header");

	}

	/** Returns the name of the file this entry represents. */
	public String getName() {
		return new String(name).trim();
	}

	public String getTypeName() {
		switch(type) {
		case LF_OLDNORMAL:
		case LF_NORMAL:
			return "file";
		case LF_LINK:
			return "link w/in archive";
		case LF_SYMLINK:
			return "symlink";
		case LF_CHR:
		case LF_BLK:
		case LF_FIFO:
			return "special file";
		case LF_DIR:
			return "directory";
		case LF_CONTIG:
			return "contig";
		default:
			throw new IllegalStateException("TarEntry.getTypeName: type " + type + " invalid");
		}
	}

	/** Returns the UNIX-specific "mode" (type+permissions) of the entry */
	public int getMode() {
		try {
			return Integer.parseInt(new String(mode).trim(), 8) & 0777;
		} catch (IllegalArgumentException e) {
			return 0;
		}
	}

	/** Returns the size of the entry */
	public int getSize() {
		try {
			return Integer.parseInt(new String(size).trim(), 8);
		} catch (IllegalArgumentException e) {
			return 0;
		}
	}

	/** Returns the name of the file this entry is a link to,
	 * or null if this entry is not a link.
	 */
	public String getLinkName() {
		// if (isLink())
		// 	return null;
		return new String(linkName).trim();
	}

	
	/** Returns the modification time of the entry */
	public long getTime() {
		try {
			return Long.parseLong(new String(mtime).trim(), 8);
		} catch (IllegalArgumentException e) {
			return 0;
		}
	}

	/** Returns the string name of the userid */
	public String getUname() {
		return new String(uname).trim();
	}

	/** Returns the string name of the group id */
	public String getGname() {
		return new String(gname).trim();
	}

	/** Returns the numeric userid of the entry */
	public int getuid() {
		try {
			return Integer.parseInt(new String(uid).trim());
		} catch (IllegalArgumentException e) {
			return -1;
		}
	}
	/** Returns the numeric gid of the entry */
	public int getgid() {
		try {
			return Integer.parseInt(new String(gid).trim());
		} catch (IllegalArgumentException e) {
			return -1;
		}
	}

	/** Returns true if this entry represents a file */
	boolean isFile() {
		return type == LF_NORMAL || type == LF_OLDNORMAL;
	}

	/** Returns true if this entry represents a directory */
	boolean isDirectory() {
		return type == LF_DIR;
	}

	/** Returns true if this a hard link (to a file in the archive) */
	boolean isLink() {
		return type == LF_LINK;
	}

	/** Returns true if this a symbolic link */
	boolean isSymLink() {
		return type == LF_SYMLINK;
	}

	/** Returns true if this entry represents some type of UNIX special file */
	boolean isSpecial() {
		return type == LF_CHR || type == LF_BLK || type == LF_FIFO;
	}

	public String toString() {
		return "TarEntry[" + getName() + ']';
	} 
}
