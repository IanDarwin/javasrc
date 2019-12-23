package dir_file;

import java.nio.file.FileSystems;

// tag::main[]
public class ListRoots {
	public static void main(String argh_my_aching_fingers[]) {
		FileSystems.getDefault().getRootDirectories().forEach(System.out::println);
	}
}
// end::main[]
