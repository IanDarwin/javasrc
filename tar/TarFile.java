/*
 * Tape Archive Lister
 * Written by Ian Darwin, ian@darwinsys.com
 *
 * Tar format info taken from John Gilmore's public domain tar program,
 * @(#)tar.h 1.21 87/05/01	Public Domain, which said:
 * "Created 25 August 1985 by John Gilmore, ihnp4!hoptoad!gnu." (
 * John is now gnu@toad.com, and by another path tar.h is GPL'd under GNU Tar.
 */

public class Tar {

	/** Size of header block on tape.  */
	public static final int	RECORDSIZE	512

	/* The checksum field is filled with this while the checksum is computed. */
	public static final byte[] CHKBLANKS = {
		' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
	}; /* 8 blanks, no null */

	/* The magic field is filled with this if uname and gname are valid. */
	public static final String	TMAGIC		"ustar  "	/* 7 chars and a null */

	protected int		blocking;	/* Size of each block, in records */
	protected int		blocksize;	/* Size of each block, in bytes */
	protected String	ar_file;	/* File containing archive */

	/*
	 * Flags from the command line
	 */
	protected boolean f_reblock;		/* -B */
	protected boolean f_create;			/* -c */
	protected boolean f_diff;			/* -d */
	protected boolean f_sayblock;		/* -D */
	protected boolean f_follow_links;	/* -h */
	protected boolean f_ignorez;		/* -i */
	protected boolean f_keep;			/* -k */
	protected boolean f_modified;		/* -m */
	protected boolean f_oldarch;		/* -o */
	protected boolean f_use_protection;	/* -p */
	protected boolean f_sorted_names;	/* -s */
	protected boolean f_list;			/* -t */
	protected boolean f_namefile;		/* -T */
	protected boolean f_verbose;		/* -v */
	protected boolean f_extract;		/* -x */
	protected boolean f_compress;		/* -z */

	/** Represents one "link" found in the archive.
	 */
	class link {
		link	next;
		int		dev;
		int		ino;
		short		linkcount;
		byte		name[NAMSIZ+1];
	};
}
