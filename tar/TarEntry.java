/** One entry in an archive file.
 * @author Ian Darwin
 * @version $Id$
 */

/*
 * Tar format info taken from John Gilmore's public domain tar program,
 * @(#)tar.h 1.21 87/05/01	Public Domain, which said:
 * "Created 25 August 1985 by John Gilmore, ihnp4!hoptoad!gnu."
 * John is now gnu@toad.com, and by another path tar.h is GPL'd under GNU Tar.
 */
public class TarEntry {
	/** Where in the tar archive this entry's HEADER is found. */
	public long fileOffset = 0;

	/** The maximum size of a name */
	public static final int	NAMSIZ	= 100;
	public static final int	TUNMLEN	= 32;
	public static final int	TGNMLEN	= 32;

	// Next fourteen fields constitute one physical record.
	// Padded to RECORDSIZE bytes long on tape/disk.

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
	byte[]	linkname = new byte[NAMSIZ];
	byte[]	magic = new byte[8];
	byte[]	uname = new byte[TUNMLEN];
	byte[]	gname = new byte[TGNMLEN];
	byte[]	devmajor = new byte[8];
	byte[]	devminor = new byte[8];

	// End of the physical data fields.

	/* The checksum field is filled with this while checksum being computed. */
	public static final byte[] CHKBLANKS = {
		0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 
	}; /* 8 blanks, no null */

	/* The magic field is filled with this if uname and gname are valid. */
	public static final byte TMAGIC[] = {
		// 'u', 's', 't', 'a', 'r', ' ', ' ', '\0'
		0, 0, 0, 0, 0, 0, 0x20, 0x20, 0
	}; /* 7 chars and a null */

	/* Type value for Normal disk file, Unix compat */
	public static final int	LF_OLDNORMAL ='\0';		
	/* Type value for Normal disk file */
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

	/** Returns the name of the file this entry represents. */
	public String getName() {
		return new String(name);
	}

	/** Returns the modification time of the entry */
	public long getTime() {
		return 0;
	}

	/** Returns true if this entry represents a file */
	boolean isFile() {
		return type == LF_NORMAL || type == LF_OLDNORMAL;
	}

	/** Returns true if this entry represents a directory */
	boolean isDirectory() {
		return type == LF_DIR;
	}

	/** Returns true if this entry represents some type of UNIX special file */
	boolean isSpecial() {
		return type == LF_CHR || type == LF_BLK || type == LF_FIFO;
	}
}
