package dir_file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;

/**
 * Simple demo of Files.walkFileTree() method; always continues,
 * so will visit everything in and under the starting point.
 */
// tag::main[]
public class WalkTreeDemo {

	public static void main(String[] args) throws IOException {
		String dir = args.length == 0 ? "." : args[0];
		Files.walkFileTree(Path.of(dir), myVisitor);
	}

	static final FileVisitor myVisitor = new FileVisitor() {
		@Override
		public FileVisitResult preVisitDirectory(Object dir,
			BasicFileAttributes attrs) throws IOException {
			System.out.println("Starting Directory " + dir);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Object dir,
			IOException exc) throws IOException {
			System.out.println("Finished Directory " + dir +
				" " + (exc != null? exc : ""));
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Object file,
			BasicFileAttributes attrs) throws IOException {
			System.out.println("Visiting File " + file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Object file,
			IOException exc) throws IOException {
			System.out.println(
				MessageFormat.format("FAILURE visiting {0} ({1})", file, exc));
			return FileVisitResult.CONTINUE;
		}
	};
}
// end::main[]
