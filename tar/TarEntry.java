/** One entry in an archive file.
 */
public class TarEntry {
	public static final int	NAMSIZ	= 100;
	public static final int	TUNMLEN	= 32;
	public static final int	TGNMLEN	= 32;

	// Next fourteen fields constitute one physical record.
	//  Padded to RECORDSIZE bytes long on tape/disk.

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
}
