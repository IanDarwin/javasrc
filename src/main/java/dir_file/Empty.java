import java.io.*;
/**
 * DANGEROUS Program to empty a directory
 * Written for Course 333 comparison section.
 * @author	Ian Darwin, Learning Tree, Course 471/478
 */
class Empty {
	public static void main(String argv[]) {
		if (argv.length != 1) {	// no progname in argv[0]
			System.err.println("usage: Empty dirname");
			System.exit(1);
		}

		File dir = new File(argv[0]);
		if (!dir.exists()) {
			System.out.println(argv[0] + " does not exist");
			return;
		}

		String[] info = dir.list();
		for (int i=0; i<info.length; i++) {
			File n = new File(argv[0] + dir.separator + info[i]);
			if (!n.isFile())	// skip ., .., other directories too
				continue;
			System.out.println("removing " + n.getPath());
			if (!n.delete())
				System.err.println("Couldn't remove " + n.getPath());
		}
	}
}
