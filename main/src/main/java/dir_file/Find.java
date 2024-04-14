package dir_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

// tag::main[]
/**
 * Find - find files by name, size, or other criteria. Non-GUI version.
 */
public class Find {
	
	public enum Conjunction { AND, OR };
	
	private static Logger logger = Logger.getLogger(Find.class.getSimpleName());
	static boolean started;
	
	/** Main program 
	 * @throws IOException If the Files.walkTree does so
	 */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			startWalkingAt(".");
		} else {
			for (int i = 0; i < args.length; i++) {
				if (args[i].charAt(0) == '-') {
					switch(args[i].substring(1)) {
					case "name":
						Find.filter.setNameFilter(args[++i]);
						continue;
					case "size":
						Find.filter.setSizeFilter(args[++i]);
						continue;
//					Not implemented by back-end yet
//					case "a":
//						finder.filter.addConjunction(Conjunction.AND);
//						continue;
//					case "o":
//						finder.filter.addConjunction(Conjunction.OR);
//						continue;
					default: throw new IllegalArgumentException(
						"Unknown argument " + args[i]);
					}
				}
				startWalkingAt(args[i]);
			}
			if (!started) {
				startWalkingAt(".");
			}
		}
	}

	protected static FindFilter filter = new FindFilter();

	public static void usage() {
		System.err.println(
			"Usage: Find [-n namefilter][-s sizefilter][dir...]");
		System.exit(1);
	}

	/** doName - handle one filesystem object by name */
	private static void startWalkingAt(String s) throws IOException {
		logger.info("doName(" + s + ")");
		started = true;
		Path f = Path.of(s);
		if (!Files.exists(f)) {
			System.out.println(s + " does not exist");
			return;
		}
		Files.walk(f).forEach(fp -> {
			try {
				if (Files.isRegularFile(fp))
					doFile(fp);
				else if (Files.isDirectory(fp)) {
					doDir(fp);
				} else {
					System.err.println("Unknown type: " + s);
				}
			} catch (IOException e) {
				throw new RuntimeException("IO Exception: " + e);
			}
		});
	}

	/** doFile - process one regular file. 
	 * @throws IOException */
	private static void doFile(Path f) throws IOException {
		if (filter.accept(f)) {
			System.out.println("f " + f);
		}
	}
	
	/** doDir - process a directory */
	private static void doDir(Path d) {
		System.out.println("d " + d.normalize());
	}
}
// end::main[]
