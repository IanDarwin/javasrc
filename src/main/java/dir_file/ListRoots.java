package dir_file;

import java.io.*;

// BEGIN main
public class ListRoots {
	public static void main(String argh_my_aching_fingers[]) {
		File[] drives = File.listRoots(); // Get list of names
		for (File dr : drives) {
			System.out.println(dr);		// Print the list
		}
	}
}
// END main
