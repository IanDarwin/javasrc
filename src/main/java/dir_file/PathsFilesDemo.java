package dir_file;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsFilesDemo {
	public static void main(String[] args) throws Exception {
		// BEGIN main
		Path p = Paths.get("my_junk_file");                    // <1>
		boolean deleted = Files.deleteIfExists(p);             // <2>
		InputStream is =                                       // <3>
				PathsFilesDemo.class.getResourceAsStream("/demo.txt");
		long newFileSize = Files.copy(is, p);                  // <4>
		System.out.println(newFileSize);                       // <5>
		final Path realPath = p.toRealPath();                  // <6>
		System.out.println(realPath);
		realPath.forEach(pc-> System.out.println(pc));         // <7>
		Files.delete(p);                                       // <8>
		// END main
	}
}
