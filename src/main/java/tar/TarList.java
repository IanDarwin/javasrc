package tar;

import java.io.*;
import java.text.*;	// only for formatting
import java.util.*;

/**
 * Demonstrate the Tar archive lister.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class TarList {
	public static void main(String[] argv) throws IOException, TarException {
		if (argv.length == 0) {
			System.err.println("Usage: TarList archive");
			System.exit(1);
		}
		new TarList(argv[0]).list();
	}
	/** The TarFile we are reading */
	TarFile tf;

	/** Constructor */
	public TarList(String fileName) {
		tf = new TarFile(fileName);
	}

	/** Generate and print the listing */
	public void list() throws IOException, TarException {
		Enumeration list = tf.entries();
		while (list.hasMoreElements()) {
			TarEntry e = (TarEntry)list.nextElement();
			System.out.println(toListFormat(e));
		}
	}

	protected StringBuffer sb;
	/** Shift used in formatting permissions */
	protected static int[] shft = { 6, 3, 0 };
	/** Format strings used in permissions */
	protected static String rwx[] = {
		"---", "--x", "-w-", "-wx",
		"r--", "r-x", "rw-", "rwx"
	};
	/** NumberFormat used in formatting List form string */
	NumberFormat sizeForm = new DecimalFormat("00000000");
	/** Date used in printing mtime */
	Date date = new Date();
	SimpleDateFormat dateForm =
		new SimpleDateFormat ("yyyy-MM-dd HH:mm");

	/** Format a TarEntry the same way that UNIX tar does */
	public String toListFormat(TarEntry e) {
		sb = new StringBuffer();
		switch(e.type) {
			case TarEntry.LF_OLDNORMAL:
			case TarEntry.LF_NORMAL:
			case TarEntry.LF_CONTIG:
			case TarEntry.LF_LINK:		// hard link: same as file
				sb.append('-');	// 'f' would be sensible
				break;
			case TarEntry.LF_DIR:
				sb.append('d');
				break;
			case TarEntry.LF_SYMLINK:
				sb.append('l');
				break;
			case TarEntry.LF_CHR:		// UNIX character device file
				sb.append('c');
				break;
			case TarEntry.LF_BLK:		// UNIX block device file
				sb.append('b');
				break;
			case TarEntry.LF_FIFO:		// UNIX named pipe
				sb.append('p');
				break;
			default:			// Can't happen?
				sb.append('?');
				break;
		}

		// Convert e.g., 754 to rwxrw-r--
		int mode = e.getMode();
		for (int i=0; i<3; i++) {
			sb.append(rwx[mode >> shft[i] & 007]);
		}
		sb.append(' ');

		// owner and group
		sb.append(e.getUname()).append('/').append(e.getGname()).append(' ');

		// size
		// DecimalFormat can't do "%-9d", so we do part of it ourselves
		sb.append(' ');
		String t = sizeForm.format(e.getSize());
		boolean digit = false;
		char c;
		for (int i=0; i<8; i++) {
			c = t.charAt(i);
			if (!digit && i<(8-1) && c == '0')
				sb.append(' ');		// leading space
			else {
				digit = true;
				sb.append(c);
			}
		}
		sb.append(' ');

		// mtime
		// copy file's mtime into Data object (after scaling
		// from "sec since 1970" to "msec since 1970"), and format it.
		date.setTime(1000*e.getTime());
		sb.append(dateForm.format(date)).append(' ');

		sb.append(e.getName());
		if (e.isLink())
			sb.append(" link to " ).append(e.getLinkName());
		if (e.isSymLink())
			sb.append(" -> " ).append(e.getLinkName());

		return sb.toString();
	}
}
