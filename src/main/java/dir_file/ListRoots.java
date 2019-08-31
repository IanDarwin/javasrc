package dir_file;

import java.io.*;

// tag::main[]
public class ListRoots {
	public static void main(String argh_my_aching_fingers[]) {
		File[] drives = File.listRoots(); // Get list of names
		for (File dr : drives) {
			System.out.println(dr);		// Print the list
		}
	}
}
// end::main[]
