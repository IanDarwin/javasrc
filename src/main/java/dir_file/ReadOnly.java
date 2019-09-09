package dir_file;

import java.io.*;

// tag::main[]
public class ReadOnly {
	public static void main(String[] a) throws IOException {

		File f = new File("f");
		f.deleteOnExit();
		
		if (!f.createNewFile()) {
			System.out.println("Can't create new file.");
			return;
		}

		if (!f.canWrite()) {
			System.out.println("Can't write new file!");
			return;
		}

		if (!f.setReadOnly()) {
			System.out.println("Grrr! Can't set file read-only.");
			return;
		}
		Runtime.getRuntime().exec("ls -l " + f.getAbsolutePath());

		if (f.canWrite()) {
			System.out.println("Most immutable, captain!");
			System.out.println("But it still says canWrite() after setReadOnly");
			return;
		} else {
			System.out.println("Logical, captain!");
			System.out.println(
				"canWrite() correctly returns false after setReadOnly");
		}
	}
}
// end::main[]
