import java.io.*;
/**
 * DirTree - directory lister, like UNIX ls or DOS/VMS dir
 *
 * @author	Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
class DirTree {
	/** Main program */
	public static void main(String argv[]) {
		DirTree dt = new DirTree();
		if (argv.length == 0)
			dt.doDir(".");
		else for (int i = 0; i<argv.length; i++)
			dt.doDir(argv[i]);
	}

	/** doDir - handle one filesystem object by name */
	private void doDir(String s) {
		File f = new File(s);
		if (!f.exists()) {
			System.out.println(s + " does not exist");
			return;
		}
		if (f.isFile())
			doFile(f);
		else if (f.isDirectory()) {
			System.out.println("d " + f.getPath());
			String objects[] = f.list();

			for (int i=0; i<objects.length; i++)
				doDir(s + f.separator + objects[i]);
		} else
			System.err.println("Unknown: " + s);
	}

	/** doFile - process one regular file. */
	private static void doFile(File f) {
		System.out.println("f " + f.getPath());
	}
}
