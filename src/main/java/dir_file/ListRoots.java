package dir_file;

import java.io.*;

public class ListRoots {
	public static void main(String argh_my_aching_fingers[]) {
		File[] drives = File.listRoots(); // Get list of names
		for (int i=0; i<drives.length; i++)
			System.out.println(drives[i]);	// Print the list
	}
}
