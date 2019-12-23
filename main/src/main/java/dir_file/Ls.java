package dir_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Simple directory lister. */
// tag::main[]
public class Ls {
	public static void main(String args[]) throws IOException {
		Files.list(Path.of("."))
			.sorted()
			.forEach(dir -> {
				System.out.println(dir);
			});
	}
}
// end::main[]
