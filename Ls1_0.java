import java.io.*;

/**
 * ls - directory lister, like UNIX ls or DOS/VMS dir.
 *
 * @version 1.1, 04/04/96
 *
 * @author Ian Darwin
 */
public class Ls1_0 {
	/** doOneIsFile - structured to let you extend it (hint, hint) */
	private void doOneIsFile(File f) {
		System.out.println("File: " + f.getName());
	}
	/** process - handle one filesystem object by name */
	private void process(String s) {
		File f = new File(s);
		if (!f.exists()) {
			System.out.println(s + " does not exist");
			return;
		}
		if (f.isFile())
			doOneIsFile(f);
		else if (f.isDirectory()) {
			String objects[];
			objects = f.list();
			if (objects == null) {
				System.err.println("Error readdir()ing "+s);
				return;
			}
			for (int i=0; i<objects.length; i++)
				doOneIsFile(new File(s + f.separator + objects[i]));
		} else
			System.out.println("Unknown: " + s);
	}

	/** Main program */
	public static void main(String argv[]) {
		Ls1_0 myLister = new Ls1_0();

		if (argv.length == 0)
			myLister.process(".");
		else for (int i = 0; i<argv.length; i++)
			myLister.process(argv[i]);
	}
}
